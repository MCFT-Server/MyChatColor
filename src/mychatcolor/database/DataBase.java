package mychatcolor.database;

import java.io.File;

import cn.nukkit.utils.Config;
import mychatcolor.Main;

public class DataBase extends BaseDB<Main> {

	public DataBase(Main plugin) {
		super(plugin);
		setPrefix("[채팅색]");
		
		initDB("colorcode", new File(plugin.getDataFolder(), "colorcode.json"), Config.JSON);
		initConfig();
	}
	
	private void initConfig() {
		saveDefaultConfig();
	}

}
