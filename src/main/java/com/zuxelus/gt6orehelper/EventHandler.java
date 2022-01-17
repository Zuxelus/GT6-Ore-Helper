package com.zuxelus.gt6orehelper;

import com.zuxelus.gt6orehelper.journeymap.WaypointHandler;

import codechicken.nei.guihook.GuiContainerManager;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.WorldEvent;

public class EventHandler {
	public final static EventHandler instance = new EventHandler();

	@SubscribeEvent
	public void onWorldUnload(WorldEvent.Load event) {
		GuiContainerManager.addInputHandler(new InputHandler());
	}

	@SubscribeEvent
	public void playerRightClick(PlayerInteractEvent event) {
		if (!GT6OreHelper.useWaypoints || event.isCanceled() || event.action != PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK)
			return;

		WaypointHandler.checkWaypoint(event.world, event.entityPlayer, event.x, event.y, event.z);
	}
}
