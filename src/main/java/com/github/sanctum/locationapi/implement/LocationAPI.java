package com.github.sanctum.locationapi.implement;

import java.util.List;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

/**
 * An api interface meant for cross-plugin location communication.
 */
public interface LocationAPI {

	/**
	 * Get a random api implementation.
	 *
	 * @return A random implementation by highest priority.
	 */
	static LocationAPI getInstance() {
		return Bukkit.getServicesManager().load(LocationAPI.class);
	}

	/**
	 * Get the desired plugin referenced api implementation.
	 *
	 * @param pluginName The plugin to get an implementation from.
	 * @return The desired implementation or random selection by priority if not found.
	 */
	static LocationAPI getInstance(String pluginName, Implementation impl) {
		RegisteredServiceProvider<LocationAPI> t = Bukkit.getServicesManager().getRegistrations(LocationAPI.class).stream().filter(r -> r.getPlugin().getName().equalsIgnoreCase(pluginName) && r.getProvider().getImplementation() == impl).findFirst().orElse(null);
		return t != null ? t.getProvider() : Bukkit.getServicesManager().load(LocationAPI.class);
	}

	/**
	 * Get the desired plugin referenced api implementation.
	 *
	 * @param pluginName The plugin to get an implementation from.
	 * @return The desired implementation or random selection by priority if not found.
	 */
	static LocationAPI getInstance(String pluginName) {
		RegisteredServiceProvider<LocationAPI> t = Bukkit.getServicesManager().getRegistrations(LocationAPI.class).stream().filter(r -> r.getPlugin().getName().equalsIgnoreCase(pluginName)).findFirst().orElse(null);
		return t != null ? t.getProvider() : Bukkit.getServicesManager().load(LocationAPI.class);
	}

	/**
	 * @return The type of implementation this object provides.
	 */
	Implementation getImplementation();

	/**
	 * @return true if this implementation supports public warps.
	 */
	boolean hasPublicSupport();

	/**
	 * @return true if this implementation supports private warps.
	 */
	boolean hasPrivateSupport();

	/**
	 * Wrap a unique id to check for linked warps.
	 *
	 * @param id The uuid to provide.
	 * @return The desired warp holder.
	 */
	WarpHolder getHolder(UUID id);

	/**
	 * Wrap a name to check for linked warps.
	 *
	 * @param holder The name to provide.
	 * @return The desired warp holder.
	 */
	WarpHolder getHolder(String holder);

	/**
	 * Get a desired warp by its name and type.
	 *
	 * @param warpName The name of the warp.
	 * @param warpType The type of warp to retrieve.
	 * @return The desired warp.
	 */
	Warp getWarp(String warpName, Warp.Type warpType);

	/**
	 * Get a desired warp by its name.
	 *
	 * @param warpName The name of the warp.
	 * @return The desired warp.
	 */
	Warp getWarp(String warpName);

	/**
	 * Get all warps belonging to a holder by name.
	 *
	 * @param holder The name of the holder.
	 * @return A list of all warps pertaining to the search.
	 */
	List<Warp> getWarps(String holder);

	/**
	 * Get all warps belonging to a unique id.
	 *
	 * @param id The id of the holder.
	 * @return A list of all warps pertaining to the search.
	 */
	List<Warp> getWarps(UUID id);

	/**
	 * Get all known warps.
	 *
	 * @return A list of all known warps from this implementation.
	 */
	List<Warp> getWarps();

	/**
	 * Describes the status of the currently used API.
	 *
	 * <p>SIMPLE = Only {@link LocationAPI#getWarps()}, {@link LocationAPI#getWarp(String, Warp.Type)} && {@link LocationAPI#getWarp(String)} are valid api usages.
	 * Used for simple location grabbing.
	 *
	 * <p>ADVANCED = Full API support available.
	 * Used for full warp information access + custom implementations.
	 *
	 */
	enum Implementation {
		SIMPLE, ADVANCED
	}

}
