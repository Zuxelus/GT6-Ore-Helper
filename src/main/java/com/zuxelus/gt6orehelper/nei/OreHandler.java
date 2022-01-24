package com.zuxelus.gt6orehelper.nei;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import com.zuxelus.gt6orehelper.nei.OreHelper.BlockWrapper;
import com.zuxelus.gt6orehelper.nei.OreHelper.ColtanWrapper;
import com.zuxelus.gt6orehelper.nei.OreHelper.Ore2LayerWrapper;
import com.zuxelus.gt6orehelper.nei.OreHelper.OreBedrockWrapper;
import com.zuxelus.gt6orehelper.nei.OreHelper.OreLargeWrapper;
import com.zuxelus.gt6orehelper.nei.OreHelper.OreLayerWrapper;
import com.zuxelus.gt6orehelper.nei.OreHelper.OreSmallWrapper;
import com.zuxelus.gt6orehelper.nei.OreHelper.OreVanillaWrapper;
import com.zuxelus.gt6orehelper.nei.OreHelper.SpringWrapper;

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
	public final static String HANDLER_ID = "gt6orehelper.worldgen";

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
			if (ore != null)
				input.add(new PositionedStack(ore, 148, 4));
		}

		public CachedOreRecipe(String name, ItemStack ore, ItemStack flower, int type, int num) {
			this(name, flower, type);
			this.number = num;
			if (ore != null)
				input.add(new PositionedStack(ore, 126, 4));
		}

		public CachedOreRecipe(String name, ItemStack ore, ItemStack top, ItemStack bottom, int type, int num) {
			this(name, top, bottom, type, num);
			input.add(new PositionedStack(ore, 104, 4));
		}

		public CachedOreRecipe(String name, ItemStack top, ItemStack bottom, ItemStack between, ItemStack spread, int type) {
			this(name, spread, type);
			input.add(new PositionedStack(top, 82, 4));
			input.add(new PositionedStack(bottom, 104, 4));
			input.add(new PositionedStack(between, 126, 4));
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
	public String getOverlayIdentifier() {
		return HANDLER_ID;
	}

	@Override
	public String getGuiTexture() {
		return null;
	}

	@Override
	public void loadCraftingRecipes(ItemStack result) {
		String unlocalizedName = result.getUnlocalizedName();
		//System.out.println("Name: " + unlocalizedName); // remove
		if (unlocalizedName.startsWith("oredict.ore") || unlocalizedName.startsWith("oredict.oreSmall")) {
			short meta = (short) result.getItemDamage();
			for (OreBedrockWrapper worldgen : OreHelper.mapOreBedrockWrapper.values())
				if (meta == worldgen.material.mID) {
					arecipes.add(new CachedOreRecipe(worldgen.name + worldgen.material.mNameInternal,
							ST.make((Block) BlocksGT.oreBedrock, 1, worldgen.material.mID),
							ST.make(worldgen.flower, 1, worldgen.flowerMeta), 0, 0));
				}
			for (OreSmallWrapper worldgen : OreHelper.mapOreSmallWrapper.values())
				if (meta == worldgen.material.mID) {
					arecipes.add(new CachedOreRecipe(worldgen.name,
							ST.make((Block) BlocksGT.oreSmall, 1, worldgen.material.mID), 1));
				}
			for (OreLargeWrapper worldgen : OreHelper.mapOreLargeWrapper.values())
				if ((meta == worldgen.material.mID) || (meta == worldgen.mBottom.mID) || (meta == worldgen.mBetween.mID) || (meta == worldgen.mSpread.mID)) {
					arecipes.add(new CachedOreRecipe(worldgen.name,
							ST.make((Block) BlocksGT.stoneToNormalOres.get(new ItemStackContainer(Blocks.stone, 1, 0)), 1, worldgen.material.mID),
							ST.make((Block) BlocksGT.stoneToNormalOres.get(new ItemStackContainer(Blocks.stone, 1, 0)), 1, worldgen.mBottom.mID),
							ST.make((Block) BlocksGT.stoneToNormalOres.get(new ItemStackContainer(Blocks.stone, 1, 0)), 1, worldgen.mBetween.mID),
							ST.make((Block) BlocksGT.stoneToNormalOres.get(new ItemStackContainer(Blocks.stone, 1, 0)), 1, worldgen.mSpread.mID), 2));
				}
			for (OreLayerWrapper worldgen : OreHelper.mapOreLayerWrapper)
				if (meta == worldgen.material.mID) {
					arecipes.add(new CachedOreRecipe(worldgen.name, ST.make(worldgen.stone, 1, 0),
							ST.make((Block) BlocksGT.stoneToNormalOres.get(new ItemStackContainer(worldgen.stone, 1, 0)), 1, worldgen.material.mID),
							3, worldgen.number));
				}
			for (Ore2LayerWrapper worldgen : OreHelper.mapOre2LayerWrapper)
				if (meta == worldgen.material.mID) {
					arecipes.add(new CachedOreRecipe(worldgen.name, ST.make(worldgen.stone, 1, 0),
							ST.make(worldgen.stone2, 1, 0),
							ST.make((Block) BlocksGT.stoneToNormalOres.get(new ItemStackContainer(worldgen.stone, 1, 0)), 1, worldgen.material.mID),
							4, worldgen.number));
				}
			for (OreVanillaWrapper worldgen : OreHelper.mapOreVanillaWrapper.values())
				if (worldgen.material != null && meta == worldgen.material.mID)
					arecipes.add(new CachedOreRecipe(worldgen.name, worldgen.block, 6));
			if (OreHelper.coltanWrapper != null && meta == OreHelper.coltanWrapper.material.mID)
				arecipes.add(new CachedOreRecipe(OreHelper.coltanWrapper.name, result, 8));
		} else if (unlocalizedName.startsWith("gt.stone.") && unlocalizedName.endsWith(".0")) {
			for (OreLayerWrapper worldgen : OreHelper.mapOreLayerWrapper)
				if (result.getItem() == Item.getItemFromBlock(worldgen.stone)) {
					arecipes.add(new CachedOreRecipe(worldgen.name, ST.make(worldgen.stone, 1, 0),
							ST.make((Block) BlocksGT.stoneToNormalOres.get(new ItemStackContainer(worldgen.stone, 1, 0)), 1, worldgen.material.mID),
							3, worldgen.number));
				}
			for (Ore2LayerWrapper worldgen : OreHelper.mapOre2LayerWrapper)
				if ((result.getItem() == Item.getItemFromBlock(worldgen.stone)) || (result.getItem() == Item.getItemFromBlock(worldgen.stone2))) {
					arecipes.add(new CachedOreRecipe(worldgen.name, ST.make(worldgen.stone, 1, 0),
							ST.make(worldgen.stone2, 1, 0),
							ST.make((Block) BlocksGT.stoneToNormalOres.get(new ItemStackContainer(worldgen.stone, 1, 0)), 1, worldgen.material.mID),
							4, worldgen.number));
				}
		} else if (unlocalizedName.equals("tile.bedrock")) {
			for (OreBedrockWrapper worldgen : OreHelper.mapOreBedrockWrapper.values())
				arecipes.add(new CachedOreRecipe(worldgen.name + worldgen.material.mNameInternal,
						ST.make((Block) BlocksGT.oreBedrock, 1, worldgen.material.mID),
						ST.make(worldgen.flower, 1, worldgen.flowerMeta), 0, 0));
		} else if (unlocalizedName.equals("tile.hellrock")) {
			AddDimRecipes(CS.DIM_NETHER);
		} else if (unlocalizedName.equals("tile.whiteStone")) {
			AddDimRecipes(CS.DIM_END);
		} else if (unlocalizedName.equals("tile.limestone")) {
			AddDimRecipes(CS.DIM_ATUM);
		} else if (unlocalizedName.equals("tile.thebetweenlands.betweenstone")) {
			AddDimRecipes(CS.DIM_BETWEENLANDS);
		} else if (unlocalizedName.equals("tile.erebus.umberstone_0")) {
			AddDimRecipes(CS.DIM_EREBUS);
		} else if (unlocalizedName.equals("tile.tropicraft:chunk")) {
			AddDimRecipes(CS.DIM_TROPICS);
		} else if (unlocalizedName.equals("tile.blockCavenium")) {
			AddDimRecipes(CS.DIM_CW2_Caveland);
		} else if (unlocalizedName.equals("tile.holystone1")) {
			AddDimRecipes(CS.DIM_AETHER);
		} else if (OreHelper.mapStackWrapper.containsKey(unlocalizedName)) {
			BlockWrapper worldgen = OreHelper.mapBlockWrapper.get(OreHelper.mapStackWrapper.get(unlocalizedName));
			arecipes.add(new CachedOreRecipe(worldgen.name, worldgen.block, 5));
		} else if (unlocalizedName.startsWith("gt.block.flower")) {
			short meta = (short) result.getItemDamage();
			for (OreBedrockWrapper worldgen : OreHelper.mapOreBedrockWrapper.values())
				if (worldgen.flower != null && meta == worldgen.flowerMeta && unlocalizedName.startsWith(worldgen.flower.getUnlocalizedName())) {
					arecipes.add(new CachedOreRecipe(worldgen.name + worldgen.material.mNameInternal,
							ST.make((Block) BlocksGT.oreBedrock, 1, worldgen.material.mID),
							ST.make(worldgen.flower, 1, worldgen.flowerMeta), 0, 0));
				}
		} else if (unlocalizedName.startsWith("fluid.")) {
			for (SpringWrapper worldgen : OreHelper.mapSpringWrapper.values())
				if (unlocalizedName.equals(worldgen.springFluid.getUnlocalizedName()))
					arecipes.add(new CachedOreRecipe(worldgen.name, worldgen.block, 7));
		} else
			super.loadCraftingRecipes(result);
	}

	private void AddDimRecipes(int dim) {
		String dimName = OreHelper.getWorldNameTranslated(dim);
		for (OreBedrockWrapper worldgen : OreHelper.mapOreBedrockWrapper.values())
			if (worldgen.getWorlds().indexOf(dimName) > -1)
				arecipes.add(new CachedOreRecipe(worldgen.name + worldgen.material.mNameInternal,
						ST.make((Block) BlocksGT.oreBedrock, 1, worldgen.material.mID),
						ST.make(worldgen.flower, 1, worldgen.flowerMeta), 0, 0));
		for (OreSmallWrapper worldgen : OreHelper.mapOreSmallWrapper.values())
			if (worldgen.getWorlds().indexOf(dimName) > -1)
				arecipes.add(new CachedOreRecipe(worldgen.name,
						ST.make((Block) BlocksGT.oreSmall, 1, worldgen.material.mID), 1));
		for (OreLargeWrapper worldgen : OreHelper.mapOreLargeWrapper.values())
			if (worldgen.getWorlds().indexOf(dimName) > -1)
				arecipes.add(new CachedOreRecipe(worldgen.name,
						ST.make((Block) BlocksGT.stoneToNormalOres.get(new ItemStackContainer(Blocks.stone, 1, 0)), 1, worldgen.material.mID),
						ST.make((Block) BlocksGT.stoneToNormalOres.get(new ItemStackContainer(Blocks.stone, 1, 0)), 1, worldgen.mBottom.mID),
						ST.make((Block) BlocksGT.stoneToNormalOres.get(new ItemStackContainer(Blocks.stone, 1, 0)), 1, worldgen.mBetween.mID),
						ST.make((Block) BlocksGT.stoneToNormalOres.get(new ItemStackContainer(Blocks.stone, 1, 0)), 1, worldgen.mSpread.mID), 2));
		for (SpringWrapper worldgen : OreHelper.mapSpringWrapper.values())
			if (worldgen.getWorlds().indexOf(dimName) > -1)
				arecipes.add(new CachedOreRecipe(worldgen.name, worldgen.block, 7));
	}

	@Override
	public void drawBackground(int recipe) { }

	@Override
	public void drawForeground(int recipe) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(2896);
		drawExtras_(recipe);
	}

	public void drawExtras_(int recipe) {
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
		} else if (crecipe.type == 6) {
			drawVanillaExtras(OreHelper.mapOreVanillaWrapper.get(crecipe.name));
		} else if (crecipe.type == 7) {
			drawSpringExtras(OreHelper.mapSpringWrapper.get(crecipe.name));
		} else if (crecipe.type == 8) {
			drawColtanExtras(OreHelper.coltanWrapper);
		}
	}

	private void drawBedrockExtras(OreBedrockWrapper oreLayer) {
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.bedrockOre"), 4, 10, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.oreName") + ": " + oreLayer.materialName, 2, 32, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.height") + ": 0-6", 2, 44, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.probability") + ": 1/" + oreLayer.probability, 2, 56, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.worlds") + ": ", 2, 80, 0x404040, false);
		String worlds = oreLayer.getWorlds();
		if (!drawWorldList(worlds, 28))
			GuiDraw.drawString(oreLayer.getWorlds(), 3, 92, 0x404040, false);
	}

	private void drawSmallExtras(OreSmallWrapper oreLayer) {
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.smallOre"), 4, 10, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.oreName") + ": " + oreLayer.materialName, 2, 32, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.height") + ": " + oreLayer.worldGenHeightRange, 2, 44, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.amount") + ": " + oreLayer.amountPerChunk, 2, 56, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.worlds") + ": ", 2, 80, 0x404040, false);
		String worlds = oreLayer.getWorlds();
		if (!drawWorldList(worlds, 28))
			GuiDraw.drawString(worlds, 3, 92, 0x404040, false);
	}

	private void drawVanillaExtras(OreVanillaWrapper oreLayer) {
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.vanillaOre"), 4, 10, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.oreName") + ": " + oreLayer.materialName, 2, 32, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.height") + ": " + oreLayer.worldGenHeightRange, 2, 44, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.amount") + ": " + oreLayer.amount, 2, 56, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.size") + ": " + oreLayer.size, 2, 68, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.probability") + ": 1/" + oreLayer.probability, 2, 80, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.worlds") + ": ", 2, 92, 0x404040, false);
		String worlds = oreLayer.getWorlds();
		if (!drawWorldList(worlds, 28))
			GuiDraw.drawString(worlds, 3, 104, 0x404040, false);
	}

	private void drawLargeExtras(OreLargeWrapper oreLayer) {
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.largeOre"), 4, 10, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.height") + ": " + oreLayer.worldGenHeightRange, 2, 32, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.top") + ": " + oreLayer.materialName, 2, 44, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.bottom") + ": " + oreLayer.mBottom.getLocal(), 2, 56, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.between") + ": " + oreLayer.mBetween.getLocal(), 2, 68, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.spread") + ": " + oreLayer.mSpread.getLocal(), 2, 80, 0x404040, false);
		String worlds = oreLayer.getWorlds();
		if (worlds.length() > 32) {
			int index = worlds.indexOf(",", 20);
			if (index > -1) {
				GuiDraw.drawString(I18n.format("gt6orehelper.nei.worlds") + ": " + worlds.substring(0, index + 1), 2, 92, 0x404040, false);
				GuiDraw.drawString(worlds.substring(index + 2), 4, 104, 0x404040, false);
				return;
			}
		}
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.worlds") + ": ", 2, 92, 0x404040, false);
		GuiDraw.drawString(worlds, 3, 104, 0x404040, false);
	}

	private void drawOreLayerExtras(OreLayerWrapper oreLayer) {
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.oreLayer"), 4, 10, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.stone") + ": " + oreLayer.stoneMaterialName, 2, 32, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.oreName") + ": " + oreLayer.materialName, 2, 44, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.height") + ": " + oreLayer.worldGenHeightRange, 2, 56, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.chance") + ": " + "1/" + oreLayer.chance, 2, 68, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.biomes") + ": ", 2, 80, 0x404040, false);
		String worlds = oreLayer.getWorlds();
		if (!drawWorldList(worlds, 40))
			GuiDraw.drawString(worlds, 3, 92, 0x404040, false);
	}

	private void drawOre2LayerExtras(Ore2LayerWrapper oreLayer) {
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.oreLayerBetween"), 4, 10, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.stoneTop") + ": " + oreLayer.stoneMaterialName, 2, 44, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.stoneBottom") + ": " + oreLayer.stone2MaterialName, 2, 56, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.oreName") + ": " + oreLayer.materialName, 2, 68, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.height") + ": " + oreLayer.worldGenHeightRange, 2, 80, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.chance") + ": " + "1/" + oreLayer.chance, 2, 92, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.biomes") + ": ", 2, 104, 0x404040, false);
		GuiDraw.drawString(oreLayer.getWorlds(), 4, 116, 0x404040, false);
	}

	private void drawBlockExtras(BlockWrapper oreLayer) {
		GuiDraw.drawString(oreLayer.block.getDisplayName(), 4, 10, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.height") + ": ", 2, 20, 0x404040, false);
		GuiDraw.drawString(oreLayer.worldGenHeightRange, 2, 32, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.biomes") + ": ", 2, 44, 0x404040, false);
		GuiDraw.drawString(oreLayer.biomes, 4, 56, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.worlds") + ": ", 2, 68, 0x404040, false);
		String worlds = oreLayer.getWorlds();
		if (!drawWorldList(worlds, 28))
			GuiDraw.drawString(worlds, 3, 80, 0x404040, false);
	}

	private void drawSpringExtras(SpringWrapper oreLayer) {
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.fluidSpring"), 4, 10, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.fluidName") + ": " + oreLayer.springFluid.getLocalizedName(), 2, 32, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.amount") + ": " + oreLayer.springFluid.amount + "L", 2, 44, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.probability") + ": 1/" + oreLayer.probability, 2, 68, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.worlds") + ": ", 2, 92, 0x404040, false);
		String worlds = oreLayer.getWorlds();
		if (!drawWorldList(worlds, 28))
			GuiDraw.drawString(worlds, 3, 104, 0x404040, false);
	}

	private void drawColtanExtras(ColtanWrapper oreLayer) {
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.specialLayer"), 4, 10, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.oreName") + ": " + oreLayer.materialName, 2, 32, 0x404040, false);
		GuiDraw.drawString(I18n.format("gt6orehelper.nei.worlds") + ": ", 2, 92, 0x404040, false);
		String worlds = oreLayer.getWorlds();
		if (!drawWorldList(worlds, 28))
			GuiDraw.drawString(worlds, 3, 104, 0x404040, false);
	}

	private boolean drawWorldList(String worlds, int length) {
		if (worlds.length() > 2 * length + 2) {
			int index = worlds.indexOf(",", length);
			int index2 = worlds.indexOf(",", 2 * length);
			if (index > -1 && index2 > -1) {
				GuiDraw.drawString(worlds.substring(0, index + 1), 3, 92, 0x404040, false);
				GuiDraw.drawString(worlds.substring(index + 2, index2 + 1), 3, 104, 0x404040, false);
				GuiDraw.drawString(worlds.substring(index2 + 2), 3, 116, 0x404040, false);
				return true;
			}
		}
		if (worlds.length() > length + 2) {
			int index = worlds.indexOf(",", length);
			if (index > -1) {
				GuiDraw.drawString(worlds.substring(0, index + 1), 3, 92, 0x404040, false);
				GuiDraw.drawString(worlds.substring(index + 2), 3, 104, 0x404040, false);
				return true;
			}
		}
		return false;
	}
}
