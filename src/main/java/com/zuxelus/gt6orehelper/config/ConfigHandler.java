package com.zuxelus.gt6orehelper.config;

import java.io.File;

import com.zuxelus.gt6orehelper.GT6OreHelper;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

public class ConfigHandler {
	public Configuration config;

	public boolean useWaypoints;

	public void init(File configFile) {
		if (config == null)
			config = new Configuration(configFile);
		loadConfig();
	}

	private void loadConfig() {
		try {
			useWaypoints = config.getBoolean("useWaypoints", Configuration.CATEGORY_GENERAL, true, "", "gt6orehelper.config.useWaypoints");
		} catch (Exception e) {
		} finally {
			if (config.hasChanged())
				config.save();
		}
	}

	@SubscribeEvent
	public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.modID.equals(GT6OreHelper.MODID))
			loadConfig();
	}
}
