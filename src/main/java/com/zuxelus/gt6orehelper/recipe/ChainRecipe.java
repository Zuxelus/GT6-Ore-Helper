package com.zuxelus.gt6orehelper.recipe;

import java.util.ArrayList;
import java.util.List;

import com.zuxelus.gt6orehelper.GT6OreHelper;
import com.zuxelus.gt6orehelper.containers.ShowSlot;

import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.data.FL;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public abstract class ChainRecipe {
	public static final ResourceLocation CHAIN3 = new ResourceLocation(GT6OreHelper.MODID + ":textures/gui/gui_chain3.png");
	public static final ResourceLocation CHAIN5 = new ResourceLocation(GT6OreHelper.MODID + ":textures/gui/gui_chain5.png");
	public static final ResourceLocation CHAIN6 = new ResourceLocation(GT6OreHelper.MODID + ":textures/gui/gui_chain6.png");
	protected MultiTileEntityRegistry reg = MultiTileEntityRegistry.getRegistry("gt.multitileentity");
	protected IInventory inventory;
	protected SlotGroup[] groups;

	public String name;
	public ResourceLocation texture;

	public ChainRecipe(IInventory inventory, String name, ResourceLocation texture) {
		this.inventory = inventory;
		this.name = name;
		this.texture = texture;
	}

	public List<Slot> getSlots() {
		ArrayList slots = new ArrayList();
		for (SlotGroup group : groups)
			for (Slot slot : group.slots)
				slots.add(slot);
		return slots;
	}

	public ItemStack getStack(Fluid fluid, long amount) {
		return FL.display(FL.make(fluid, amount), false, false);
	}

	public ItemStack getMachineStack(String name) {
		if (name.equals("Electrolyzer"))
			return reg.getItem(20091);
		if (name.equals("Mixer"))
			return reg.getItem(20181);
		if (name.equals("Smelter"))
			return reg.getItem(20241);
		if (name.equals("Dryer"))
			return reg.getItem(20311);
		if (name.equals("Burner Mixer"))
			return reg.getItem(20521);
		if (name.equals("Bath"))
			return reg.getItem(22002);
		return null;
	}

	public class SlotGroup {
		public Slot[] slots;

		public SlotGroup(IInventory inv, int first, int x, int y, ItemStack[] stacks) {
			slots = new Slot[9];
			addSlot(inv, 0, first, x, y, stacks);
			addSlot(inv, 1, first, x + 18, y, stacks);
			addSlot(inv, 2, first, x, y + 18, stacks);
			addSlot(inv, 3, first, x + 18, y + 18, stacks);
			addSlot(inv, 4, first, x + 56, y, stacks);
			addSlot(inv, 5, first, x + 74, y, stacks);
			addSlot(inv, 6, first, x + 56, y + 18, stacks);
			addSlot(inv, 7, first, x + 74, y + 18, stacks);
			addSlot(inv, 8, first, x + 37, y + 9, stacks);
		}

		private void addSlot(IInventory inv, int id, int first, int x, int y, ItemStack[] stacks) {
			if (stacks[id] == null)
				return;
			slots[id] = new ShowSlot(inv, first + id, x, y);
			slots[id].putStack(stacks[id]);
		}
	}
}
