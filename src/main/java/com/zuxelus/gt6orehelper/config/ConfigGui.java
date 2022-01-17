package com.zuxelus.gt6orehelper.config;

import com.zuxelus.gt6orehelper.GT6OreHelper;

import cpw.mods.fml.client.config.GuiConfig;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

public class ConfigGui extends GuiConfig {

	public ConfigGui(GuiScreen parent) {
		super(parent, new ConfigElement(GT6OreHelper.config.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
				GT6OreHelper.MODID, false, false,GuiConfig.getAbridgedConfigPath(GT6OreHelper.config.config.toString()));
	}
}