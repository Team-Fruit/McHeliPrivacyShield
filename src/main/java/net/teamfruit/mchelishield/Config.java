package net.teamfruit.mchelishield;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.GuiConfigEntries;
import cpw.mods.fml.client.config.GuiConfigEntries.SelectValueEntry;
import cpw.mods.fml.client.config.IConfigElement;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.teamfruit.mchelishield.api.ModListShield;
import net.teamfruit.mchelishield.api.ModListShieldRegistery;
import net.teamfruit.mchelishield.api.ScreenShotShieldRegistery;
import net.teamfruit.mchelishield.api.ScrennShotShield;

public final class Config extends Configuration {
	private static @Nullable Config instance;

	public static @Nonnull Config getConfig() {
		if (instance!=null)
			return instance;
		throw new IllegalStateException("config not initialized");
	}

	public static void init(final @Nonnull File cfgFile) {
		instance = new Config(cfgFile);
	}

	private final @Nonnull File configFile;
	private final @Nonnull Set<IReloadableConfig> configs = Sets.newHashSet();

	public static interface IReloadableConfig {
		void reload();
	}

	public @Nonnull <T extends IReloadableConfig> T registerReload(final @Nonnull T config) {
		this.configs.add(config);
		return config;
	}

	public void reload() {
		for (final IReloadableConfig config : this.configs)
			config.reload();
	}

	{
		getCategory("ScreenShotShield").setLanguageKey("mchelishield.config.ssshield").setComment("Action corresponding to screen shot request");
	}
	public final @Nonnull ConfigProperty<String> ssmode = propertyString(get("ScreenShotShield", "Mode", "none", "select the behavior when the server requests a screenshot").setLanguageKey("mchelishield.config.ssshield.mode").setConfigEntryClass(SelectSSShieldEntry.class));
	public final @Nonnull ConfigProperty<String> ssfile = propertyString(get("ScreenShotShield", "ImageLocation", "", "File Mode: specify the path of the image to send instead of the screenshot").setLanguageKey("mchelishield.config.ssshield.file"));

	{
		getCategory("ModListShield").setLanguageKey("mchelishield.config.mlshield").setComment("Action corresponding to mod list request");
		;
	}
	public final @Nonnull ConfigProperty<String> mlmode = propertyString(get("ModListShield", "Mode", "none", "select the behavior when the server requests a mod list").setLanguageKey("mchelishield.config.mlshield.mode").setConfigEntryClass(SelectMLShieldEntry.class));
	public final @Nonnull ConfigProperty<String> mlfile = propertyString(get("ModListShield", "TextLocation", "", "File Mode: specify the path of the text to send instead of the mod list").setLanguageKey("mchelishield.config.mlshield.file"));

	{
		getCategory("Notification").setLanguageKey("mchelishield.config.notification").setComment("Server requesting packet notifications");
	}
	public final @Nonnull ConfigProperty<Boolean> notifyChat = propertyBoolean(get("Notification", "Chat", false, "notify by chat").setLanguageKey("mchelishield.config.notification.chat"));
	public final @Nonnull ConfigProperty<Boolean> notifyOverlay = propertyBoolean(get("Notification", "Overlay", false, "notify by overlay text").setLanguageKey("mchelishield.config.notification.overlay"));
	public final @Nonnull ConfigProperty<Boolean> notifyEffect = propertyBoolean(get("Notification", "Effect", false, "notify by effect").setLanguageKey("mchelishield.config.notification.effect"));
	public final @Nonnull ConfigProperty<Boolean> notifySound = propertyBoolean(get("Notification", "Sound", false, "notify by sound").setLanguageKey("mchelishield.config.notification.sound"));

	public static class SelectSSShieldEntry extends SelectValueEntry {
		public SelectSSShieldEntry(final GuiConfig owningScreen, final GuiConfigEntries owningEntryList, final IConfigElement<String> configElement) {
			super(owningScreen, owningEntryList, configElement, getSelectable());
		}

		private static @Nonnull Map<Object, String> getSelectable() {
			final Map<String, ScrennShotShield> listeners = ScreenShotShieldRegistery.getListeners();
			final Map<Object, String> modes = Maps.newHashMap();
			for (final Iterator<Entry<String, ScrennShotShield>> itr = listeners.entrySet().iterator(); itr.hasNext();) {
				final Entry<String, ScrennShotShield> entry = itr.next();
				modes.put(entry.getKey(), entry.getKey());
			}
			return modes;
		}
	}

	public static class SelectMLShieldEntry extends SelectValueEntry {
		public SelectMLShieldEntry(final GuiConfig owningScreen, final GuiConfigEntries owningEntryList, final IConfigElement<String> configElement) {
			super(owningScreen, owningEntryList, configElement, getSelectable());
		}

		private static @Nonnull Map<Object, String> getSelectable() {
			final Map<String, ModListShield> listeners = ModListShieldRegistery.getListeners();
			final Map<Object, String> modes = Maps.newHashMap();
			for (final Iterator<Entry<String, ModListShield>> itr = listeners.entrySet().iterator(); itr.hasNext();) {
				final Entry<String, ModListShield> entry = itr.next();
				modes.put(entry.getKey(), entry.getKey());
			}
			return modes;
		}
	}

	private Config(final @Nonnull File configFile) {
		super(configFile, "1.0.0", true);
		this.configFile = configFile;
	}

	@Override
	public void save() {
		if (hasChanged())
			super.save();
	}

	@CoreEvent
	public void onConfigChanged(final @Nonnull ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
		if (StringUtils.equals(eventArgs.modID, Reference.MODID)) {
			save();
			reload();
		}
	}

