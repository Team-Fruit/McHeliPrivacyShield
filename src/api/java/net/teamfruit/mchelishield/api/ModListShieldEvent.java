package net.teamfruit.mchelishield.api;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraftforge.common.MinecraftForge;

/**
 * Modリスト要求リクエストを受けた時のイベント
 *
 * @author TeamFruit
 */
public abstract class ModListShieldEvent extends Event {
	/**
	 * 現在設定されている返答タイプです
	 */
	public @Nonnull String mode;

	/**
	 * プレイヤーの名前
	 */
	public @Nonnull String playerName;

	public ModListShieldEvent(final @Nonnull String mode, final @Nonnull String playerName) {
		this.mode = mode;
		this.playerName = playerName;
	}

	/**
	 * イベントを発生させます。
	 */
	public void post() {
		MinecraftForge.EVENT_BUS.post(this);
	}

	/**
	 * Modリスト要求リクエストを受けた時のイベント
	 * <br>
	 * 返答タイプの生成処理の前に呼ばれます。
	 *
	 * @author TeamFruit
	 */
	@Cancelable
	public static class Pre extends ModListShieldEvent {
		public Pre(final @Nonnull String mode, final @Nonnull String playerName) {
			super(mode, playerName);
		}
	}

	/**
	 * Modリスト要求リクエストを受けた時のイベント
	 * <br>
	 * 返答タイプの生成処理の後に呼ばれます。
	 *
	 * @author TeamFruit
	 */
	@Cancelable
	public static class Post extends ModListShieldEvent {
		/**
		 * 送信予定のテキスト
		 * <br>
		 * nullの場合は送信されません
		 */
		public @Nullable List<String> pending;

		public Post(final @Nonnull String mode, final @Nonnull String playerName) {
			super(mode, playerName);
		}
	}
}
