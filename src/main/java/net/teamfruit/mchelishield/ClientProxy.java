package net.teamfruit.mchelishield;

import javax.annotation.Nonnull;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.teamfruit.mchelishield.api.ModListShieldRegistery;
import net.teamfruit.mchelishield.api.ScreenShotShieldRegistery;
import net.teamfruit.mchelishield.shields.DefaultMLShield;
import net.teamfruit.mchelishield.shields.DefaultSSShield;
import net.teamfruit.mchelishield.shields.FileMLShield;
import net.teamfruit.mchelishield.shields.FileSSShield;

public class ClientProxy extends CommonProxy {
	@Override
	public void preInit(final @Nonnull FMLPreInitializationEvent event) {
		super.preInit(event);
	}

	@Override
	public void init(final @Nonnull FMLInitializationEvent event) {
		super.init(event);

		new CoreHandler().init();

		ScreenShotShieldRegistery.register(new DefaultSSShield());
		ScreenShotShieldRegistery.register(new FileSSShield());
		ModListShieldRegistery.register(new DefaultMLShield());
		ModListShieldRegistery.register(new FileMLShield());
	}

	@Override
	public void postInit(final @Nonnull FMLPostInitializationEvent event) {
		super.postInit(event);
	}
}