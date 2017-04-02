package net.teamfruit.mchelishield.api;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * /mcheli modlistコマンドより、Modリスト要求リクエストを受けた時の返答タイプを定義します
 *
 * @author TeamFruit
 */
public interface ModListShield {
	/**
	 * この動作の名称
	 * @return 名称
	 */
	@Nonnull
	String name();

	/**
	 * この動作のID
	 * @return ID
	 */
	@Nonnull
	String id();

	/**
	 * リクエストを受けた時呼び出されます。
	 * @param playerName プレイヤーの名前
	 * @return 送信するテキスト ※nullの場合は送信されません
	 */
	@Nullable
	List<String> onRequested(final @Nonnull String playerName);
}
