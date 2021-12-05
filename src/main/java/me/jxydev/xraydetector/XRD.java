package me.jxydev.xraydetector;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.jxydev.xraydetector.checks.impl.MineNotify;
import me.jxydev.xraydetector.checks.impl.MinePattern;
import me.jxydev.xraydetector.checks.impl.MineSight;
import me.jxydev.xraydetector.command.AlertCommand;
import me.jxydev.xraydetector.command.NotifyCommand;
import me.jxydev.xraydetector.data.PlayerData;
import me.jxydev.xraydetector.data.PlayerDataManager;
import me.jxydev.xraydetector.listeners.impl.BlockBreakListener;
import me.jxydev.xraydetector.listeners.impl.ConnectionListener;
import me.jxydev.xraydetector.listeners.impl.MoveListener;
import net.md_5.bungee.api.ChatColor;

public class XRD extends JavaPlugin {

	public static String prefix = "&7[&eXRD&7] &e";
	public String startup = "XRay-Detector is now active.", close = "XRay-Detector is no longer active.";
	
	public static XRD instance;
	
	public void onEnable() {
		
		instance = this;
		
		log("Loading listeners...");
		
		getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
		getServer().getPluginManager().registerEvents(new MoveListener(), this);
		getServer().getPluginManager().registerEvents(new ConnectionListener(), this);
		
		log("Listeners loaded.");
		
		log("Loading checks...");
		
		loadChecks();
		
		log("Checks loaded.");
		
		log("Loading commands...");
		
		getCommand("notify").setExecutor(new NotifyCommand());
		getCommand("alerts").setExecutor(new AlertCommand());
		
		log("Commands loaded.");
		
		log("Creating players...");
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			
			PlayerDataManager.createPlayer(p);
			
			PlayerData pd = PlayerDataManager.getPlayer(p);
			
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
	
	public void loadChecks() {
		
		new MineNotify();
		
		new MinePattern();
		new MineSight();
		
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
