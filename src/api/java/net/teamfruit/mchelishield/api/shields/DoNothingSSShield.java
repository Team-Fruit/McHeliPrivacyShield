package net.teamfruit.mchelishield.api.shields;

import java.awt.image.BufferedImage;

import net.teamfruit.mchelishield.api.ScrennShotShield;

public class DoNothingSSShield implements ScrennShotShield {
	@Override
	public String name() {
		return "none";
	}

	@Override
	public BufferedImage onRequested() {
		return null;
	}
}
