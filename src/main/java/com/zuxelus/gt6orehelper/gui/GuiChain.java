package com.zuxelus.gt6orehelper.gui;

import org.lwjgl.input.Keyboard;

import com.zuxelus.gt6orehelper.containers.ContainerChain;
import com.zuxelus.gt6orehelper.recipe.ChainRecipe;
import com.zuxelus.zlib.gui.GuiContainerBase;

import codechicken.nei.NEIClientConfig;
import codechicken.nei.NEIClientUtils;
import codechicken.nei.recipe.GuiCraftingRecipe;
import codechicken.nei.recipe.GuiUsageRecipe;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class GuiChain extends GuiContainerBase {
	public GuiScreen prevGui;
	private Slot currSlot;

	public GuiChain(ContainerChain container, GuiScreen prevgui, ChainRecipe recipe) {
		super(container, recipe.name, recipe.texture);
		xSize = 220;
		ySize = 176;
		prevGui = prevgui;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		fontRendererObj.drawString(name, (xSize - fontRendererObj.getStringWidth(name)) / 2, 6, 0x404040);
		currSlot = null;
		for (int i = 0; i < inventorySlots.inventorySlots.size(); i++) {
			Slot slot = (Slot) inventorySlots.inventorySlots.get(i);
			if (isMouseOverSlot(slot, mouseX, mouseY) && slot.func_111238_b())
				currSlot = slot;
		}
	}

	@Override
	public void keyTyped(char keyChar, int keyCode) {
		if (keyCode == Keyboard.KEY_ESCAPE) { // esc
			mc.displayGuiScreen(prevGui);
			return;
		}
		if (currSlot == null)
			return;

		ItemStack stackover = currSlot.getStack();
		if (stackover == null)
			return;

		if (keyCode == NEIClientConfig.getKeyBinding("gui.usage") || (keyCode == NEIClientConfig.getKeyBinding("gui.recipe") && NEIClientUtils.shiftKey())) {
			GuiUsageRecipe.openRecipeGui("item", stackover.copy());
			return;
		}

		if (keyCode == NEIClientConfig.getKeyBinding("gui.recipe")) {
			GuiCraftingRecipe.openRecipeGui("item", stackover.copy());
			return;
		}
	}

	protected boolean isMouseOverSlot(Slot slot, int mouseX, int mouseY) {
		int k1 = guiLeft;
		int l1 = guiTop;
		mouseX -= k1;
		mouseY -= l1;
		return mouseX >= slot.xDisplayPosition - 1 && mouseX < slot.xDisplayPosition + 16 + 1
				&& mouseY >= slot.yDisplayPosition - 1 && mouseY < slot.yDisplayPosition + 16 + 1;
	}

	/*private void drawSlot(Slot slot) {
		int i = slot.xDisplayPosition;
		int j = slot.yDisplayPosition;
		ItemStack stack = slot.getStack();
		boolean flag1 = false;

		zLevel = 100.0F;
		itemRender.zLevel = 100.0F;

		if (stack == null) {
			IIcon iicon = slot.getBackgroundIconIndex();

			if (iicon != null) {
				GL11.glDisable(GL11.GL_LIGHTING);
				GL11.glEnable(GL11.GL_BLEND); // Forge: Blending needs to be enabled for this.
				mc.getTextureManager().bindTexture(TextureMap.locationItemsTexture);
				drawTexturedModelRectFromIcon(i, j, iicon, 16, 16);
				GL11.glDisable(GL11.GL_BLEND); // Forge: And clean that up
				GL11.glEnable(GL11.GL_LIGHTING);
				flag1 = true;
			}
		}
		if (!flag1) {
			GL11.glEnable(GL11.GL_DEPTH_TEST);
			itemRender.renderItemAndEffectIntoGUI(fontRendererObj, mc.getTextureManager(), stack, i, j);
			itemRender.renderItemOverlayIntoGUI(fontRendererObj, mc.getTextureManager(), stack, i, j, null);
		}

		itemRender.zLevel = 0.0F;
		zLevel = 0.0F;
	}*/
}
