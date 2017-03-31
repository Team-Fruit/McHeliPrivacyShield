package net.teamfruit.mchelishield;

import javax.annotation.Nonnull;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.MinecraftForge;

public class CoreHandler {
	public final @Nonnull Config configHandler = Config.getConfig();

	public void init() {
		FMLCommonHandler.instance().bus().register(this);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onConfigChanged(final @Nonnull ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
		this.configHandler.onConfigChanged(eventArgs);
	}
}
