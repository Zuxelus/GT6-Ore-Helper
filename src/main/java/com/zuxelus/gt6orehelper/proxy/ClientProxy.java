package com.zuxelus.gt6orehelper.proxy;

import com.zuxelus.gt6orehelper.EventHandler;
import com.zuxelus.gt6orehelper.GT6OreHelper;
import com.zuxelus.gt6orehelper.config.ConfigHandler;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends ServerProxy {

	@Override
	public void registerEventHandlers() {
		MinecraftForge.EVENT_BUS.register(EventHandler.instance);
	}

	@Override
	public void loadConfig(FMLPreInitializationEvent event) {
		GT6OreHelper.config = new ConfigHandler();
		MinecraftForge.EVENT_BUS.register(GT6OreHelper.config);
		GT6OreHelper.config.init(event.getSuggestedConfigurationFile());
	}
}