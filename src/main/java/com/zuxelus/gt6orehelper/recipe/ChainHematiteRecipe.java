package com.zuxelus.gt6orehelper.recipe;

import gregapi.data.MT;
import gregapi.data.OP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class ChainHematiteRecipe extends ChainRecipe {

	public ChainHematiteRecipe(IInventory inventory) {
		super(inventory, "Hematite -> Iron", CHAIN5);
		groups = new SlotGroup[5];
		groups[0] = new SlotGroup(inventory, 0, 9, 25, new ItemStack[] {
				OP.dust.mat(MT.Fe2O3, 5), null, getStack(MT.HCl.mGas.getFluid(), 12000), null, 
				OP.dust.mat(MT.FeCl3, 8), null, getStack(MT.H2O.mLiquid.getFluid(), 9000), null,
				getMachineStack("Bath") });
		groups[1] = new SlotGroup(inventory, 10, 121, 25, new ItemStack[] {
				OP.dust.mat(MT.Fe, 1), OP.dust.mat(MT.FeCl3, 8), null, null,
				OP.dust.mat(MT.FeCl2, 9), null, null, null,
				getMachineStack("Mixer") });
		groups[2] = new SlotGroup(inventory, 20, 121, 79, new ItemStack[] {
				OP.dust.mat(MT.FeCl2, 9), null, null, null,
				null, null, getStack(MT.FeCl2.mLiquid.getFluid(), 1296), null,
				getMachineStack("Smelter") });
		groups[3] = new SlotGroup(inventory, 30, 65, 133, new ItemStack[] {
				 null, null, getStack(MT.FeCl2.mLiquid.getFluid(), 1296), null,
				OP.dust.mat(MT.Fe, 3), null,getStack(MT.Cl.mGas.getFluid(), 6000), null,
				getMachineStack("Electrolyzer") });
		groups[4] = new SlotGroup(inventory, 40, 9, 79, new ItemStack[] {
				null, null, getStack(MT.Cl.mGas.getFluid(), 6000), getStack(MT.H2O.mLiquid.getFluid(), 9000),
				null, null, getStack(MT.HCl.mGas.getFluid(), 12000),getStack(MT.O.mGas.getFluid(), 3000),
				getMachineStack("Mixer") });
	}
}
