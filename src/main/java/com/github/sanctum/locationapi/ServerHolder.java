package com.github.sanctum.locationapi;

import com.github.sanctum.locationapi.implement.WarpHolder;
import org.bukkit.plugin.Plugin;

public abstract class ServerHolder implements WarpHolder {

	private final Plugin plugin;

	public ServerHolder(Plugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public final String id() {
		return this.plugin.getName();
	}

	public Plugin getPlugin() {
		return this.plugin;
	}

	@Override
	public final Type getType() {
		return Type.SERVER;
	}
}
