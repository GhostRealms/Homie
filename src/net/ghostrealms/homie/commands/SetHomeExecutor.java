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

public class SetHomeExecutor implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;


		if(!player.hasPermission("homie.sethome"))
		{
			GhostMessage.messagePlayer(player, "&cSorry, you don't have permission", true);
			return true;
		}

		Location newHome = player.getLocation();
		
		Map<String, String> data = new HashMap<String, String>();
		
		data.put("world", newHome.getWorld().getName());
		data.put("x", String.valueOf(newHome.getX()));
		data.put("y", String.valueOf(newHome.getY()));
		data.put("z", String.valueOf(newHome.getZ()));
		data.put("yaw", String.valueOf(newHome.getYaw()));
		data.put("pitch", String.valueOf(newHome.getPitch()));
		
		String key = "Homie:home:" + player.getUniqueId().toString();
		Homie.instance.redis.hmset(key, data);
		
		player.sendMessage(ChatColor.AQUA + "Your home has been set! You can use /home to come back.");
		return true;
	}

}
