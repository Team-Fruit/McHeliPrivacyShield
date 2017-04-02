package net.teamfruit.mchelishield.api;

import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.collect.Maps;

import net.teamfruit.mchelishield.api.shields.DoNothingMLShield;

/**
 * Modリスト要求リクエストの返答タイプを登録します
 *
 * @author TeamFruit
 */
public class ModListShieldRegistery {
	private static final @Nonnull Map<String, ModListShield> listeners = Maps.newHashMap();
	private static final @Nonnull ModListShield defaultListener = new DoNothingMLShield();

	static {
		register(defaultListener);
	}

	private ModListShieldRegistery() {
	}

	/**
	 * Modリスト要求リクエストの返答タイプを登録します
	 * @param listener 返答タイプ
	 */
	public static void register(final @Nonnull ModListShield listener) {
		listeners.put(listener.name(), listener);
	}

	public static @Nullable List<String> onRequested(final @Nonnull String mode, final @Nonnull String playerName) {
		ModListShield shield = listeners.get(mode);
		if (shield==null)
			shield = defaultListener;
		return shield.onRequested(playerName);
	}

	public static @Nonnull Map<String, ModListShield> getListeners() {
		return listeners;
	}
}
