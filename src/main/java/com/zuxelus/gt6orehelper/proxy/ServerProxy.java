package com.zuxelus.gt6orehelper.proxy;

import com.zuxelus.gt6orehelper.GT6OreHelper;
import com.zuxelus.gt6orehelper.config.ConfigHandler;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ServerProxy {

	public void registerEventHandlers() {}

	public void loadConfig(FMLPreInitializationEvent event) {
		GT6OreHelper.config = new ConfigHandler();
		GT6OreHelper.config.init(event.getSuggestedConfigurationFile());
	}
}