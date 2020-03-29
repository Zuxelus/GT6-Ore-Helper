package com.zuxelus.gt6orehelper;

import com.zuxelus.gt6orehelper.nei.OreHelper;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.relauncher.Side;

@Mod(name = GT6OreHelper.NAME, modid = GT6OreHelper.MODID, version = GT6OreHelper.VERSION, dependencies="required-after:gregapi_post;after:gregtech;required-after:NotEnoughItems") 
public class GT6OreHelper
{
	public static final String NAME = "GT6 Ore Helper";
    public static final String MODID = "gt6orehelper";
    public static final String VERSION = "@VERSION@";
    
    @EventHandler
    public void onLoadComplete(FMLLoadCompleteEvent event) {
        if (event.getSide() == Side.CLIENT)
            new OreHelper();
    }
}
