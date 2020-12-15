package com.zuxelus.gt6orehelper.nei;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import com.zuxelus.gt6orehelper.nei.OreHelper.BlockWrapper;
import com.zuxelus.gt6orehelper.nei.OreHelper.Ore2LayerWrapper;
import com.zuxelus.gt6orehelper.nei.OreHelper.OreBedrockWrapper;
import com.zuxelus.gt6orehelper.nei.OreHelper.OreLargeWrapper;
import com.zuxelus.gt6orehelper.nei.OreHelper.OreLayerWrapper;
import com.zuxelus.gt6orehelper.nei.OreHelper.OreSmallWrapper;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import gregapi.code.ItemStackContainer;
import gregapi.data.CS;
import gregapi.data.CS.BlocksGT;
import gregapi.util.ST;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class OreHandler extends TemplateRecipeHandler {

	public class CachedOreRecipe extends TemplateRecipeHandler.CachedRecipe {
		public ArrayList<PositionedStack> input;
		public String name;
		public int number;
		public int type;

		@Override
		public ArrayList<PositionedStack> getIngredients() {
			return this.input;
		}

		@Override
		public PositionedStack getResult() {
			return null;
		}

		public CachedOreRecipe(String name, ItemStack ore, int type) {
			this.name = name;
			this.type = type;
			this.input = new ArrayList<PositionedStack>();
			input.add(new PositionedStack(ore, 2, 1));
		}

		public CachedOreRecipe(String name, ItemStack ore, ItemStack flower, int type, int num) {
			this(name, ore, type);
			this.number = num;
			if (flower != null)
				input.add(new PositionedStack(flower, 24, 1));
		}

		public CachedOreRecipe(String name, ItemStack ore, ItemStack top, ItemStack bottom, int type, int num) {
			this(name, ore, top, type, num);
			input.add(new PositionedStack(bottom, 46, 1));
		}

		public CachedOreRecipe(String name, ItemStack top, ItemStack bottom, ItemStack between, ItemStack spread, int type) {
			this(name, top, type);
			input.add(new PositionedStack(bottom, 24, 1));
			input.add(new PositionedStack(between, 46, 1));
			input.add(new PositionedStack(spread, 68, 1));
		}
	}

	@Override
	public int recipiesPerPage() {
		return 1;
	}

	@Override
	public String getRecipeName() {
		return I18n.format("gt6orehelper.nei.worldgen.name");
	}

	@Override
	public String getGuiTexture() {
		return null;
	}

	@Override
	public void loadCraftingRecipes(ItemStack result) {
		String unlocalizedName = result.getUnlocalizedName();
		if (unlocalizedName.startsWith("oredict.ore") || unlocalizedName.startsWith("oredict.oreSmall")) {
			short meta = (short) result.getItemDamage();
			for (OreBedrockWrapper worldgen : OreHelper.mapOreBedrockWrapper.values()) {
				if (meta == worldgen.material.mID) {
					arecipes.add(new CachedOreRecipe(worldgen.name + worldgen.material.mNameInternal,
							ST.make((Block) BlocksGT.oreBedrock, 1, worldgen.material.mID),
							ST.make(worldgen.flower, 1, worldgen.flowerMeta), 0, 0));
				}
			}
			for (OreSmallWrapper worldgen : OreHelper.mapOreSmallWrapper.values()) {
				if (meta == worldgen.material.mID) {
					arecipes.add(new CachedOreRecipe(worldgen.name,
							ST.make((Block) BlocksGT.oreSmall, 1, worldgen.material.mID), 1));
				}
			}
			for (OreLargeWrapper worldgen : OreHelper.mapOreLargeWrapper.values()) {
				if ((meta == worldgen.material.mID) || (meta == worldgen.mBottom.mID) || (meta == worldgen.mBetween.mID) || (meta == worldgen.mSpread.mID)) {
					arecipes.add(new CachedOreRecipe(worldgen.name,
							ST.make((Block) BlocksGT.stoneToNormalOres.get(new ItemStackContainer(Blocks.stone, 1, 0)), 1, worldgen.material.mID),
							ST.make((Block) BlocksGT.stoneToNormalOres.get(new ItemStackContainer(Blocks.stone, 1, 0)), 1, worldgen.mBottom.mID),
							ST.make((Block) BlocksGT.stoneToNormalOres.get(new ItemStackContainer(Blocks.stone, 1, 0)), 1, worldgen.mBetween.mID),
							ST.make((Block) BlocksGT.stoneToNormalOres.get(new ItemStackContainer(Blocks.stone, 1, 0)), 1, worldgen.mSpread.mID), 2));
				}
			}
			for (OreLayerWrapper worldgen : OreHelper.mapOreLayerWrapper) {
				if (meta == worldgen.material.mID) {
					arecipes.add(new CachedOreRecipe(worldgen.name, ST.make(worldgen.stone, 1, 0),
							ST.make((Block) BlocksGT.stoneToNormalOres.get(new ItemStackContainer(worldgen.stone, 1, 0)), 1, worldgen.material.mID),
							3, worldgen.number));
				}
			}
			for (Ore2LayerWrapper worldgen : OreHelper.mapOre2LayerWrapper) {
				if (meta == worldgen.material.mID) {
					arecipes.add(new CachedOreRecipe(worldgen.name, ST.make(worldgen.stone, 1, 0),
							ST.make(worldgen.stone2, 1, 0),
							ST.make((Block) BlocksGT.stoneToNormalOres.get(new ItemStackContainer(worldgen.stone, 1, 0)), 1, worldgen.material.mID),
							4, worldgen.number));
				}
			}
		} else if (unlocalizedName.startsWith("gt.stone.") && unlocalizedName.endsWith(".0")) {
			for (OreLayerWrapper worldgen : OreHelper.mapOreLayerWrapper) {
				if (result.getItem() == Item.getItemFromBlock(worldgen.stone)) {
					arecipes.add(new CachedOreRecipe(worldgen.name, ST.make(worldgen.stone, 1, 0),
							ST.make((Block) BlocksGT.stoneToNormalOres.get(new ItemStackContainer(worldgen.stone, 1, 0)), 1, worldgen.material.mID),
							3, worldgen.number));
				}
			}
			for (Ore2LayerWrapper worldgen : OreHelper.mapOre2LayerWrapper) {
				if ((result.getItem() == Item.getItemFromBlock(worldgen.stone)) || (result.getItem() == Item.getItemFromBlock(worldgen.stone2))) {
					arecipes.add(new CachedOreRecipe(worldgen.name, ST.make(worldgen.stone, 1, 0),
							ST.make(worldgen.stone2, 1, 0),
							ST.make((Block) BlocksGT.stoneToNormalOres.get(new ItemStackContainer(worldgen.stone, 1, 0)), 1, worldgen.material.mID),
							4, worldgen.number));
				}
			}
		} else if (unlocalizedName.equals("tile.bedrock")) {
			for (OreBedrockWrapper worldgen : OreHelper.mapOreBedrockWrapper.values()) {
				arecipes.add(new CachedOreRecipe(worldgen.name + worldgen.material.mNameInternal,
						ST.make((Block) BlocksGT.oreBedrock, 1, worldgen.material.mID),
						ST.make(worldgen.flower, 1, worldgen.flowerMeta), 0, 0));
			}
		} else if (unlocalizedName.equals("tile.hellrock")) {
			AddDimRecipes(CS.DIM_NETHER);
		} else if (unlocalizedName.equals("tile.whiteStone")) {
			AddDimRecipes(CS.DIM_END);
		} else if (unlocalizedName.equals("gt.block.sands.0")) {
			BlockWrapper worldgen = OreHelper.mapBlockWrapper.get("river.magnetite");
			arecipes.add(new CachedOreRecipe(worldgen.name, worldgen.block, 5));
		} else if (unlocalizedName.equals("gt.block.diggable.2")) {
			BlockWrapper worldgen = OreHelper.mapBlockWrapper.get("swamp.turf");
			arecipes.add(new CachedOreRecipe(worldgen.name, worldgen.block, 5));
		} else if (unlocalizedName.startsWith("gt.block.flower")) {
			short meta = (short) result.getItemDamage();
			for (OreBedrockWrapper worldgen : OreHelper.mapOreBedrockWrapper.values()) {
				if (worldgen.flower != null && meta == worldgen.flowerMeta && unlocalizedName.startsWith(worldgen.flower.getUnlocalizedName())) {
					arecipes.add(new CachedOreRecipe(worldgen.name + worldgen.material.mNameInternal,
							ST.make((Block) BlocksGT.oreBedrock, 1, worldgen.material.mID),
							ST.make(worldgen.flower, 1, worldgen.flowerMeta), 0, 0));
				}
			}
		} else
			super.loadCraftingRecipes(result);
	}

	private void AddDimRecipes(int dim) {
		for (OreSmallWrapper worldgen : OreHelper.mapOreSmallWrapper.values()) {
			if (worldgen.getWorlds().indexOf(OreHelper.getWorldNameTranslated(dim)) > -1) {
				arecipes.add(new CachedOreRecipe(worldgen.name,
						ST.make((Block) BlocksGT.oreSmall, 1, worldgen.material.mID), 1));
			}
		}
		for (OreLargeWrapper worldgen : OreHelper.mapOreLargeWrapper.values()) {
			if (worldgen.getWorlds().indexOf(OreHelper.getWorldNameTranslated(dim)) > -1) {
				arecipes.add(new CachedOreRecipe(worldgen.name,
						ST.make((Block) BlocksGT.stoneToNormalOres.get(new ItemStackContainer(Blocks.stone, 1, 0)), 1, worldgen.material.mID),
						ST.make((Block) BlocksGT.stoneToNormalOres.get(new ItemStackContainer(Blocks.stone, 1, 0)), 1, worldgen.mBottom.mID),
						ST.make((Block) BlocksGT.stoneToNormalOres.get(new ItemStackContainer(Blocks.stone, 1, 0)), 1, worldgen.mBetween.mID),
						ST.make((Block) BlocksGT.stoneToNormalOres.get(new ItemStackContainer(Blocks.stone, 1, 0)), 1, worldgen.mSpread.mID), 2));
			}
		}
	}

	@Override
	public void drawBackground(int recipe) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GuiDraw.drawTexturedModalRect(0, 0, 5, 11, 166, 65);
	}

	@Override
	public void drawForeground(int recipe) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(2896);
		drawExtras(recipe);
	}

	@Override
	public void drawExtras(int recipe) {
		CachedOreRecipe crecipe = (CachedOreRecipe) this.arecipes.get(recipe);
		if (crecipe.type == 0) {
			drawBedrockExtras(OreHelper.mapOreBedrockWrapper.get(crecipe.name));
		} else if (crecipe.type == 1) {
			drawSmallExtras(OreHelper.mapOreSmallWrapper.get(crecipe.name));
		} else if (crecipe.type == 2) {
			drawLargeExtras(OreHelper.mapOreLargeWrapper.get(crecipe.name));
		} else if (crecipe.type == 3) {
			drawOreLayerExtras(OreHelper.mapOreLayerWrapper.get(crecipe.number));
		} else if (crecipe.type == 4) {
			drawOre2LayerExtras(OreHelper.mapOre2LayerWrapper.get(crecipe.number));
		} else if (crecipe.type == 5) {
			drawBlockExtras(OreHelper.mapBlockWrapper.get(crecipe.name));
		}
	}

	private void drawBedrockExtras(OreBedrockWrapper oreLayer) {
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.bedrockOre"), 2, 22, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.oreName") + ": " + oreLayer.materialName, 2, 44, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.height") + ": " + "0-6", 2, 56, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.probability") + ": " + "1/" + oreLayer.probability, 2, 68, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.worlds") + ": ", 2, 92, 0x404040, false);
		String worlds = oreLayer.getWorlds();
		if (!drawWorldList(worlds, 30))
			GuiDraw.drawString(oreLayer.getWorlds(), 4, 104, 0x404040, false);
	}

	private void drawSmallExtras(OreSmallWrapper oreLayer) {
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.smallOre"), 2, 22, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.oreName") + ": " + oreLayer.materialName, 2, 44, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.height") + ": " + oreLayer.worldGenHeightRange, 2, 56, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.amount") + ": " + oreLayer.amountPerChunk, 2, 68, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.worlds") + ": ", 2, 92, 0x404040, false);
		String worlds = oreLayer.getWorlds();
		if (!drawWorldList(worlds, 30))
			GuiDraw.drawString(worlds, 4, 104, 0x404040, false);
	}

	private void drawLargeExtras(OreLargeWrapper oreLayer) {
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.largeOre"), 2, 22, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.height") + ": " + oreLayer.worldGenHeightRange, 2, 44, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.top") + ": " + oreLayer.materialName, 2, 56, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.bottom") + ": " + oreLayer.mBottom.getLocal(), 2, 68, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.between") + ": " + oreLayer.mBetween.getLocal(), 2, 80, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.spread") + ": " + oreLayer.mSpread.getLocal(), 2, 92, 0x404040, false);
		String worlds = oreLayer.getWorlds();
		if (worlds.length() > 32) {
			int index = worlds.indexOf(",", 20);
			if (index > -1) {
				GuiDraw.drawString(I18n.format("gt6orehelper.nei.worlds") + ": " + worlds.substring(0, index + 1), 2, 104, 0x404040, false);
				GuiDraw.drawString(worlds.substring(index + 2), 4, 116, 0x404040, false);
				return;
			}
		}
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.worlds") + ": ", 2, 104, 0x404040, false);
		GuiDraw.drawString(worlds, 4, 116, 0x404040, false);
	}

	private void drawOreLayerExtras(OreLayerWrapper oreLayer) {
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.oreLayer"), 2, 22, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.stone") + ": " + oreLayer.stoneMaterialName, 2, 44, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.oreName") + ": " + oreLayer.materialName, 2, 56, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.height") + ": " + oreLayer.worldGenHeightRange, 2, 68, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.chance") + ": " + "1/" + oreLayer.chance, 2, 80, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.biomes") + ": ", 2, 92, 0x404040, false);
		String worlds = oreLayer.getWorlds();
		if (!drawWorldList(worlds, 40))
			GuiDraw.drawString(worlds, 4, 104, 0x404040, false);
	}

	private void drawOre2LayerExtras(Ore2LayerWrapper oreLayer) {
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.oreLayerBetween"), 2, 22, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.stoneTop") + ": " + oreLayer.stoneMaterialName, 2, 44, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.stoneBottom") + ": " + oreLayer.stone2MaterialName, 2, 56, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.oreName") + ": " + oreLayer.materialName, 2, 68, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.height") + ": " + oreLayer.worldGenHeightRange, 2, 80, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.chance") + ": " + "1/" + oreLayer.chance, 2, 92, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.biomes") + ": ", 2, 104, 0x404040, false);
		GuiDraw.drawString(oreLayer.getWorlds(), 4, 116, 0x404040, false);
	}

	private void drawBlockExtras(BlockWrapper oreLayer) {
		GuiDraw.drawString(oreLayer.block.getDisplayName(), 2, 22, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.height") + ": ", 2, 44, 0x404040, false);
		GuiDraw.drawString(oreLayer.worldGenHeightRange, 2, 56, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.biomes") + ": ", 2, 68, 0x404040, false);
		GuiDraw.drawString(oreLayer.biomes, 4, 80, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.worlds") + ": ", 2, 92, 0x404040, false);
		String worlds = oreLayer.getWorlds();
		if (!drawWorldList(worlds, 30))
			GuiDraw.drawString(worlds, 4, 104, 0x404040, false);
	}

	private boolean drawWorldList(String worlds, int length) {
		if (worlds.length() > length + 2) {
			int index = worlds.indexOf(",", length);
			if (index > -1) {
				GuiDraw.drawString(worlds.substring(0, index + 1), 4, 104, 0x404040, false);
				GuiDraw.drawString(worlds.substring(index + 2), 4, 116, 0x404040, false);
				return true;
			}
		}
		return false;
	}
}
