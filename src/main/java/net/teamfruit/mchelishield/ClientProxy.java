package net.teamfruit.mchelishield;

import javax.annotation.Nonnull;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
	@Override
	public void preInit(final @Nonnull FMLPreInitializationEvent event) {
		super.preInit(event);
	}

	@Override
	public void init(final @Nonnull FMLInitializationEvent event) {
		super.init(event);
	}

	@Override
	public void postInit(final @Nonnull FMLPostInitializationEvent event) {
		super.postInit(event);
	}
}