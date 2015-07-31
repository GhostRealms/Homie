package net.ghostrealms.homie.commands;

import java.util.HashMap;
import java.util.Map;

import net.ghostrealms.homie.Homie;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetWorkExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		Location newWork = player.getLocation();
		
		Map<String, String> data = new HashMap<String, String>();
		
		data.put("world", newWork.getWorld().getName());
		data.put("x", String.valueOf(newWork.getX()));
		data.put("y", String.valueOf(newWork.getY()));
		data.put("z", String.valueOf(newWork.getZ()));
		data.put("yaw", String.valueOf(newWork.getYaw()));
		data.put("pitch", String.valueOf(newWork.getPitch()));
		
		String key = "Homie:work:" + player.getUniqueId().toString();
		Homie.instance.redis.hmset(key, data);
		
		player.sendMessage("Your work has been set! You can use /work to come back.");
		return true;
	}

}
