package net.teamfruit.mchelishield;

import javax.annotation.Nonnull;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.teamfruit.mchelishield.api.ModListShieldEvent;
import net.teamfruit.mchelishield.api.ScreenShotShieldEvent;

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

	@SubscribeEvent
	public void onScreenShotShield(final @Nonnull ScreenShotShieldEvent.Post event) {
		if (Config.getConfig().notifyChat.get())
			ChatBuilder.create("mchelishield.notification.chat.message").useTranslation().chatClient();
		if (Config.getConfig().notifySound.get())
			FMLClientHandler.instance().getClient().getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("mchelishield", "notification.sound"), 1.0F));
	}

	@SubscribeEvent
	public void onModListShield(final @Nonnull ModListShieldEvent.Post event) {
		if (Config.getConfig().notifyChat.get())
			ChatBuilder.create("mchelishield.notification.modlist.message").useTranslation().chatClient();
		if (Config.getConfig().notifySound.get())
			FMLClientHandler.instance().getClient().getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("mchelishield", "notification.sound"), 1.0F));
	}
}
