package com.zuxelus.gt6orehelper.recipe;

import gregapi.data.MT;
import gregapi.data.OP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class ChainTungstateRecipe extends ChainRecipe {

	public ChainTungstateRecipe(IInventory inventory) {
		super(inventory, "Tungstate -> Tungsten", CHAIN6);
		groups = new SlotGroup[6];
		groups[0] = new SlotGroup(inventory, 0, 9, 25, new ItemStack[] {
				OP.dust.mat(MT.OREMATS.Tungstate, 7), null, getStack(MT.HCl.mGas.getFluid(), 4000), null, 
				OP.dust.mat(MT.LiCl, 4), OP.dust.mat(MT.H2WO4, 7), null, null,
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
				OP.dust.mat(MT.LiCl, 4), null, getStack(MT.H2O.mLiquid.getFluid(), 12000), null,
				OP.dust.mat(MT.LiOH, 6), getStack(MT.Cl.mGas.getFluid(), 2000), getStack(MT.H.mGas.getFluid(), 6000), getStack(MT.O.mGas.getFluid(), 2000),
				getMachineStack("Electrolyzer") });
		groups[4] = new SlotGroup(inventory, 40, 9, 133, new ItemStack[] {
				OP.dust.mat(MT.LiOH, 6), null, null, null,
				OP.dust.mat(MT.Li, 2), null, getStack(MT.O.mGas.getFluid(), 2000), getStack(MT.H.mGas.getFluid(), 2000),
				getMachineStack("Electrolyzer") });
		groups[5] = new SlotGroup(inventory, 50, 9, 79, new ItemStack[] {
				null, null, getStack(MT.Cl.mGas.getFluid(), 2000), getStack(MT.H2O.mLiquid.getFluid(), 6000),
				null, null, getStack(MT.HCl.mGas.getFluid(), 4000), getStack(MT.O.mGas.getFluid(), 1000),
				getMachineStack("Mixer") });
	}
}
