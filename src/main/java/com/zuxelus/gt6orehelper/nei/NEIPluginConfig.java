package com.zuxelus.gt6orehelper.nei;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import com.zuxelus.gt6orehelper.GT6OreHelper;

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
