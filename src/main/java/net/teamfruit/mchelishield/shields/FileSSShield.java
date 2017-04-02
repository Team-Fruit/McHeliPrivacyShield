package net.teamfruit.mchelishield.shields;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import net.teamfruit.mchelishield.ChatBuilder;
import net.teamfruit.mchelishield.Config;
import net.teamfruit.mchelishield.Log;
import net.teamfruit.mchelishield.api.ScrennShotShield;

public class FileSSShield implements ScrennShotShield {
	@Override
	public String name() {
		return "file";
	}

	@Override
	public BufferedImage onRequested() {
		try {
			final File file = new File(Config.getConfig().ssfile.get());
			if (file.exists()) {
				final BufferedImage image = ImageIO.read(file);
				return image;
			} else {
				Log.log.info("No image found.");
				new ChatBuilder().setText("mchelishield.ssshield.file.notfound").useTranslation().chatClient();
				return null;
			}
		} catch (final Exception arg7) {
			Log.log.info("Image load error: ", arg7);
			new ChatBuilder().setText("mchelishield.ssshield.file.error").useTranslation().chatClient();
		}
		return null;
	}
}
