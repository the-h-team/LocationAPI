package com.github.sanctum.locationapi;

import com.github.sanctum.locationapi.implement.Warp;
import org.bukkit.Location;

public abstract class PrivateWarp implements Warp {

	protected final Location location;
	protected final String name;
	protected PlayerHolder holder;

	protected PrivateWarp(PlayerHolder holder, Location location, String name) {
		this.holder = holder;
		this.location = location;
		this.name = name;
	}

	protected PrivateWarp(Location location, String name) {
		this.location = location;
		this.name = name;
	}

	@Override
	public final String getName() {
		return this.name;
	}

	@Override
	public final Location toLocation() {
		return this.location;
	}

	@Override
	public final PlayerHolder getHolder() {
		return this.holder;
	}

	@Override
	public final Type getType() {
		return Type.PRIVATE;
	}
}
