package com.zuxelus.gt6orehelper.nei;

import com.zuxelus.gt6orehelper.GT6OreHelper;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;

public class NEIPluginConfig implements IConfigureNEI {

	@Override
	public String getName() {
		return GT6OreHelper.NAME + " NEI plugin";
	}

	@Override
	public String getVersion() {
		return GT6OreHelper.VERSION;
	}

	@Override
	public void loadConfig() {
		API.registerRecipeHandler(new OreHandler());
	}
}
