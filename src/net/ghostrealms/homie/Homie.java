package net.ghostrealms.homie;

import net.ghostrealms.homie.commands.HomeExecutor;
import net.ghostrealms.homie.commands.SetHomeExecutor;
import net.ghostrealms.homie.commands.SetWorkExecutor;
import net.ghostrealms.homie.commands.WorkExecutor;

import org.bukkit.plugin.java.JavaPlugin;

import redis.clients.jedis.Jedis;

public class Homie extends JavaPlugin
{
	public static Homie instance;
	
	public Jedis redis;
	
	@Override
	public void onEnable()
	{		
		instance = this;
		this.getLogger().info("Enabled");
		
		this.getCommand("sethome").setExecutor(new SetHomeExecutor());
		this.getCommand("home").setExecutor(new HomeExecutor());
	
		this.getCommand("setwork").setExecutor(new SetWorkExecutor());
		this.getCommand("work").setExecutor(new WorkExecutor());
	/*
		try {
			Class.forName("redis.clients.jedis.Jedis").newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}*/
		redis = new Jedis("localhost");
	}
	
	@Override
	public void onDisable()
	{
		this.getLogger().info("Disabled");
	}
}
