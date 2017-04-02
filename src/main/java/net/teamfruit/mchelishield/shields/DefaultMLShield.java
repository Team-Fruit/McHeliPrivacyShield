package net.teamfruit.mchelishield.shields;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import javax.annotation.Nonnull;

import com.google.common.collect.Lists;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.relauncher.CoreModManager;
import mcheli.MCH_FileSearch;
import mcheli.MCH_Lib;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;
import net.teamfruit.mchelishield.api.ModListShield;

public class DefaultMLShield implements ModListShield {
	@Override
	public String name() {
		return "default";
	}

	@Override
	public List<String> onRequested(final @Nonnull String playerName) {
		final ArrayList<String> modList = Lists.newArrayList();

		modList.add(EnumChatFormatting.RED+"###### "+playerName+" ######");

		final String[] classFileNameList = System.getProperty("java.class.path").split(File.pathSeparator);
		final String[] mc = classFileNameList;
		final int search = classFileNameList.length;
		for (int files = 0; files<search; ++files) {
			final String arr$ = mc[files];
			MCH_Lib.DbgLog(true, "java.class.path="+arr$, new Object[0]);
			if (arr$.length()>1) {

				final File len$ = new File(arr$);
				if (len$.getAbsolutePath().toLowerCase().indexOf("versions")>=0)
					modList.add(EnumChatFormatting.AQUA+"# Client class="+len$.getName()+" : file size= "+len$.length());
			}
		}

		modList.add(EnumChatFormatting.YELLOW+"=== ActiveModList ===");
		final Iterator<ModContainer> arg19 = Loader.instance().getActiveModList().iterator();
		while (arg19.hasNext()) {
			final ModContainer arg20 = arg19.next();
			modList.add(""+arg20+"  ["+arg20.getModId()+"]  "+arg20.getName()+"["+arg20.getDisplayVersion()+"]  "+arg20.getSource().getName());

		}

		if (CoreModManager.getAccessTransformers().size()>0) {
			modList.add(EnumChatFormatting.YELLOW+"=== AccessTransformers ===");
			final Iterator<String> arg19_0 = CoreModManager.getAccessTransformers().iterator();
			while (arg19_0.hasNext()) {
				final String arg21 = arg19_0.next();
				modList.add(arg21);
			}
		}

		if (CoreModManager.getLoadedCoremods().size()>0) {

			modList.add(EnumChatFormatting.YELLOW+"=== LoadedCoremods ===");
			final Iterator<String> arg19_1 = CoreModManager.getLoadedCoremods().iterator();
			while (arg19_1.hasNext()) {
				final String arg21 = arg19_1.next();
				modList.add(arg21);
			}
		}

		if (CoreModManager.getReparseableCoremods().size()>0) {

			modList.add(EnumChatFormatting.YELLOW+"=== ReparseableCoremods ===");
			final Iterator<String> arg19_2 = CoreModManager.getReparseableCoremods().iterator();
			while (arg19_2.hasNext()) {
				final String arg21 = arg19_2.next();
				modList.add(arg21);

			}
		}

		final Minecraft arg22 = Minecraft.getMinecraft();
		MCH_FileSearch arg23 = new MCH_FileSearch();
		File[] arg24 = arg23.listFiles(new File(arg22.mcDataDir, "mods").getAbsolutePath(), "*.jar");
		modList.add(EnumChatFormatting.YELLOW+"=== Manifest ===");
		File[] arg25 = arg24;
		int arg26 = arg24.length;
		int i$;
		File file;
		String e;
		JarFile jarFile;
		Enumeration<JarEntry> jarEntries;
		String litemod_json;
		ZipEntry zipEntry;
		for (i$ = 0; i$<arg26; ++i$) {
			file = arg25[i$];
			try {
				e = file.getCanonicalPath();
				jarFile = new JarFile(e);
				jarEntries = jarFile.entries();
				litemod_json = "";
				while (jarEntries.hasMoreElements()) {

					zipEntry = jarEntries.nextElement();
					if (zipEntry.getName().equalsIgnoreCase("META-INF/MANIFEST.MF")&&!zipEntry.isDirectory()) {

						final InputStream fname = jarFile.getInputStream(zipEntry);
						final BufferedReader index = new BufferedReader(new InputStreamReader(fname));
						for (String br = index.readLine(); br!=null; br = index.readLine()) {

							br = br.replace(" ", "").trim();
							if (!br.isEmpty())
								litemod_json = litemod_json+" ["+br+"]";
						}

						fname.close();
						break;
					}
				}

				jarFile.close();
				if (!litemod_json.isEmpty())
					modList.add(file.getName()+litemod_json);
			} catch (final Exception arg18) {
				modList.add(file.getName()+" : Read Manifest failed.");

			}
		}

		arg23 = new MCH_FileSearch();
		arg24 = arg23.listFiles(new File(arg22.mcDataDir, "mods").getAbsolutePath(), "*.litemod");
		modList.add(EnumChatFormatting.LIGHT_PURPLE+"=== LiteLoader ===");
		arg25 = arg24;
		arg26 = arg24.length;
		label117: for (i$ = 0; i$<arg26; ++i$) {
			file = arg25[i$];
			try {
				e = file.getCanonicalPath();
				jarFile = new JarFile(e);
				jarEntries = jarFile.entries();
				litemod_json = "";
				while (true)
					while (true) {
						String arg27;
						do {
							if (!jarEntries.hasMoreElements())
								continue label117;
							zipEntry = jarEntries.nextElement();
							arg27 = zipEntry.getName().toLowerCase();
						} while (zipEntry.isDirectory());
						if (arg27.equals("litemod.json")) {

							final InputStream arg29 = jarFile.getInputStream(zipEntry);
							final BufferedReader arg30 = new BufferedReader(new InputStreamReader(arg29));
							for (String line = arg30.readLine(); line!=null; line = arg30.readLine()) {

								line = line.replace(" ", "").trim();
								if (line.toLowerCase().indexOf("name")>=0) {

									litemod_json = litemod_json+" ["+line+"]";
									break;
								}
							}

							arg29.close();

						} else {
							final int arg28 = arg27.lastIndexOf("/");
							if (arg28>=0)
								arg27 = arg27.substring(arg28+1);
							if (arg27.indexOf("litemod")>=0&&arg27.endsWith("class")) {

								arg27 = zipEntry.getName();
								if (arg28>=0)
									arg27 = arg27.substring(arg28+1);
								litemod_json = litemod_json+" ["+arg27+"]";

								jarFile.close();
								if (!litemod_json.isEmpty())
									modList.add(file.getName()+litemod_json);
							}
						}
					}
			} catch (final Exception arg17) {
				modList.add(file.getName()+" : Read LiteLoader failed.");

			}
		}
		return modList;
	}
}
