package com.zuxelus.gt6orehelper;

import com.zuxelus.gt6orehelper.nei.OreHandler;
import com.zuxelus.gt6orehelper.nei.OreHelper;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.nbt.NBTTagCompound;

@Mod(name = GT6OreHelper.NAME, modid = GT6OreHelper.MODID, version = GT6OreHelper.VERSION, dependencies = "required-after:gregapi_post;after:gregtech;required-after:NotEnoughItems")
public class GT6OreHelper {
	public static final String NAME = "GT6 Ore Helper";
	public static final String MODID = "gt6orehelper";
	public static final String VERSION = "@VERSION@";

	@EventHandler
	public void inInit(FMLPreInitializationEvent event) {
		if (event.getSide() == Side.CLIENT) {
			NBTTagCompound tag = new NBTTagCompound();
			tag.setString("handler", OreHandler.HANDLER_ID);
			tag.setString("modName", GT6OreHelper.NAME);
			tag.setString("modId", GT6OreHelper.MODID);
			tag.setString("itemName", "minecraft:gold_ore");
			tag.setInteger("maxRecipesPerPage", 1);
			FMLInterModComms.sendMessage("NotEnoughItems", "registerHandlerInfo", tag);
		}
	}

	@EventHandler
	public void onLoadComplete(FMLLoadCompleteEvent event) {
		if (event.getSide() == Side.CLIENT)
			new OreHelper();
	}
}
