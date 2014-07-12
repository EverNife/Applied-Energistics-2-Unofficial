package appeng.items.tools.powered;

import java.util.Collections;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.util.ForgeDirection;
import appeng.api.AEApi;
import appeng.api.config.Actionable;
import appeng.api.config.FuzzyMode;
import appeng.api.config.SortDir;
import appeng.api.implementations.items.IItemGroup;
import appeng.api.implementations.items.IStorageCell;
import appeng.api.networking.security.BaseActionSource;
import appeng.api.storage.ICellInventory;
import appeng.api.storage.ICellInventoryHandler;
import appeng.api.storage.IMEInventory;
import appeng.api.storage.StorageChannel;
import appeng.api.storage.data.IAEItemStack;
import appeng.api.storage.data.IItemList;
import appeng.api.util.AEColor;
import appeng.client.render.items.ToolColorApplicatorRender;
import appeng.core.AEConfig;
import appeng.core.features.AEFeature;
import appeng.core.localization.GuiText;
import appeng.helpers.IMouseWheelItem;
import appeng.hooks.DispenserBlockTool;
import appeng.hooks.IBlockTool;
import appeng.items.contents.CellConfig;
import appeng.items.contents.CellUpgrades;
import appeng.items.misc.ItemPaintBall;
import appeng.items.tools.powered.powersink.AEBasePoweredItem;
import appeng.me.storage.CellInventoryHandler;
import appeng.util.ItemSorters;
import appeng.util.Platform;
import appeng.util.item.AEItemStack;

public class ToolColorApplicator extends AEBasePoweredItem implements IStorageCell, IItemGroup, IBlockTool, IMouseWheelItem
{

	public ToolColorApplicator() {
		super( ToolColorApplicator.class, null );
		setfeature( EnumSet.of( AEFeature.ColorApplicator, AEFeature.StorageCells, AEFeature.PoweredTools ) );
		maxStoredPower = AEConfig.instance.colorapplicator_battery;
		if ( Platform.isClient() )
			MinecraftForgeClient.registerItemRenderer( this, new ToolColorApplicatorRender() );
	}

	@Override
	public void postInit()
	{
		super.postInit();
		BlockDispenser.dispenseBehaviorRegistry.putObject( this, new DispenserBlockTool() );
	}

	public ItemStack getColor(ItemStack is)
	{
		NBTTagCompound c = is.getTagCompound();
		if ( c != null && c.hasKey( "color" ) )
		{
			NBTTagCompound color = c.getCompoundTag( "color" );
			ItemStack oldColor = ItemStack.loadItemStackFromNBT( color );
			if ( oldColor != null )
				return oldColor;
		}

		return findNextColor( is, null, 0 );
	}

	private ItemStack findNextColor(ItemStack is, ItemStack anchor, int scrollOffset)
	{
		ItemStack newColor = null;

		IMEInventory<IAEItemStack> inv = AEApi.instance().registries().cell().getCellInventory( is, StorageChannel.ITEMS );
		if ( inv != null )
		{
			IItemList<IAEItemStack> itemList = inv.getAvailableItems( AEApi.instance().storage().createItemList() );
			if ( anchor == null )
			{
				IAEItemStack firstItem = itemList.getFirstItem();
				if ( firstItem != null )
					newColor = firstItem.getItemStack();
			}
			else
			{
				LinkedList<IAEItemStack> list = new LinkedList<IAEItemStack>();

				for (IAEItemStack i : itemList)
					list.add( i );

				ItemSorters.Direction = SortDir.ASCENDING;
				ItemSorters.init();
				Collections.sort( list, ItemSorters.ConfigBased_SortByName );

				if ( list.size() <= 0 )
					return null;

				IAEItemStack where = list.getFirst();
				int cycles = 1 + list.size();

				while (cycles > 0 && !where.equals( anchor ))
				{
					list.addLast( list.removeFirst() );
					cycles--;
					where = list.getFirst();
				}

				if ( scrollOffset > 0 )
					list.addLast( list.removeFirst() );

				if ( scrollOffset < 0 )
					list.addFirst( list.removeLast() );

				return list.get( 0 ).getItemStack();
			}
		}

		if ( newColor != null )
			setColor( is, newColor );

		return newColor;
	}

