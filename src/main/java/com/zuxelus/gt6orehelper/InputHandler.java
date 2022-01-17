package com.zuxelus.gt6orehelper;

import com.zuxelus.gt6orehelper.containers.ContainerChain;
import com.zuxelus.gt6orehelper.gui.GuiChain;
import com.zuxelus.gt6orehelper.recipe.*;

import codechicken.nei.NEIClientConfig;
import codechicken.nei.NEIClientUtils;
import codechicken.nei.guihook.GuiContainerManager;
import codechicken.nei.guihook.IContainerInputHandler;
import gregapi.data.MT;
import gregapi.data.OP;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;

public class InputHandler implements IContainerInputHandler {

	public boolean lastKeyTyped(GuiContainer gui, char keyChar, int keyCode) {
		ItemStack stackover = GuiContainerManager.getStackMouseOver(gui);
		if (stackover == null)
			return false;
		if (keyCode == NEIClientConfig.getKeyBinding("gui.usage") && NEIClientUtils.shiftKey())
			return openChainGui(stackover.copy());
		return false;
	}

	private static boolean openChainGui(ItemStack stack) {
		InventoryBasic inventory = new InventoryBasic("", false, 60);
		ChainRecipe recipe = getRecipe(stack, inventory);
		ContainerChain container = new ContainerChain(inventory, recipe);
		if (recipe == null)
			return false;
		Minecraft mc = NEIClientUtils.mc();
		GuiScreen prevscreen = mc.currentScreen;
		mc.displayGuiScreen(new GuiChain(container, prevscreen, recipe));
		return true;
	}

	private static ChainRecipe getRecipe(ItemStack stack, IInventory inventory) {
		if (stack.isItemEqual(OP.dust.mat(MT.Fe2O3, 1)))
			return new ChainHematiteRecipe(inventory);
		if (stack.isItemEqual(OP.dust.mat(MT.MnO2, 1)))
			return new ChainPyrolusiteRecipe(inventory);
		if (stack.isItemEqual(OP.dust.mat(MT.TiO2, 1)))
			return new ChainRutileRecipe(inventory);
		if (stack.isItemEqual(OP.dust.mat(MT.OREMATS.Ferberite, 1)))
			return new ChainFerberiteRecipe(inventory);
		if (stack.isItemEqual(OP.dust.mat(MT.OREMATS.Huebnerite, 1)))
			return new ChainHuebneriteRecipe(inventory);
		if (stack.isItemEqual(OP.dust.mat(MT.OREMATS.Ilmenite, 1)))
			return new ChainIlmeniteRecipe(inventory);
		if (stack.isItemEqual(OP.dust.mat(MT.OREMATS.Scheelite, 1)))
			return new ChainScheeliteRecipe(inventory);
		if (stack.isItemEqual(OP.dust.mat(MT.OREMATS.Tungstate, 1)))
			return new ChainTungstateRecipe(inventory);
		if (stack.isItemEqual(OP.dust.mat(MT.OREMATS.Wolframite, 1)))
			return new ChainWolframiteRecipe(inventory);
		return null;
	}

	public boolean mouseClicked(GuiContainer gui, int mousex, int mousey, int button) {
		return false;
	}

	public void onKeyTyped(GuiContainer gui, char keyChar, int keyID) {}

	public void onMouseClicked(GuiContainer gui, int mousex, int mousey, int button) {}

	public void onMouseUp(GuiContainer gui, int mousex, int mousey, int button) {}

	public boolean keyTyped(GuiContainer gui, char keyChar, int keyID) {
		return false;
	}

	public boolean mouseScrolled(GuiContainer gui, int mousex, int mousey, int scrolled) {
		return false;
	}

	public void onMouseScrolled(GuiContainer gui, int mousex, int mousey, int scrolled) {}

	public void onMouseDragged(GuiContainer gui, int mousex, int mousey, int button, long heldTime) {}
}
