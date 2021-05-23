package com.github.sanctum.locationapi.implement;

import org.bukkit.Location;

/**
 * Used to encapsulate a location and other data.
 */
public interface Warp {

	WarpHolder getHolder();

	String getName();

	Location toLocation();

	default Type getType() {
		return Type.PRIVATE;
	}

	enum Type {
		PUBLIC, PRIVATE
	}

}
