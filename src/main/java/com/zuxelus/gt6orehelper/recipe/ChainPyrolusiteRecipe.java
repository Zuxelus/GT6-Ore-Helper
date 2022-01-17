package com.zuxelus.gt6orehelper.recipe;

import gregapi.data.MT;
import gregapi.data.OP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class ChainPyrolusiteRecipe extends ChainRecipe {

	public ChainPyrolusiteRecipe(IInventory inventory) {
		super(inventory, "Pyrolusite -> Manganese", CHAIN3);
		groups = new SlotGroup[3];
		groups[0] = new SlotGroup(inventory, 0, 9, 25, new ItemStack[] {
				OP.dust.mat(MT.MnO2, 1), null, getStack(MT.HCl.mGas.getFluid(), 8000), null, 
				OP.dust.mat(MT.MnCl2, 3), null, getStack(MT.H2O.mLiquid.getFluid(), 6000), getStack(MT.Cl.mGas.getFluid(), 2000),
				getMachineStack("Mixer") });
		groups[1] = new SlotGroup(inventory, 10, 121, 25, new ItemStack[] {
				OP.dust.mat(MT.MnCl2, 3), null, null, null,
				OP.dust.mat(MT.Mn, 1), null, getStack(MT.Cl.mGas.getFluid(), 2000), null,
				getMachineStack("Electrolyzer") });
		groups[2] = new SlotGroup(inventory, 20, 65, 79, new ItemStack[] {
				null, null, getStack(MT.Cl.mGas.getFluid(), 4000), getStack(MT.H2O.mLiquid.getFluid(), 6000),
				null, null, getStack(MT.HCl.mGas.getFluid(), 8000), getStack(MT.O.mGas.getFluid(), 2000),
				getMachineStack("Mixer") });
	}
}
