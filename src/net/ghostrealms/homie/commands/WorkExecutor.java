/**
 * 
 */
package net.ghostrealms.homie.commands;

import net.ghostrealms.homie.Homie;
import net.ghostrealms.lib.GhostMessage;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

public class WorkExecutor implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;


		if(!player.hasPermission("homie.work"))
		{
			GhostMessage.messagePlayer(player, "&cSorry, you don't have permission", true);
			return true;
		}

		String key = "Homie:work:" + player.getUniqueId().toString();

		Map<String, String> data = Homie.instance.redis.hgetAll(key);

		if(data.size() == 0)
		{
			player.sendMessage(ChatColor.RED + "You have not set your work yet. You may set it with /setwork");
			return true;
		}

		Location work  = new Location(Homie.instance.getServer().getWorld(data.get("world")),
				Double.parseDouble(data.get("x")),
				Double.parseDouble(data.get("y")),
				Double.parseDouble(data.get("z")),
				Float.parseFloat(data.get("yaw")),
				Float.parseFloat(data.get("pitch")));

		player.teleport(work);
		player.sendMessage(ChatColor.GREEN + "Welcome to work!");
		return true;
	}

}
