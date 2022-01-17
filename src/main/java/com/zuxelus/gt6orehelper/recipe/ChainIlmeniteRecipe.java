package com.zuxelus.gt6orehelper.recipe;

import gregapi.data.MT;
import gregapi.data.OP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class ChainIlmeniteRecipe extends ChainRecipe {

	public ChainIlmeniteRecipe(IInventory inventory) {
		super(inventory, "Ilmenite -> Rutile", CHAIN3);
		groups = new SlotGroup[2];
		groups[0] = new SlotGroup(inventory, 0, 9, 25, new ItemStack[] {
				OP.dust.mat(MT.OREMATS.Ilmenite, 5), null, getStack(MT.H2SO4.mLiquid.getFluid(), 7000), null, 
				OP.dust.mat(MT.TiO2, 1), null, getStack(MT.GreenVitriol.mLiquid.getFluid(), 6000), getStack(MT.H2O.mLiquid.getFluid(), 3000),
				getMachineStack("Bath") });
		groups[1] = new SlotGroup(inventory, 10, 121, 25, new ItemStack[] {
				null, null, getStack(MT.GreenVitriol.mLiquid.getFluid(), 6000), getStack(MT.H2O.mLiquid.getFluid(), 3000),
				OP.dust.mat(MT.Fe, 1), null, getStack(MT.H2SO4.mLiquid.getFluid(), 7000), getStack(MT.O.mGas.getFluid(), 1000),
				getMachineStack("Electrolyzer") });
	}
}