	public void setColor(ItemStack is, ItemStack newColor)
	{
		NBTTagCompound data = Platform.openNbtData( is );
		if ( newColor == null )
			data.removeTag( "color" );
		else
		{
			NBTTagCompound color = new NBTTagCompound();
			newColor.writeToNBT( color );
			data.setTag( "color", color );
		}
	}

	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer p, World w, int x, int y, int z, int side, float hitx, float hity, float hitz)
	{
		Block blk = w.getBlock( x, y, z );
		double powerPerUse = 100;

		ItemStack paintBall = getColor( is );

		IMEInventory<IAEItemStack> inv = AEApi.instance().registries().cell().getCellInventory( is, StorageChannel.ITEMS );
		if ( inv != null )
		{
			IAEItemStack option = inv.extractItems( AEItemStack.create( paintBall ), Actionable.SIMULATE, new BaseActionSource() );

			if ( option != null )
			{
				paintBall = option.getItemStack();
				paintBall.stackSize = 1;
			}
			else
				paintBall = null;

			if ( paintBall != null && paintBall.getItem() instanceof ItemPaintBall )
			{
				ItemPaintBall ipb = (ItemPaintBall) paintBall.getItem();
				AEColor color = ipb.getColor( paintBall );

				if ( getAECurrentPower( is ) > powerPerUse )
				{
					if ( color != AEColor.Transparent
							&& recolourBlock( blk, ForgeDirection.getOrientation( side ), w, x, y, z, ForgeDirection.getOrientation( side ), color ) )
					{
						inv.extractItems( AEItemStack.create( paintBall ), Actionable.MODULATE, new BaseActionSource() );
						extractAEPower( is, powerPerUse );
						return true;
					}
				}

			}
		}

		if ( p.isSneaking() )
		{
			cycleColors( is, paintBall, 1 );
		}

		return false;
	}

	private boolean recolourBlock(Block blk, ForgeDirection side, World w, int x, int y, int z, ForgeDirection orientation, AEColor newColor)
	{
		if ( blk == Blocks.glass )
		{
			return w.setBlock( x, y, z, Blocks.stained_glass, newColor.ordinal(), 3 );
		}

		if ( blk == Blocks.stained_glass )
		{
			int meta = w.getBlockMetadata( x, y, z );
			if ( newColor.ordinal() == meta )
				return false;
			return w.setBlock( x, y, z, Blocks.stained_glass, newColor.ordinal(), 3 );
		}

		return blk.recolourBlock( w, x, y, z, side, newColor.ordinal() );
	}

	public void cycleColors(ItemStack is, ItemStack paintBall, int i)
	{
		if ( paintBall == null )
		{
			setColor( is, getColor( is ) );
		}
		else
		{
			setColor( is, findNextColor( is, paintBall, i ) );
		}
	}

	@Override
	public String getItemStackDisplayName(ItemStack par1ItemStack)
	{
		String extra = GuiText.Empty.getLocal();

		ItemStack selected = getColor( par1ItemStack );

		if ( selected != null && selected.getItem() instanceof ItemPaintBall )
			extra = ((ItemPaintBall) selected.getItem()).getExtraName( selected );

		return super.getItemStackDisplayName( par1ItemStack ) + " - " + extra;
	}

	@Override
	public void addInformation(ItemStack is, EntityPlayer player, List lines, boolean advancedItemTooltips)
	{
		super.addInformation( is, player, lines, advancedItemTooltips );

		IMEInventory<IAEItemStack> cdi = AEApi.instance().registries().cell().getCellInventory( is, StorageChannel.ITEMS );

		if ( cdi instanceof CellInventoryHandler )
		{
			ICellInventory cd = ((ICellInventoryHandler) cdi).getCellInv();
			if ( cd != null )
			{
				lines.add( cd.getUsedBytes() + " " + GuiText.Of.getLocal() + " " + cd.getTotalBytes() + " " + GuiText.BytesUsed.getLocal() );
				lines.add( cd.getStoredItemTypes() + " " + GuiText.Of.getLocal() + " " + cd.getTotalItemTypes() + " " + GuiText.Types.getLocal() );
			}
		}
	}

	@Override
	public int getBytes(ItemStack cellItem)
	{
		return 512;
	}

	@Override
	public int BytePerType(ItemStack iscellItem)
	{
		return 8;
	}

	@Override
	public int getTotalTypes(ItemStack cellItem)
	{
		return 27;
	}

	@Override
	public boolean isBlackListed(ItemStack cellItem, IAEItemStack requsetedAddition)
	{
		return requsetedAddition == null || !(requsetedAddition.getItem() instanceof ItemPaintBall && requsetedAddition.getItemDamage() < 20);
	}

	@Override
	public boolean storableInStorageCell()
	{
		return true;
	}

	@Override
	public boolean isStorageCell(ItemStack i)
	{
		return true;
	}

	@Override
	public IInventory getUpgradesInventory(ItemStack is)
	{
		return new CellUpgrades( is, 2 );
	}

	@Override
	public IInventory getConfigInventory(ItemStack is)
	{
		return new CellConfig( is );
	}

	@Override
	public FuzzyMode getFuzzyMode(ItemStack is)
	{
		String fz = Platform.openNbtData( is ).getString( "FuzzyMode" );
		try
		{
			return FuzzyMode.valueOf( fz );
		}
		catch (Throwable t)
		{
			return FuzzyMode.IGNORE_ALL;
		}
	}

	@Override
	public String getUnlocalizedGroupName(ItemStack is)
	{
		return GuiText.StorageCells.getUnlocalized();
	}

	@Override
	public void setFuzzyMode(ItemStack is, FuzzyMode fzMode)
	{
		Platform.openNbtData( is ).setString( "FuzzyMode", fzMode.name() );
	}

	@Override
	public boolean isEditable(ItemStack is)
	{
		return true;
	}

	@Override
	public double getIdleDrain()
	{
		return 0.5;
	}

	@Override
	public void onWheel(ItemStack is, boolean up)
	{
		cycleColors( is, getColor( is ), up ? 1 : -1 );
	}

}
