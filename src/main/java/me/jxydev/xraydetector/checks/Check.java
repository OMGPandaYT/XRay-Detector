package me.jxydev.xraydetector.checks;

import org.bukkit.Material;

import me.jxydev.xraydetector.XRD;
import me.jxydev.xraydetector.data.PlayerData;
import me.jxydev.xraydetector.events.Event;

public class Check {

	public Check() {
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

}
