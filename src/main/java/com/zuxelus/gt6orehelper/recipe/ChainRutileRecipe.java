package com.zuxelus.gt6orehelper.recipe;

import gregapi.data.MT;
import gregapi.data.OP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class ChainRutileRecipe extends ChainRecipe {

	public ChainRutileRecipe(IInventory inventory) {
		super(inventory, "Rutile -> Titanium", CHAIN5);
		groups = new SlotGroup[5];
		groups[0] = new SlotGroup(inventory, 0, 9, 25, new ItemStack[] {
				OP.dust.mat(MT.TiO2, 1), OP.dust.mat(MT.C, 1), getStack(MT.CaCO3.mLiquid.getFluid(), 144), getStack(MT.Cl.mGas.getFluid(), 4000),
				null, null, getStack(MT.TiCl4.mLiquid.getFluid(), 5000), getStack(MT.CO2.mGas.getFluid(), 3000),
				getMachineStack("Burner Mixer") });
		groups[1] = new SlotGroup(inventory, 10, 121, 25, new ItemStack[] {
				OP.dust.mat(MT.Mg, 2), null, getStack(MT.TiCl4.mLiquid.getFluid(), 5000), null,
				OP.dust.mat(MT.Ti, 1), OP.dust.mat(MT.MgCl2, 6), null, null,
				getMachineStack("Bath") });
		groups[2] = new SlotGroup(inventory, 20, 121, 79, new ItemStack[] {
				OP.dust.mat(MT.MgCl2, 6), null, null, null,
				null, null, getStack(MT.MgCl2.mLiquid.getFluid(), 864), null,
				getMachineStack("Smelter") });
		groups[3] = new SlotGroup(inventory, 30, 65, 133, new ItemStack[] {
				null, null, getStack(MT.MgCl2.mLiquid.getFluid(), 864), null,
				OP.dust.mat(MT.Mg, 2), null, getStack(MT.Cl.mGas.getFluid(), 4000), null,
				getMachineStack("Electrolyzer") });
		groups[4] = new SlotGroup(inventory, 40, 9, 79, new ItemStack[] {
				null, null, getStack(MT.CO2.mGas.getFluid(), 3000), null,
				OP.dust.mat(MT.C, 1), null, getStack(MT.O.mGas.getFluid(), 2000), null,
				getMachineStack("Electrolyzer") });
	}
}
