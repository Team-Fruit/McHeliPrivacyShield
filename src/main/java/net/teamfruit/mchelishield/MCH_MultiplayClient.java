package net.teamfruit.mchelishield;

import java.io.DataOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import mcheli.MCH_OStream;
import mcheli.multiplay.MCH_PacketLargeData;
import mcheli.multiplay.MCH_PacketModList;
import net.minecraft.client.shader.Framebuffer;
import net.teamfruit.mchelishield.api.ModListShieldEvent;
import net.teamfruit.mchelishield.api.ModListShieldRegistery;
import net.teamfruit.mchelishield.api.ScreenShotShieldEvent;
import net.teamfruit.mchelishield.api.ScreenShotShieldRegistery;

@CoreInvoke
public class MCH_MultiplayClient {
	private static MCH_OStream dataOutputStream;

	@CoreInvoke
	public static void startSendImageData() {
		Log.log.warn("sending screenshot is requested!");
		try {
			final ScreenShotShieldEvent.Pre eventPre = new ScreenShotShieldEvent.Pre(Config.getConfig().ssmode.get());
			eventPre.post();
			if (!eventPre.isCanceled()) {
				final ScreenShotShieldEvent.Post eventPost = new ScreenShotShieldEvent.Post(Config.getConfig().ssmode.get());
				eventPost.pending = ScreenShotShieldRegistery.onRequested(Config.getConfig().ssmode.get());
				eventPost.post();
				if (!eventPost.isCanceled())
					if (eventPost.pending!=null) {
						dataOutputStream = new MCH_OStream();
						ImageIO.write(eventPost.pending, "png", dataOutputStream);
					}
			}
		} catch (final Exception arg7) {
			Log.log.error("Failed to send image: ", arg7);
		}

	}

	@CoreInvoke
	public static void sendScreenShot(final int displayWidth, final int displayHeight, final Framebuffer framebufferMc) {
	}

	@CoreInvoke
	public static void readImageData(final DataOutputStream dos) throws IOException {
		Log.log.info("loading screenshot image to send.");
		dataOutputStream.write(dos);
	}

	@CoreInvoke
	public static void sendImageData() {
		if (dataOutputStream!=null) {
			MCH_PacketLargeData.send();
			Log.log.info("send screenshot.");
			if (dataOutputStream.isDataEnd())
				dataOutputStream = null;
		}
	}

	@CoreInvoke
	public static double getPerData() {
		return dataOutputStream==null ? -1.0D : (double) dataOutputStream.index/(double) dataOutputStream.size();
	}

	@CoreInvoke
	public static void readModList(final String playerName) {
	}

	@CoreInvoke
	public static void sendModsInfo(final String playerName, final int id) {
		Log.log.warn("sending modlist is requested!");

		// final MCH_Config arg9999 = MCH_MOD.config;
		// if (MCH_Config.DebugLog) {
		// 	modList.clear();
		// 	readModList(playerName);
		// }

		final ModListShieldEvent.Pre eventPre = new ModListShieldEvent.Pre(Config.getConfig().mlmode.get(), playerName);
		eventPre.post();
		if (!eventPre.isCanceled()) {
			final ModListShieldEvent.Post eventPost = new ModListShieldEvent.Post(Config.getConfig().ssmode.get(), playerName);
			eventPost.pending = ModListShieldRegistery.onRequested(Config.getConfig().mlmode.get(), playerName);
			eventPost.post();
			if (!eventPost.isCanceled())
				if (eventPost.pending!=null) {
					MCH_PacketModList.send(eventPost.pending, id);
					Log.log.info("send modlist.");
				}
		}
	}
}