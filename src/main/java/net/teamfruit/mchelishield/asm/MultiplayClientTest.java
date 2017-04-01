package net.teamfruit.mchelishield.asm;

import java.io.DataOutputStream;
import java.io.IOException;

import mcheli.multiplay.MCH_MultiplayClient;
import net.minecraft.client.shader.Framebuffer;

public class MultiplayClientTest {
	public static void startSendImageData() {
		MCH_MultiplayClient.startSendImageData();
	}

	public static void sendScreenShot(final int displayWidth, final int displayHeight, final Framebuffer framebufferMc) {
		MCH_MultiplayClient.sendScreenShot(displayWidth, displayHeight, framebufferMc);
	}

	public static void readImageData(final DataOutputStream dos) throws IOException {
		MCH_MultiplayClient.readImageData(dos);
	}

	public static void sendImageData() {
		MCH_MultiplayClient.sendImageData();
	}

	public static double getPerData() {
		return MCH_MultiplayClient.getPerData();
	}

	public static void readModList(final String playerName) {
		MCH_MultiplayClient.readModList(playerName);
	}

	public static void sendModsInfo(final String playerName, final int id) {
		MCH_MultiplayClient.sendModsInfo(playerName, id);
	}
}