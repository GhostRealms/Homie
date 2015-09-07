package net.ghostrealms.homie.commands;

import net.ghostrealms.homie.Homie;
import net.ghostrealms.lib.GhostMessage;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class MarkExecutor implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;


		if(!player.hasPermission("homie.mark"))
		{
			GhostMessage.messagePlayer(player, "&cSorry, you don't have permission", true);
			return true;
		}

		Location temp = player.getLocation();
		
		Map<String, String> data = new HashMap<String, String>();
		
		data.put("world", temp.getWorld().getName());
		data.put("x", String.valueOf(temp.getX()));
		data.put("y", String.valueOf(temp.getY()));
		data.put("z", String.valueOf(temp.getZ()));
		data.put("yaw", String.valueOf(temp.getYaw()));
		data.put("pitch", String.valueOf(temp.getPitch()));
		
		String key = "Homie:temp:" + player.getUniqueId().toString();
		Homie.instance.redis.hmset(key, data);
		
		player.sendMessage(ChatColor.AQUA + "Your temporary location has been set! You can use /return to come back.");
		return true;
	}

}
