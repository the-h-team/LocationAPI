package com.github.sanctum.locationapi;

import com.github.sanctum.locationapi.implement.Warp;
import org.bukkit.Location;

public abstract class PublicWarp implements Warp {

	protected final Location location;
	protected final String name;
	protected ServerHolder holder;

	protected PublicWarp(ServerHolder holder, Location location, String name) {
		this.holder = holder;
		this.location = location;
		this.name = name;
	}

	protected PublicWarp(Location location, String name) {
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
	public final ServerHolder getHolder() {
		return this.holder;
	}

	@Override
	public final Type getType() {
		return Type.PUBLIC;
	}
}
