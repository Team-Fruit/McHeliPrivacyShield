package net.teamfruit.mchelishield.shields;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

import com.google.common.collect.Lists;

import net.minecraft.client.resources.I18n;
import net.teamfruit.mchelishield.ChatBuilder;
import net.teamfruit.mchelishield.Config;
import net.teamfruit.mchelishield.Log;
import net.teamfruit.mchelishield.api.ModListShield;

public class FileMLShield implements ModListShield {
	@Override
	public String name() {
		return I18n.format("mchelishield.config.mlshield.mode.file");
	}

	@Override
	public String id() {
		return "file";
	}

	@Override
	public List<String> onRequested(final String playerName) {
		BufferedReader br = null;
		try {
			final File file = new File(Config.getConfig().mlfile.get());
			if (file.exists()) {
				Charset charset = null;
				try {
					charset = Charset.forName(Config.getConfig().mlfileencode.get());
				} catch (final Exception e) {
				}
				if (charset==null)
					charset = Charsets.UTF_8;

				br = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
				final List<String> modlist = Lists.newArrayList();
				String line;
				while ((line = br.readLine())!=null)
					modlist.add(line);
				return modlist;
			} else {
				Log.log.info("No image found.");
				new ChatBuilder().setText("mchelishield.ssshield.file.notfound").useTranslation().chatClient();
				return null;
			}
		} catch (final Exception arg7) {
			Log.log.info("Image load error: ", arg7);
			new ChatBuilder().setText("mchelishield.ssshield.file.error").useTranslation().chatClient();
		} finally {
			IOUtils.closeQuietly(br);
		}
		return null;
	}
}
