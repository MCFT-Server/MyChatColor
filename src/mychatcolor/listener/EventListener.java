package mychatcolor.listener;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.ConsoleCommandSender;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.lang.TranslationContainer;
import cn.nukkit.utils.TextFormat;
import mychatcolor.Main;
import mychatcolor.database.DataBase;
import mychatcolor.manager.ChatColorManager;

public class EventListener implements Listener {
	private Main plugin;

	public EventListener(Main plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		char colorcode = 'f';
		String player;
		if (args.length == 1) {
			if (sender instanceof ConsoleCommandSender) {
				sender.sendMessage(new TranslationContainer(TextFormat.RED + "%commands.generic.ingame"));
				return true;
			}
			colorcode = args[0].charAt(0);
			player = sender.getName();
		} else if (args.length == 2) {
			player = args[0];
			colorcode = args[1].charAt(0);
		} else {
			return false;
		}
		try {
			ChatColorManager.getInstance().setColor(player, colorcode);
			message(sender, TextFormat.ESCAPE + "" + colorcode + "" + colorcode + "로 색을 설정했습니다.");
		} catch (IllegalArgumentException e) {
			alert(sender, colorcode + "는 유효하지 않은 색깔코드입니다. 0~9, a~f 사이의 코드를 써주세요.");
		}
		return true;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onChat(PlayerChatEvent event) {
		event.setMessage(ChatColorManager.getInstance().getColor(event.getPlayer().getName()) + event.getMessage());
	}

	private DataBase getDB() {
		return plugin.getDB();
	}

	private void alert(CommandSender sender, String msg) {
		getDB().alert(sender, msg);
	}

	private void message(CommandSender sender, String msg) {
		getDB().message(sender, msg);
	}
}
