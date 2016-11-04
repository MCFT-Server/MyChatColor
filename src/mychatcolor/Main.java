package mychatcolor;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.plugin.PluginBase;
import mychatcolor.database.DataBase;
import mychatcolor.listener.EventListener;
import mychatcolor.manager.ChatColorManager;

public class Main extends PluginBase {
	private DataBase db;
	private EventListener listener;
	private ChatColorManager manager;
	
	@Override
	public void onEnable() {
		db = new DataBase(this);
		listener = new EventListener(this);
		manager = new ChatColorManager(this);
		
		getServer().getPluginManager().registerEvents(listener, this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		return listener.onCommand(sender, command, label, args);
	}
	
	public DataBase getDB() {
		return db;
	}
	
	public ChatColorManager getManager() {
		return manager;
	}
}
