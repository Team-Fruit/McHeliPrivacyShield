package net.teamfruit.mchelishield.api.shields;

import java.util.List;

import javax.annotation.Nonnull;

import net.teamfruit.mchelishield.api.ModListShield;

public class DoNothingMLShield implements ModListShield {
	@Override
	public String name() {
		return "none";
	}

	@Override
	public List<String> onRequested(final @Nonnull String playerName) {
		return null;
	}
}
