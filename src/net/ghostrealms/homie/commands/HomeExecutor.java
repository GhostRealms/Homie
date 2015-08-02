package net.ghostrealms.homie.commands;

import java.util.Map;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.ghostrealms.homie.Homie;

public class HomeExecutor implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		String key = "Homie:home:" + player.getUniqueId().toString();
		
		Map<String, String> data = Homie.instance.redis.hgetAll(key);
		
		if(data.size() == 0)
		{
			player.sendMessage("You have not set your home yet. You may set it with /sethome");
			return true;
		}
		
		Location home  = new Location(Homie.instance.getServer().getWorld(data.get("world")),
				Double.parseDouble(data.get("x")),
				Double.parseDouble(data.get("y")),
				Double.parseDouble(data.get("z")),
				Float.parseFloat(data.get("yaw")),
				Float.parseFloat(data.get("pitch")));
		
		player.teleport(home);
		player.sendMessage("Welcome home!");
		return true;
	}

}
