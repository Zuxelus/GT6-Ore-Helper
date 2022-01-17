package com.zuxelus.gt6orehelper.containers;

import com.zuxelus.gt6orehelper.recipe.ChainRecipe;
import com.zuxelus.zlib.containers.ContainerBase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerChain extends ContainerBase<InventoryBasic> {

	public ContainerChain(InventoryBasic inventory, ChainRecipe recipe) {
		super(inventory);

		for (Slot slot : recipe.getSlots())
			if (slot != null)
				addSlotToContainer(slot);
	}

	@Override
	public ItemStack slotClick(int p_75144_1_, int p_75144_2_, int p_75144_3_, EntityPlayer p_75144_4_) {
		return null;
	}
}
