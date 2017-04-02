package net.teamfruit.mchelishield.api;

import java.awt.image.BufferedImage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * /mcheli sendssコマンドより、スクリーンショット要求リクエストを受けた時の返答タイプを定義します
 *
 * @author TeamFruit
 */
public interface ScrennShotShield {
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
	 * @return 送信する画像 ※nullの場合は送信されません
	 */
	@Nullable
	BufferedImage onRequested();
}