	public @Nonnull String getFilePath() {
		return this.configFile.getPath();
	}

	public @Nonnull ConfigProperty<String> propertyString(final @Nonnull Property property) {
		return registerReload(ConfigProperty.propertyString(this, property));
	}

	public @Nonnull ConfigProperty<Boolean> propertyBoolean(final @Nonnull Property property) {
		return registerReload(ConfigProperty.propertyBoolean(this, property));
	}

	public @Nonnull ConfigProperty<Double> propertyDouble(final @Nonnull Property property) {
		return registerReload(ConfigProperty.propertyDouble(this, property));
	}

	public @Nonnull ConfigProperty<Integer> propertyInteger(final @Nonnull Property property) {
		return registerReload(ConfigProperty.propertyInteger(this, property));
	}

	public static interface ConfigListener<E> {
		void onChanged(@Nonnull E value);
	}

	public static abstract class ConfigProperty<E> implements IReloadableConfig {
		protected final @Nonnull Configuration config;
		protected final @Nonnull Property property;
		private transient @Nonnull E prop;
		private @Nullable ConfigListener<E> listener;

		protected ConfigProperty(final @Nonnull Configuration config, final @Nonnull Property property, final @Nonnull E prop) {
			this.config = config;
			this.property = property;
			this.prop = prop;
		}

		public @Nonnull Property getProperty() {
			return this.property;
		}

		public @Nonnull ConfigProperty<E> setListener(@Nullable final ConfigListener<E> listener) {
			this.listener = listener;
			return this;
		}

		public @Nonnull ConfigProperty<E> setComment(final @Nonnull String comment) {
			this.property.comment = comment;
			return this;
		}

		protected void setProp(final @Nonnull E prop) {
			if (!this.property.requiresMcRestart()) {
				this.prop = prop;
				if (this.listener!=null)
					this.listener.onChanged(prop);
			}
		}

		public @Nonnull E get() {
			return this.prop;
		}

		public abstract @Nonnull ConfigProperty<E> set(@Nonnull E value);

		public abstract @Nonnull ConfigProperty<E> reset();

		public static @Nonnull ConfigProperty<String> propertyString(final @Nonnull Config config, final @Nonnull Property property) {
			return new StringConfigProperty(config, property);
		}

		public static @Nonnull ConfigProperty<Boolean> propertyBoolean(final @Nonnull Config config, final @Nonnull Property property) {
			return new BooleanConfigProperty(config, property);
		}

		public static @Nonnull ConfigProperty<Double> propertyDouble(final @Nonnull Config config, final @Nonnull Property property) {
			return new DoubleConfigProperty(config, property);
		}

		public static @Nonnull ConfigProperty<Integer> propertyInteger(final @Nonnull Config config, final @Nonnull Property property) {
			return new IntegerConfigProperty(config, property);
		}

		private static class StringConfigProperty extends ConfigProperty<String> {
			protected StringConfigProperty(final @Nonnull Configuration config, final @Nonnull Property property) {
				super(config, property, property.getString());
			}

			@Override
			public @Nonnull StringConfigProperty set(final @Nonnull String value) {
				this.property.set(value);
				setProp(value);
				this.config.save();
				return this;
			}

			@Override
			public @Nonnull StringConfigProperty reset() {
				final String p = this.property.getDefault();
				this.property.set(p);
				setProp(p);
				this.config.save();
				return this;
			}

			@Override
			public void reload() {
				setProp(this.property.getString());
			}
		}

		private static class BooleanConfigProperty extends ConfigProperty<Boolean> {
			protected BooleanConfigProperty(final @Nonnull Configuration config, final @Nonnull Property property) {
				super(config, property, property.getBoolean());
			}

			@Override
			public @Nonnull BooleanConfigProperty set(final @Nonnull Boolean value) {
				this.property.set(value);
				setProp(value);
				this.config.save();
				return this;
			}

			@Override
			public @Nonnull BooleanConfigProperty reset() {
				final String p = this.property.getDefault();
				this.property.set(p);
				setProp(this.property.getBoolean());
				this.config.save();
				return this;
			}

			@Override
			public void reload() {
				setProp(this.property.getBoolean());
			}
		}

		private static class DoubleConfigProperty extends ConfigProperty<Double> {
			protected DoubleConfigProperty(final @Nonnull Configuration config, final @Nonnull Property property) {
				super(config, property, property.getDouble());
			}

			@Override
			public @Nonnull DoubleConfigProperty set(final @Nonnull Double value) {
				this.property.set(value);
				setProp(value);
				this.config.save();
				return this;
			}

			@Override
			public @Nonnull DoubleConfigProperty reset() {
				final String p = this.property.getDefault();
				this.property.set(p);
				setProp(this.property.getDouble());
				this.config.save();
				return this;
			}

			@Override
			public void reload() {
				setProp(this.property.getDouble());
			}
		}

		private static class IntegerConfigProperty extends ConfigProperty<Integer> {
			protected IntegerConfigProperty(final @Nonnull Configuration config, final @Nonnull Property property) {
				super(config, property, property.getInt());
			}

			@Override
			public @Nonnull IntegerConfigProperty set(final @Nonnull Integer value) {
				this.property.set(value);
				setProp(value);
				this.config.save();
				return this;
			}

			@Override
			public @Nonnull IntegerConfigProperty reset() {
				final String p = this.property.getDefault();
				this.property.set(p);
				setProp(this.property.getInt());
				this.config.save();
				return this;
			}

			@Override
			public void reload() {
				setProp(this.property.getInt());
			}
		}
	}
}