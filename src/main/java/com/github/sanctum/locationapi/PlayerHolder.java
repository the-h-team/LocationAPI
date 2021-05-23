package com.github.sanctum.locationapi;

import com.github.sanctum.locationapi.implement.WarpHolder;
import org.bukkit.OfflinePlayer;

public abstract class PlayerHolder implements WarpHolder {

	private final OfflinePlayer player;

	public PlayerHolder(OfflinePlayer player) {
		this.player = player;
	}

	@Override
	public final String id() {
		return this.player.getUniqueId().toString();
	}

	public OfflinePlayer getPlayer() {
		return this.player;
	}

	@Override
	public final Type getType() {
		return Type.PLAYER;
	}
}
