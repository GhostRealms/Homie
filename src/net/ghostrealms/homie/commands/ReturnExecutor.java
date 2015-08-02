package net.ghostrealms.homie.commands;

import java.util.Map;

import net.ghostrealms.homie.Homie;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReturnExecutor implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		String key = "Homie:temp:" + player.getUniqueId().toString();
		
		Map<String, String> data = Homie.instance.redis.hgetAll(key);
		
		if(data.size() == 0)
		{
			player.sendMessage("You have not set your temporary location yet. You may set it with /mark");
			return true;
		}
		
		Location temp  = new Location(Homie.instance.getServer().getWorld(data.get("world")),
				Double.parseDouble(data.get("x")),
				Double.parseDouble(data.get("y")),
				Double.parseDouble(data.get("z")),
				Float.parseFloat(data.get("yaw")),
				Float.parseFloat(data.get("pitch")));
		
		player.teleport(temp);
		player.sendMessage("Returned to your temporary location!");
		return true;
	}

}
