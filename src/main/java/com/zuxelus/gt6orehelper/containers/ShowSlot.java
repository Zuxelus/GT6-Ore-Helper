package com.zuxelus.gt6orehelper.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class ShowSlot extends Slot {

	public ShowSlot(IInventory inventory, int id, int x, int y) {
		super(inventory, id, x, y);
	}

	@Override
	public boolean canTakeStack(EntityPlayer player) {
		return false;
	}
}
