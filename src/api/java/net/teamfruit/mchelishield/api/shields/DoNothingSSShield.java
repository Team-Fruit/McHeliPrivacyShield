package net.teamfruit.mchelishield.api.shields;

import java.awt.image.BufferedImage;

import net.minecraft.client.resources.I18n;
import net.teamfruit.mchelishield.api.ScrennShotShield;

public class DoNothingSSShield implements ScrennShotShield {
	@Override
	public String name() {
		return I18n.format("mchelishield.config.ssshield.mode.none");
	}

	@Override
	public String id() {
		return "none";
	}

	@Override
	public BufferedImage onRequested() {
		return null;
	}
}
