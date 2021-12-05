package me.jxydev.xraydetector;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.jxydev.xraydetector.checks.impl.*;
import me.jxydev.xraydetector.command.*;
import me.jxydev.xraydetector.data.*;
import me.jxydev.xraydetector.listeners.impl.*;
import net.md_5.bungee.api.ChatColor;

public class XRD extends JavaPlugin {

	public static String prefix = "&7[&eXRD&7] &e";
	public String startup = "XRay-Detector is now active.", close = "XRay-Detector is no longer active.";
	
	public static XRD instance;
	
	public void onEnable() {
		
		instance = this;
		
		log("Loading listeners...");
		
		getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
		getServer().getPluginManager().registerEvents(new ConnectionListener(), this);
		getServer().getPluginManager().registerEvents(new MoveListener(), this);
		
		log("Listeners loaded.");
		
		log("Setting up configuration...");
		
		FileConfiguration config = getConfig();
		
		this.saveDefaultConfig();
		
		log("Configuration setup.");
		
		log("Loading checks...");
		
		loadChecks(config);
		
		log("Checks loaded.");
		
		log("Loading commands...");
		
		getCommand("notify").setExecutor(new NotifyCommand());
		getCommand("alerts").setExecutor(new AlertCommand());
		
		log("Commands loaded.");
		
		log("Creating players...");
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			
			PlayerData pd = PlayerDataManager.createPlayer(p);
			
			boolean perm = p.hasPermission("xrd.notify");
			
			pd.alert = perm;
			pd.notify = perm;
			
		}
		
		log("Created players.");
		
		log(startup);
		
	}
	
	public void onDisable() {
		
		log(close);
		
	}
	
	public void loadChecks(FileConfiguration config) {
		
		new MineNotify(config);
		
		new MinePattern(config);
		new MineSight(config);
		
	}
	
	public static void log(String text) {
		
		String finalText = prefix + text;
		
		instance.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', finalText));
		
	}

	public static void logPlayers(String string) {
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			
			if(p.hasPermission("xrd.alerts")) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + string));
			}
			
		}
		
	}

	public static void sendMessage(Player sender, String string) {
		
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + string));
	
	}
	
}
