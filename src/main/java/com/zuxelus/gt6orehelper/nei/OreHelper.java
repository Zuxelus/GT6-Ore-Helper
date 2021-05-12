package com.zuxelus.gt6orehelper.nei;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import gregapi.block.BlockBase;
import gregapi.block.metatype.BlockStones;
import gregapi.data.CS;
import gregapi.data.CS.BlocksGT;
import gregapi.oredict.OreDictMaterial;
import gregapi.worldgen.StoneLayer;
import gregapi.worldgen.StoneLayerOres;
import gregapi.worldgen.WorldgenObject;
import gregapi.worldgen.WorldgenOresBedrock;
import gregapi.worldgen.WorldgenOresLarge;
import gregapi.worldgen.WorldgenOresSmall;
import gregtech.worldgen.WorldgenBlackSand;
import gregtech.worldgen.WorldgenTurf;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class OreHelper {
	public static HashMap<String, OreBedrockWrapper> mapOreBedrockWrapper = new HashMap<String, OreBedrockWrapper>();
	public static HashMap<String, OreSmallWrapper> mapOreSmallWrapper = new HashMap<String, OreSmallWrapper>();
	public static HashMap<String, OreLargeWrapper> mapOreLargeWrapper = new HashMap<String, OreLargeWrapper>();
	public static ArrayList<OreLayerWrapper> mapOreLayerWrapper = new ArrayList<OreLayerWrapper>();
	public static ArrayList<Ore2LayerWrapper> mapOre2LayerWrapper = new ArrayList<Ore2LayerWrapper>();
	public static HashMap<String, BlockWrapper> mapBlockWrapper = new HashMap<String, BlockWrapper>();

	public OreHelper() {
		List<WorldgenObject> gen_list[] = new List[] { CS.GEN_GT, CS.GEN_NETHER, CS.GEN_END, CS.GEN_TWILIGHT,
				CS.GEN_BETWEENLANDS, CS.GEN_MOON, CS.GEN_MARS, CS.GEN_PLANETS, CS.GEN_ASTEROIDS, CS.GEN_AETHER,
				CS.GEN_EREBUS };
		List<WorldgenObject> ore_list[] = new List[] { CS.ORE_NETHER, CS.ORE_END, CS.ORE_TWILIGHT, CS.ORE_BETWEENLANDS,
				CS.ORE_MOON, CS.ORE_MARS, CS.ORE_PLANETS, CS.ORE_ASTEROIDS, CS.ORE_AETHER, CS.ORE_EREBUS };
		int[] dim_list = { CS.DIM_OVERWORLD, CS.DIM_NETHER, CS.DIM_END, CS.DIM_TWILIGHT, CS.DIM_BETWEENLANDS,
				CS.DIM_MOON, CS.DIM_MARS, CS.DIM_PLANETS, CS.DIM_ASTEROIDS, CS.DIM_AETHER, CS.DIM_EREBUS };
		int i = 0;
		for (List<WorldgenObject> list : gen_list) {
			for (WorldgenObject worldgenObject : list) {
				AddWrappers(worldgenObject, dim_list[i]);
			}
			i++;
		}
		i = 0;
		for (List<WorldgenObject> list : ore_list) {
			for (WorldgenObject worldgenObject : list) {
				AddOreLargeWrapper(worldgenObject, dim_list[i + 1]);
			}
			i++;
		}
		for (StoneLayer stoneLayer : StoneLayer.LAYERS) {
			for (StoneLayerOres oreLayer : stoneLayer.mOres) {
				mapOreLayerWrapper.add(new OreLayerWrapper(stoneLayer, oreLayer, mapOreLayerWrapper.size()));
			}
		}
		for (Entry<OreDictMaterial, Map<OreDictMaterial, List<StoneLayerOres>>> entry : StoneLayer.MAP.entrySet()) {
			OreDictMaterial key = entry.getKey();
			Map<OreDictMaterial, List<StoneLayerOres>> value = entry.getValue();
			for (Entry<OreDictMaterial, List<StoneLayerOres>> entry2 : value.entrySet()) {
				OreDictMaterial key2 = entry2.getKey();
				for (StoneLayerOres oreLayer : entry2.getValue()) {
					mapOre2LayerWrapper.add(new Ore2LayerWrapper(key, key2, oreLayer, mapOre2LayerWrapper.size()));
				}
			}
		}
	}

	public void AddWrappers(WorldgenObject worldgenObject, int dim) {
		if (!worldgenObject.mEnabled)
			return;
		if (worldgenObject instanceof WorldgenOresBedrock) {
			if (mapOreBedrockWrapper.containsKey(worldgenObject.mName + ((WorldgenOresBedrock) worldgenObject).mMaterial.mNameInternal)) {
				OreBedrockWrapper wrapper = mapOreBedrockWrapper.get(worldgenObject.mName + ((WorldgenOresBedrock) worldgenObject).mMaterial.mNameInternal);
				wrapper.world.add(getWorldNameTranslated(dim));
			} else
				mapOreBedrockWrapper.put(
						worldgenObject.mName + ((WorldgenOresBedrock) worldgenObject).mMaterial.mNameInternal,
						new OreBedrockWrapper((WorldgenOresBedrock) worldgenObject, dim));
		} else if (worldgenObject instanceof WorldgenOresSmall) {
			if (mapOreSmallWrapper.containsKey(worldgenObject.mName)) {
				OreSmallWrapper wrapper = mapOreSmallWrapper.get(worldgenObject.mName);
				wrapper.world.add(getWorldNameTranslated(dim));
			} else
				mapOreSmallWrapper.put(worldgenObject.mName, new OreSmallWrapper((WorldgenOresSmall) worldgenObject, dim));
		} else if (worldgenObject instanceof WorldgenBlackSand) {
			if (mapBlockWrapper.containsKey(worldgenObject.mName)) {
				BlockWrapper wrapper = mapBlockWrapper.get(worldgenObject.mName);
				wrapper.world.add(getWorldNameTranslated(dim));
			} else
				mapBlockWrapper.put(worldgenObject.mName, new BlockWrapper(worldgenObject.mName, new ItemStack(BlocksGT.Sands, 1, 0) ,
						"48 - 63 (24 - 31 in Twilight)" , "River, Twilight Stream, ...", dim));
		} else if (worldgenObject instanceof WorldgenTurf) {
			if (mapBlockWrapper.containsKey(worldgenObject.mName)) {
				BlockWrapper wrapper = mapBlockWrapper.get(worldgenObject.mName);
				wrapper.world.add(getWorldNameTranslated(dim));
			} else
				mapBlockWrapper.put(worldgenObject.mName, new BlockWrapper(worldgenObject.mName, new ItemStack(BlocksGT.Diggables, 1, 2) ,
						"48 - 63 (24 - 31 in Twilight)" , "Swampland, Twilight Swamp, ...", dim));
		}
	}

	public void AddOreLargeWrapper(WorldgenObject worldgenObject, int dim) {
		if (!worldgenObject.mEnabled)
			return;
		if (worldgenObject instanceof WorldgenOresLarge) {
			if (mapOreLargeWrapper.containsKey(worldgenObject.mName)) {
				OreLargeWrapper wrapper = mapOreLargeWrapper.get(worldgenObject.mName);
				wrapper.world.add(getWorldNameTranslated(dim));
			} else
				mapOreLargeWrapper.put(worldgenObject.mName, new OreLargeWrapper((WorldgenOresLarge) worldgenObject, dim));
		}
	}

	public static String getWorldNameTranslated(int dim) {
		if (dim == CS.DIM_OVERWORLD)
			return I18n.format("gt6orehelper.world.overworld.name");
		if (dim == CS.DIM_NETHER)
			return I18n.format("gt6orehelper.world.nether.name");
		if (dim == CS.DIM_END)
			return I18n.format("gt6orehelper.world.end.name");
		if (dim == CS.DIM_TWILIGHT)
			return I18n.format("gt6orehelper.world.twilight.name");
		if (dim == CS.DIM_BETWEENLANDS)
			return I18n.format("gt6orehelper.world.betweenlands.name");
		if (dim == CS.DIM_MOON)
			return I18n.format("gt6orehelper.world.moon.name");
		if (dim == CS.DIM_MARS)
			return I18n.format("gt6orehelper.world.mars.name");
		if (dim == CS.DIM_PLANETS)
			return I18n.format("gt6orehelper.world.planets.name");
		if (dim == CS.DIM_ASTEROIDS)
			return I18n.format("gt6orehelper.world.asteroids.name");
		if (dim == CS.DIM_AETHER)
			return I18n.format("gt6orehelper.world.aether.name");
		if (dim == CS.DIM_EREBUS)
			return I18n.format("gt6orehelper.world.erebus.name");		
		return "";
	}

	public abstract class Wrapper {
		public String name;
		public OreDictMaterial material;
		public String materialName;
		public ArrayList<String> world;

		public String getWorlds() {
			if ((world == null) || (world.size() == 0))
				return "[]";
			return world.toString().replace("[", "").replace("]", "");
		}
	}

	public class OreBedrockWrapper extends Wrapper {
		public int probability;
		public Block flower;
		public int flowerMeta;
		public boolean indicatorFlowers;
		public boolean indicatorRocks;

		public OreBedrockWrapper(WorldgenOresBedrock worldgen, int dim) {
			this.name = worldgen.mName;
			this.material = worldgen.mMaterial;
			this.materialName = worldgen.mMaterial.getLocal();
			this.probability = worldgen.mProbability;
			this.flower = worldgen.mFlower;
			this.flowerMeta = worldgen.mFlowerMeta;
			this.indicatorFlowers = worldgen.mIndicatorFlowers;
			this.indicatorRocks = worldgen.mIndicatorRocks;
			this.world = new ArrayList<String>();
			world.add(getWorldNameTranslated(dim));
		}
	}

	public class OreSmallWrapper extends Wrapper {
		public String worldGenHeightRange;
		public String amountPerChunk;

		public OreSmallWrapper(WorldgenOresSmall worldgen, int dim) {
			this.name = worldgen.mName;
			this.material = worldgen.mMaterial;
			this.materialName = worldgen.mMaterial.getLocal();
			this.worldGenHeightRange = worldgen.mMinY + "-" + worldgen.mMaxY;
			this.amountPerChunk = "1-" + worldgen.mAmount;
			this.world = new ArrayList<String>();
			world.add(getWorldNameTranslated(dim));
		}
	}

	public class OreLayerWrapper extends Wrapper {
		public int number;
		public String worldGenHeightRange;
		public Block stone;
		public String stoneMaterialName;
		public long chance;

		public OreLayerWrapper(StoneLayer stoneLayer, StoneLayerOres layerOre, int num) {
			this(layerOre, num);
			this.stone = stoneLayer.mStone;
			this.stoneMaterialName = stoneLayer.mMaterial.getLocal();
		}

		public OreLayerWrapper(StoneLayerOres layerOre, int num) {
			this.name = layerOre.mMaterial.mNameLocal;
			this.number = num;
			this.material = layerOre.mMaterial;
			this.materialName = layerOre.mMaterial.getLocal();
			this.worldGenHeightRange = layerOre.mMinY + "-" + layerOre.mMaxY;
			this.chance = CS.U / layerOre.mChance;
			this.world = layerOre.mBiomes;
		}
	}

	public class Ore2LayerWrapper extends OreLayerWrapper {
		public Block stone2;
		public String stone2MaterialName;

		public Ore2LayerWrapper(StoneLayer stoneLayer, StoneLayerOres layerOre, int num) {
			super(stoneLayer, layerOre, num);
		}

		public Ore2LayerWrapper(OreDictMaterial top, OreDictMaterial bottom, StoneLayerOres layerOre, int num) {
			super(layerOre, num);
			this.stone = getBlockGT(top);
			this.stoneMaterialName = top.getLocal();
			this.stone2 = getBlockGT(bottom);
			this.stone2MaterialName = bottom.getLocal();
		}

		private Block getBlockGT(OreDictMaterial material) {
			for (BlockBase stone : BlocksGT.stones)
				if (stone instanceof BlockStones && ((BlockStones) stone).mMaterial == material)
					return stone;
			return Blocks.stone;
		}
	}

	public class OreLargeWrapper extends Wrapper {
		public OreDictMaterial mBottom;
		public OreDictMaterial mBetween;
		public OreDictMaterial mSpread;
		public String worldGenHeightRange;
		public int mWeight;
		public short mDensity;
		public int mDistance;
		public short mSize;
		public boolean indicatorRocks;

		public OreLargeWrapper(WorldgenOresLarge worldgen, int dim) {
			this.name = worldgen.mName;
			this.material = worldgen.mTop;
			this.materialName = worldgen.mTop.getLocal();
			this.mBottom = worldgen.mBottom;
			this.mBetween = worldgen.mBetween;
			this.mSpread = worldgen.mSpread;
			this.worldGenHeightRange = worldgen.mMinY + "-" + worldgen.mMaxY;
			this.mWeight = worldgen.mWeight;
			this.mDensity = worldgen.mDensity;
			this.mDistance = worldgen.mDistance;
			this.mSize = worldgen.mSize;
			this.indicatorRocks = worldgen.mIndicatorRocks;
			this.world = new ArrayList<String>();
			world.add(getWorldNameTranslated(dim));
		}
	}

	public class BlockWrapper extends Wrapper {
		public ItemStack block;
		public String worldGenHeightRange;
		public String biomes;

		public BlockWrapper(String mName, ItemStack mStack, String worldGenHeightRange, String mBiomes, int dim) {
			this.name = mName;
			this.block = mStack;
			this.worldGenHeightRange = worldGenHeightRange;
			this.biomes = mBiomes;
			this.world = new ArrayList<String>();
			world.add(getWorldNameTranslated(dim));
		}
	}
}
