package com.github.sanctum.locationapi.implement;

import java.util.List;

/**
 * Used to encapsulate user data for location information.
 */
public interface WarpHolder {

	String id();

	Warp getWarp(String name);

	List<Warp> getWarps();

	default Type getType() {
		return Type.UNKNOWN;
	}

	enum Type {
		PLAYER, SERVER, UNKNOWN
	}

}
