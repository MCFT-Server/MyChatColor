package mychatcolor.manager;

import cn.nukkit.Server;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;
import mychatcolor.Main;
import mychatcolor.database.DataBase;

public class ChatColorManager {
	private Main plugin;
	private static ChatColorManager instance;

	public ChatColorManager(Main plugin) {
		this.plugin = plugin;
		if (instance == null) {
			instance = this;
		}
	}

	public static ChatColorManager getInstance() {
		return instance;
	}

	public String getDefaultColor() {
		return getConfig().getString("default-color", TextFormat.WHITE.toString());
	}

	public String getDefaultOpColor() {
		return getConfig().getString("default-op-color", TextFormat.AQUA.toString());
	}

	public void setColor(String player, char colorcode) {
		if (!((colorcode >= '0' && colorcode <= '9') || (colorcode >= 'a' && colorcode <= 'z')
				|| (colorcode >= 'A' && colorcode <= 'Z'))) {
			throw new IllegalArgumentException("Incorrect color code");
		}
		getColorCodeConfig().set(player.toLowerCase(), new String(new char[]{TextFormat.ESCAPE, colorcode}));
	}

	public String getColor(String player) {
		return getColorCodeConfig().getString(player.toLowerCase(), Server.getInstance().isOp(player) ? getDefaultOpColor() : getDefaultColor());
	}

	private Config getConfig() {
		return getDB().getConfig();
	}
	
	private DataBase getDB() {
		return plugin.getDB();
	}

	private Config getColorCodeConfig() {
		return getDB().getDB("colorcode");
	}
}
