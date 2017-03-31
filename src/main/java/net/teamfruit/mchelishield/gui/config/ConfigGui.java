package net.teamfruit.mchelishield.gui.config;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;
import net.teamfruit.mchelishield.Config;
import net.teamfruit.mchelishield.Reference;

public class ConfigGui extends GuiConfig {

	public ConfigGui(final @Nullable GuiScreen parent) {
		super(parent, getConfigElements(), Reference.MODID, false, false, GuiConfig.getAbridgedConfigPath(Config.getConfig().getFilePath()));
	}

	@SuppressWarnings("rawtypes")
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