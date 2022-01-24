package com.zuxelus.gt6orehelper.journeymap;

import java.awt.Color;

import gregapi.block.misc.BlockBaseFlower;
import gregapi.block.prefixblock.PrefixBlock;
import gregapi.oredict.OreDictMaterial;
import gregtech.tileentity.misc.MultiTileEntityFluidSpring;
import gregtech.tileentity.placeables.MultiTileEntityRock;
import journeymap.client.forge.helper.ForgeHelper;
import journeymap.client.model.Waypoint;
import journeymap.client.waypoint.WaypointStore;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

public class WaypointHandler {
	
	public static void checkWaypoint(World world, EntityPlayer player, int x, int y, int z) {
		if (world.isRemote)
			return;

		ItemStack tool = player.getHeldItem();
		if (tool == null || !tool.getUnlocalizedName().equals("gt.metatool.01.62"))
			if (!player.isSneaking())
				return;

		TileEntity te = world.getTileEntity(x, y, z);
		if (te instanceof MultiTileEntityRock) {
			ItemStack stack = ((MultiTileEntityRock) te).mRock;
			if (stack != null)
				if (stack.getDisplayName().indexOf(" bearing Rock") != -1 || stack.getDisplayName().indexOf(" Ore") != -1)
					WaypointHandler.addWaypoint(player, te, stack.getItemDamage(), Color.blue);
			return;
		}
		if (te instanceof MultiTileEntityFluidSpring) {
			FluidStack stack = ((MultiTileEntityFluidSpring) te).mFluid;
			if (stack != null && stack.getFluid() != null)
				WaypointHandler.addWaypoint(player, te, stack.getFluid().getLocalizedName(), Color.blue);
			return;
		}
		Block block = world.getBlock(x, y, z);
		if (block instanceof PrefixBlock) {
			ItemStack stack = ((PrefixBlock) block).getItemStackFromBlock(world, x, y, z, gregapi.data.CS.SIDE_INVALID);
			if (stack != null && stack.getUnlocalizedName().startsWith("oredict.ore") && !stack.getUnlocalizedName().startsWith("oredict.oreSmall"))
				if (block.getUnlocalizedName().endsWith("bedrock"))
					WaypointHandler.addWaypoint(player, te, stack.getItemDamage(), Color.black);
				else
					WaypointHandler.addWaypoint(player, te, stack.getItemDamage(), Color.blue);
			return;
		}
		if (block instanceof BlockBaseFlower) {
			String name = "Bedrock";
			if (block.getUnlocalizedName().equals("gt.block.flower.a")) {
				switch(world.getBlockMetadata(x, y, z)) {
					case 0: name = "Bedrock Gold"; break;
					case 1: name = "Bedrock Silver"; break;
					case 2: name = "Bedrock Copper"; break;
					case 3: name = "Bedrock Zinc"; break;
					case 4: name = "Bedrock Nickel"; break;
					case 5: name = "Bedrock Uranium"; break;
					case 6: name = "Bedrock Cooperite"; break;
					case 8: name = "Bedrock Hexorium"; break;
				}
			} else if(block.getUnlocalizedName().equals("gt.block.flower.b")) {
				switch(world.getBlockMetadata(x, y, z)) {
					case 0: name = "Bedrock Arsenic"; break;
					case 1: name = "Bedrock Antimony"; break;
					case 2: name = "Bedrock Gold"; break;
					case 3: name = "Bedrock Copper"; break;
					case 4: name = "Bedrock Redstone"; break;
					case 5: name = "Bedrock Uranium"; break;
					case 6: name = "Bedrock Diamond"; break;
					case 7: name = "Bedrock Tungsten"; break;
				}
			}
			WaypointHandler.addWaypoint(player, x, y, z, name, Color.black);
		}
	}

	public static void addWaypoint(EntityPlayer player, TileEntity te, int id, Color color) {
		addWaypoint(player, te, OreDictMaterial.get(id).mNameLocal, color);
	}

	public static void addWaypoint(EntityPlayer player, TileEntity te, String name, Color color) {
		addWaypoint(player, te.xCoord, te.yCoord, te.zCoord, name, color);
	}

	public static void addWaypoint(EntityPlayer player, int x, int y, int z, String name, Color color) {
		Waypoint waypoint = new Waypoint(name, x, y, z, color, Waypoint.Type.Normal, ForgeHelper.INSTANCE.getPlayerDimension());
		waypoint.setEnable(false);
		WaypointStore.instance().add(waypoint);
		player.addChatMessage(new ChatComponentTranslation("gt6orehelper.newWaypoint", name));
	}
}
