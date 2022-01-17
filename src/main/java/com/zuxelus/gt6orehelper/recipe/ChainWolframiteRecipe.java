package com.zuxelus.gt6orehelper.recipe;

import gregapi.data.MT;
import gregapi.data.OP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class ChainWolframiteRecipe extends ChainRecipe {

	public ChainWolframiteRecipe(IInventory inventory) {
		super(inventory, "Wolframite -> Tungsten", CHAIN6);
		groups = new SlotGroup[6];
		groups[0] = new SlotGroup(inventory, 0, 9, 25, new ItemStack[] {
				OP.dust.mat(MT.OREMATS.Wolframite, 6), null, getStack(MT.HCl.mGas.getFluid(), 4000), null, 
				OP.dust.mat(MT.MgCl2, 3), OP.dust.mat(MT.H2WO4, 7), null, null,
				getMachineStack("Bath") });
		groups[1] = new SlotGroup(inventory, 10, 121, 25, new ItemStack[] {
				OP.dust.mat(MT.H2WO4, 7), null, null, null,
				OP.dust.mat(MT.WO3, 4), null, getStack(MT.DistWater.mLiquid.getFluid(), 3000), null,
				getMachineStack("Dryer") });
		groups[2] = new SlotGroup(inventory, 20, 121, 79, new ItemStack[] {
				OP.dust.mat(MT.WO3, 4), null, getStack(MT.H.mGas.getFluid(), 6000), null,
				OP.dust.mat(MT.W, 1), null, getStack(MT.H2O.mLiquid.getFluid(), 9000), null,
				getMachineStack("Mixer") });
		groups[3] = new SlotGroup(inventory, 30, 121, 133, new ItemStack[] {
				OP.dust.mat(MT.MgCl2, 3), null, null, null,
				null, null, getStack(MT.MgCl2.mLiquid.getFluid(), 432), null,
				getMachineStack("Smelter") });
		groups[4] = new SlotGroup(inventory, 40, 9, 133, new ItemStack[] {
				null, null, getStack(MT.MgCl2.mLiquid.getFluid(), 432), null,
				OP.dust.mat(MT.Mg, 1), null,getStack(MT.Cl.mGas.getFluid(), 2000), null,
				getMachineStack("Electrolyzer") });
		groups[5] = new SlotGroup(inventory, 50, 9, 79, new ItemStack[] {
				null, null, getStack(MT.Cl.mGas.getFluid(), 2000), getStack(MT.H2O.mLiquid.getFluid(), 6000),
				null, null, getStack(MT.HCl.mGas.getFluid(), 4000), getStack(MT.O.mGas.getFluid(), 1000),
				getMachineStack("Mixer") });
	}
}
