package net.teamfruit.mchelishield.gui.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import cpw.mods.fml.client.IModGuiFactory;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;
import net.teamfruit.mchelishield.Config;
import net.teamfruit.mchelishield.Reference;

public class ConfigGuiFactory implements IModGuiFactory {

	public static class ConfigGui extends GuiConfig {

		public ConfigGui(final @Nullable GuiScreen parent) {
			super(parent, getConfigElements(), Reference.MODID, false, false, I18n.format("mchelishield.config.name"));
		}

		@SuppressWarnings({ "rawtypes" })
		private static @Nonnull List<IConfigElement> getConfigElements() {
			final List<IConfigElement> list = new ArrayList<IConfigElement>();

			for (final String cat : Config.getConfig().getCategoryNames()) {
				final ConfigCategory cc = Config.getConfig().getCategory(cat);

				if (cc.isChild())
					continue;

				final ConfigElement ce = new ConfigElement<String>(cc);
				list.add(ce);
			}

			return list;
		}
	}

	@Override
	public void initialize(final @Nullable Minecraft minecraftInstance) {

	}

	@Override
	public @Nullable Class<? extends GuiScreen> mainConfigGuiClass() {
		return ConfigGui.class;
	}

	@Override
	public @Nullable Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
		return null;
	}

	@Override
	public @Nullable RuntimeOptionGuiHandler getHandlerFor(final @Nullable RuntimeOptionCategoryElement element) {
		return null;
	}
}