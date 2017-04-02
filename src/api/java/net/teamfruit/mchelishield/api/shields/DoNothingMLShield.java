package net.teamfruit.mchelishield.api.shields;

import java.util.List;

import javax.annotation.Nonnull;

import net.minecraft.client.resources.I18n;
import net.teamfruit.mchelishield.api.ModListShield;

public class DoNothingMLShield implements ModListShield {
	@Override
	public String name() {
		return I18n.format("mchelishield.config.mlshield.mode.none");
	}

	@Override
	public String id() {
		return "none";
	}

	@Override
	public List<String> onRequested(final @Nonnull String playerName) {
		return null;
	}
}
