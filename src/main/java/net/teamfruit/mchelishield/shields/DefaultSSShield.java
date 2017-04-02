package net.teamfruit.mchelishield.shields;

import java.awt.image.BufferedImage;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.shader.Framebuffer;
import net.teamfruit.mchelishield.api.ScrennShotShield;

public class DefaultSSShield implements ScrennShotShield {
	private static IntBuffer pixelBuffer;
	private static int[] pixelValues;

	@Override
	public String name() {
		return I18n.format("mchelishield.config.ssshield.mode.default");
	}

	@Override
	public String id() {
		return "default";
	}

	@Override
	public BufferedImage onRequested() {
		final Minecraft mc = Minecraft.getMinecraft();
		int displayWidth = mc.displayWidth;
		int displayHeight = mc.displayHeight;
		final Framebuffer framebufferMc = mc.getFramebuffer();

		try {
			if (OpenGlHelper.isFramebufferEnabled()) {

				displayWidth = framebufferMc.framebufferTextureWidth;
				displayHeight = framebufferMc.framebufferTextureHeight;
			}

			final int exception = displayWidth*displayHeight;

			if (pixelBuffer==null||pixelBuffer.capacity()<exception) {

				pixelBuffer = BufferUtils.createIntBuffer(exception);
				pixelValues = new int[exception];
			}

			GL11.glPixelStorei(3333, 1);
			GL11.glPixelStorei(3317, 1);
			pixelBuffer.clear();

			if (OpenGlHelper.isFramebufferEnabled()) {

				GL11.glBindTexture(3553, framebufferMc.framebufferTexture);
				GL11.glGetTexImage(3553, 0, '胡', '荧', pixelBuffer);

			} else
				GL11.glReadPixels(0, 0, displayWidth, displayHeight, '胡', '荧', pixelBuffer);

			pixelBuffer.get(pixelValues);
			TextureUtil.func_147953_a(pixelValues, displayWidth, displayHeight);
			BufferedImage bufferedimage = null;

			if (OpenGlHelper.isFramebufferEnabled()) {
				bufferedimage = new BufferedImage(framebufferMc.framebufferWidth, framebufferMc.framebufferHeight, 1);
				final int l = framebufferMc.framebufferTextureHeight-framebufferMc.framebufferHeight;

				for (int i1 = l; i1<framebufferMc.framebufferTextureHeight; ++i1)
					for (int j1 = 0; j1<framebufferMc.framebufferWidth; ++j1)
						bufferedimage.setRGB(j1, i1-l, pixelValues[i1*framebufferMc.framebufferTextureWidth+j1]);
			} else {
				bufferedimage = new BufferedImage(displayWidth, displayHeight, 1);
				bufferedimage.setRGB(0, 0, displayWidth, displayHeight, pixelValues, 0, displayWidth);
			}

			return bufferedimage;
		} catch (final Exception arg7) {
			;
		}
		return null;
	}
}
