package me.jxydev.xraydetector.checks;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import me.jxydev.xraydetector.XRD;
import me.jxydev.xraydetector.data.PlayerData;
import me.jxydev.xraydetector.events.Event;

public class Check {

	protected String path;
	public boolean move;
	
	public Check(String name, boolean alert, FileConfiguration config, boolean move) {
		
		path = "checks." + (alert ? "alerts" : "notify") + "." + name.toLowerCase() + ".";
		this.move = move;
		if(config.getBoolean(path + "enabled"))
			CheckManager.registerCheck(this);
		
	}
	
	public void onEvent(Event e) {
		
	}
	
	public void flag(PlayerData p, String text, boolean fail) {
		
		if(fail && !p.alert)return;
		if(!fail && !p.notify)return;
		
		XRD.logPlayers(text);
		
	}
	
	public boolean isDiamond(Material type) {
		return type == Material.DIAMOND_ORE || type == Material.DEEPSLATE_DIAMOND_ORE;
	}
	
	public boolean isAir(Material type) {
		return type == Material.AIR || type == Material.CAVE_AIR || type == Material.VOID_AIR;
	}

}
