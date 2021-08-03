# LocationAPI
---

Welcome to the start of the LocationAPI wiki. Below you will find examples on development usage. The sole purpose this interface serves is provide cross-plugin
location communication without the need to dependency injection. Similar to how [Vault]() or [Enterprise]() utilize the Bukkit services manager to register implementations,
as does this.

### Using the API
---

Accessing our test warp from a different plugin without dependency injection

```JAVA
			LocationAPI api = LocationAPI.getInstance("TargetPlugin");

			if (api != null) {
				PlayerHolder holder = (PlayerHolder) api.getHolder(player.getUniqueId());
				Warp w = holder.getWarp("test");
				if (w != null) {
					player.teleport(w.toLocation());
					sendMessage(player, "&aWelcome to test!");
				} else {
					sendMessage(player, "&cNo warp under this name exists for you!");
				
```

### Providing the API
---

First you will need to create an implementation of the interface [**LocationAPI**](https://github.com/the-h-team/LocationAPI/blob/0e4f18596da1930fdcc3a19c9bfa936232b5af92/src/main/java/com/github/sanctum/locationapi/implement/LocationAPI.java#L9)

Go ahead and get familiar with the [**Warp**](https://github.com/the-h-team/LocationAPI/blob/30a3a6dd2c293a55d26ba81a45cc69de5bffe0da/src/main/java/com/github/sanctum/locationapi/implement/Warp.java#L6) & [**WarpHolder**](https://github.com/the-h-team/LocationAPI/blob/30a3a6dd2c293a55d26ba81a45cc69de5bffe0da/src/main/java/com/github/sanctum/locationapi/implement/WarpHolder.java#L6) objects if you plan on having full support.

```JAVA
public class MyAPI implements LocationAPI {

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
	@Override
	public Implementation getImplementation() {
		return null;
	}

	@Override
	public boolean hasPublicSupport() {
		return false;
	}

	@Override
	public boolean hasPrivateSupport() {
		return false;
	}

	@Override
	public WarpHolder getHolder(UUID id) {
		return new MyPlayerHolder(id);
	}

	@Override
	public WarpHolder getHolder(String holder) {
		return null;
	}

	@Override
	public Warp getWarp(String warpName, Warp.Type warpType) {
		return null;
	}

	@Override
	public Warp getWarp(String warpName) {
		return null;
	}

	@Override
	public List<Warp> getWarps(String holder) {
		return null;
	}

	@Override
	public List<Warp> getWarps(UUID id) {
		return null;
	}

	@Override
	public List<Warp> getWarps() {
		return null;
	}
}
```


Implementations of a WarpHolder are fairly simple. There are two defaults for you to use.

[**PlayerHolder**](https://github.com/the-h-team/LocationAPI/blob/30a3a6dd2c293a55d26ba81a45cc69de5bffe0da/src/main/java/com/github/sanctum/locationapi/PlayerHolder.java#L6) & [**ServerHolder**](https://github.com/the-h-team/LocationAPI/blob/30a3a6dd2c293a55d26ba81a45cc69de5bffe0da/src/main/java/com/github/sanctum/locationapi/ServerHolder.java#L6)

Since everyones inner api differs you are responsible for filling out the inner portions of these methods.

- **Player**:
```JAVA
public class MyPlayerHolder extends PlayerHolder {

	public MyPlayerHolder(UUID id) {
		super(Bukkit.getOfflinePlayer(id));
	}

	public MyPlayerHolder(OfflinePlayer player) {
		super(player);
	}

	@Override
	public Warp getWarp(String name) {
		return null;
	}

	@Override
	public List<Warp> getWarps() {
		return null;
	}
}
```

- **Server**:
```JAVA
public class MyServerHolder extends ServerHolder {

	public MyServerHolder(Plugin plugin) {
		super(plugin);
	}

	@Override
	public Warp getWarp(String name) {
		return null;
	}

	@Override
	public List<Warp> getWarps() {
		return null;
	}
}
```


Now that we have our bases covered, let's start on our actual warp implementations.

There are two default types present here aswell. [**PublicWarp**](https://github.com/the-h-team/LocationAPI/blob/master/src/main/java/com/github/sanctum/locationapi/PublicWarp.java) && [**PrivateWarp**](https://github.com/the-h-team/LocationAPI/blob/master/src/main/java/com/github/sanctum/locationapi/PrivateWarp.java)

You can choose to do some custom stuff with this or you can use the default implementation as so.

- **Public**:
```JAVA
public class MyPublicWarp extends PublicWarp {
	protected MyPublicWarp(ServerHolder holder, Location location, String name) {
		super(holder, location, name);
	}

	protected MyPublicWarp(Location location, String name) {
		super(location, name);
	}
}
```

- **Private**:
```JAVA
public class MyPrivateWarp extends PrivateWarp {
	protected MyPrivateWarp(PlayerHolder holder, Location location, String name) {
		super(holder, location, name);
	}

	protected MyPrivateWarp(Location location, String name) {
		super(location, name);
	}
}
```

Finally don't forget to register your interface into the services manager!

```JAVA
	Schedule.sync(() -> Bukkit.getServicesManager().register(LocationAPI.class, new MyAPI(), Plugin plugin, ServicePriority.High)).wait(3);
```
