/*
 * This file is part of Applied Energistics 2. Copyright (c) 2013 - 2014, AlgorithmX2, All rights reserved. Applied
 * Energistics 2 is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version. Applied Energistics 2 is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General
 * Public License for more details. You should have received a copy of the GNU Lesser General Public License along with
 * Applied Energistics 2. If not, see <http://www.gnu.org/licenses/lgpl>.
 */

package appeng.client.texture;

import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public enum ExtraBlockTextures {

    BlockVibrationChamberFrontOn("BlockVibrationChamberFrontOn"),

    OreQuartzStone("OreQuartzStone"),

    MEChest("BlockMEChest"),

    BlockMEChestItems_Light("BlockMEChestItems_Light"),
    BlockMEChestItems_Dark("BlockMEChestItems_Dark"),
    BlockMEChestItems_Medium("BlockMEChestItems_Medium"),

    BlockControllerPowered("BlockControllerPowered"),
    BlockControllerColumnPowered("BlockControllerColumnPowered"),
    BlockControllerColumn("BlockControllerColumn"),
    BlockControllerLights("BlockControllerLights"),
    BlockControllerColumnLights("BlockControllerColumnLights"),
    BlockControllerColumnConflict("BlockControllerColumnConflict"),
    BlockControllerConflict("BlockControllerConflict"),
    BlockControllerInsideA("BlockControllerInsideA"),
    BlockControllerInsideB("BlockControllerInsideB"),

    BlockCreativeEnergyControllerPowered("BlockCreativeEnergyControllerPowered"),
    BlockCreativeEnergyControllerColumnPowered("BlockCreativeEnergyControllerColumnPowered"),
    BlockCreativeEnergyControllerColumn("BlockCreativeEnergyControllerColumn"),
    BlockCreativeEnergyControllerInsideA("BlockCreativeEnergyControllerInsideA"),
    BlockCreativeEnergyControllerInsideB("BlockCreativeEnergyControllerInsideB"),

    BlockMolecularAssemblerLights("BlockMolecularAssemblerLights"),

    BlockChargerInside("BlockChargerInside"),

    BlockInterfaceAlternate("BlockInterfaceAlternate"),
    BlockInterfaceAlternateArrow("BlockInterfaceAlternateArrow"),

    MEStorageCellTextures("MEStorageCellTextures"),
    White("White"),

    BlockMatterCannonParticle("BlockMatterCannonParticle"),
    BlockEnergyParticle("BlockEnergyParticle"),

    GlassFrame("BlockQuartzGlassFrame"),

    BlockQRingCornerLight("BlockQRingCornerLight"),
    BlockQRingEdgeLight("BlockQRingEdgeLight"),

    MEDenseEnergyCell0("BlockDenseEnergyCell0"),
    MEDenseEnergyCell1("BlockDenseEnergyCell1"),
    MEDenseEnergyCell2("BlockDenseEnergyCell2"),
    MEDenseEnergyCell3("BlockDenseEnergyCell3"),
    MEDenseEnergyCell4("BlockDenseEnergyCell4"),
    MEDenseEnergyCell5("BlockDenseEnergyCell5"),
    MEDenseEnergyCell6("BlockDenseEnergyCell6"),
    MEDenseEnergyCell7("BlockDenseEnergyCell7"),

    MEEnergyCell0("BlockEnergyCell0"),
    MEEnergyCell1("BlockEnergyCell1"),
    MEEnergyCell2("BlockEnergyCell2"),
    MEEnergyCell3("BlockEnergyCell3"),
    MEEnergyCell4("BlockEnergyCell4"),
    MEEnergyCell5("BlockEnergyCell5"),
    MEEnergyCell6("BlockEnergyCell6"),
    MEEnergyCell7("BlockEnergyCell7"),

    BlockSpatialPylon_dim("BlockSpatialPylon_dim"),
    BlockSpatialPylon_red("BlockSpatialPylon_red"),

    BlockSpatialPylonC("BlockSpatialPylon_spanned"),
    BlockSpatialPylonC_dim("BlockSpatialPylon_spanned_dim"),
    BlockSpatialPylonC_red("BlockSpatialPylon_spanned_red"),

    BlockQuartzGlassB("BlockQuartzGlassB"),
    BlockQuartzGlassC("BlockQuartzGlassC"),
    BlockQuartzGlassD("BlockQuartzGlassD"),

    BlockSpatialPylonE("BlockSpatialPylon_end"),
    BlockSpatialPylonE_dim("BlockSpatialPylon_end_dim"),
    BlockSpatialPylonE_red("BlockSpatialPylon_end_red"),

    BlockMESecurityOn_Light("BlockMESecurityOn_Light"),
    BlockMESecurityOn_Medium("BlockMESecurityOn_Medium"),
    BlockMESecurityOn_Dark("BlockMESecurityOn_Dark"),
    BlockInscriberInside("BlockInscriberInside"),

    BlockQuartzGrowthAcceleratorOn("BlockQuartzGrowthAcceleratorOn"),
    BlockQuartzGrowthAcceleratorSideOn("BlockQuartzGrowthAcceleratorSideOn"),

    BlockWirelessInside("BlockWirelessInside"),

    BlockCraftingAccelerator("BlockCraftingAccelerator"),
    BlockCraftingAccelerator4x("BlockCraftingAccelerator4x"),
    BlockCraftingAccelerator16x("BlockCraftingAccelerator16x"),
    BlockCraftingAccelerator64x("BlockCraftingAccelerator64x"),
    BlockCraftingAccelerator256x("BlockCraftingAccelerator256x"),
    BlockCraftingAccelerator1024x("BlockCraftingAccelerator1024x"),
    BlockCraftingAccelerator4096x("BlockCraftingAccelerator4096x"),
    BlockCraftingMonitor("BlockCraftingMonitor"),

    BlockCraftingStorage1k("BlockCraftingStorage"),
    BlockCraftingStorage4k("BlockCraftingStorage4k"),
    BlockCraftingStorage16k("BlockCraftingStorage16k"),
    BlockCraftingStorage64k("BlockCraftingStorage64k"),
    BlockCraftingStorage256k("BlockCraftingStorage256k"),
    BlockCraftingStorage1024k("BlockCraftingStorage1024k"),
    BlockCraftingStorage4096k("BlockCraftingStorage4096k"),
    BlockCraftingStorage16384k("BlockCraftingStorage16384k"),
    BlockCraftingStorageSingularity("BlockCraftingStorageSingularity"),

    BlockCraftingAcceleratorFit("BlockCraftingAcceleratorFit"),
    BlockCraftingAccelerator4xFit("BlockCraftingAccelerator4xFit"),
    BlockCraftingAccelerator16xFit("BlockCraftingAccelerator16xFit"),

    BlockCraftingAccelerator64xFit("BlockCraftingAccelerator64xFit"),
    BlockCraftingAccelerator256xFit("BlockCraftingAccelerator256xFit"),
    BlockCraftingAccelerator1024xFit("BlockCraftingAccelerator1024xFit"),
    BlockCraftingAccelerator4096xFit("BlockCraftingAccelerator4096xFit"),

    BlockCraftingMonitorFit_Light("BlockCraftingMonitorFit_Light"),
    BlockCraftingMonitorFit_Dark("BlockCraftingMonitorFit_Dark"),
    BlockCraftingMonitorFit_Medium("BlockCraftingMonitorFit_Medium"),

    BlockCraftingStorage1kFit("BlockCraftingStorageFit"),
    BlockCraftingStorage4kFit("BlockCraftingStorage4kFit"),
    BlockCraftingStorage16kFit("BlockCraftingStorage16kFit"),
    BlockCraftingStorage64kFit("BlockCraftingStorage64kFit"),
    BlockCraftingStorage256kFit("BlockCraftingStorage256kFit"),
    BlockCraftingStorage1024kFit("BlockCraftingStorage1024kFit"),
    BlockCraftingStorage4096kFit("BlockCraftingStorage4096kFit"),
    BlockCraftingStorage16384kFit("BlockCraftingStorage16384kFit"),
    BlockCraftingStorageSingularityFit("BlockCraftingStorageSingularityFit"),

    BlockCraftingUnitRing("BlockCraftingUnitRing"),
    BlockCraftingUnitRingLongRotated("BlockCraftingUnitRingLongRotated"),
    BlockCraftingUnitRingLong("BlockCraftingUnitRingLong"),
    BlockCraftingUnitFit("BlockCraftingUnitFit"),

    BlockCraftingMonitorOuter("BlockCraftingMonitorOuter"),
    BlockCraftingFitSolid("BlockCraftingFitSolid"),

    BlockPaint2("BlockPaint2"),
    BlockPaint3("BlockPaint3");

    private final String name;
    private IIcon IIcon;

    ExtraBlockTextures(final String name) {
        this.name = name;
    }

    public static ResourceLocation GuiTexture(final String string) {
        return new ResourceLocation("appliedenergistics2", "textures/" + string);
    }

    @SideOnly(Side.CLIENT)
    public static IIcon getMissing() {
        return TextureUtils.getMissingBlock();
    }

    public String getName() {
        return this.name;
    }

    public IIcon getIcon() {
        return this.IIcon;
    }

    public void registerIcon(final TextureMap map) {
        this.IIcon = map.registerIcon("appliedenergistics2:" + this.name);
    }
}
