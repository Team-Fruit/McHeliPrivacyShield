package net.teamfruit.mchelishield.api;

import java.awt.image.BufferedImage;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.collect.Maps;

import net.teamfruit.mchelishield.api.shields.DoNothingSSShield;

/**
 * スクリーンショット要求リクエストの返答タイプを登録します
 *
 * @author TeamFruit
 */
public class ScreenShotShieldRegistery {
	private static final @Nonnull Map<String, ScrennShotShield> listeners = Maps.newHashMap();
	private static final @Nonnull ScrennShotShield defaultListener = new DoNothingSSShield();

	static {
		register(defaultListener);
	}

	private ScreenShotShieldRegistery() {
	}

	/**
	 * スクリーンショット要求リクエストの返答タイプを登録します
	 * @param listener 返答タイプ
	 */
	public static void register(final @Nonnull ScrennShotShield listener) {
		listeners.put(listener.id(), listener);
	}

	public static @Nullable BufferedImage onRequested(final @Nonnull String mode) {
		ScrennShotShield shield = listeners.get(mode);
		if (shield==null)
			shield = defaultListener;
		return shield.onRequested();
	}

	public static @Nonnull Map<String, ScrennShotShield> getListeners() {
		return listeners;
	}
}
