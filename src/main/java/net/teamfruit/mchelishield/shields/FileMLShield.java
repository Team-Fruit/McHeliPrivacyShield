package net.teamfruit.mchelishield.shields;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.google.common.collect.Lists;

import net.teamfruit.mchelishield.ChatBuilder;
import net.teamfruit.mchelishield.Config;
import net.teamfruit.mchelishield.Log;
import net.teamfruit.mchelishield.api.ModListShield;

public class FileMLShield implements ModListShield {
	@Override
	public String name() {
		return "file";
	}

	@Override
	public List<String> onRequested(final String playerName) {
		BufferedReader br = null;
		try {
			final File file = new File(Config.getConfig().mlfile.get());
			if (file.exists()) {
				final FileReader fr = new FileReader(file);
				br = new BufferedReader(fr);
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
