package me.jxydev.xraydetector.checks.impl;

import org.bukkit.configuration.file.FileConfiguration;

import me.jxydev.xraydetector.checks.Check;
import me.jxydev.xraydetector.data.PlayerData;
import me.jxydev.xraydetector.events.Event;
import me.jxydev.xraydetector.events.impl.XRDBlockBreak;

public class MineNotify extends Check {

	public MineNotify(FileConfiguration config) {
		super("MineNotify", false, config);
	}
		
	public void onEvent(Event e) {
		
		if(e instanceof XRDBlockBreak) {
			
			XRDBlockBreak bb = (XRDBlockBreak)e;
			
			PlayerData q = bb.playerData;
			
			if(q.lastBlockBroken == null) {block(bb);return;}

			if(isDiamond(bb.event.getBlock().getType())) {
				q.diamondsMined++;
			}
			
			if(!isDiamond(q.lastBlockBroken) && isDiamond(bb.event.getBlock().getType())) {
				
				q.timeSinceDiamond = System.currentTimeMillis() - q.timeSinceDiamond;
				
			} else if (isDiamond(q.lastBlockBroken) && !isDiamond(bb.event.getBlock().getType())) {
				
				flag(q, "&c" + q.getPlayer().getName() + " &efound &6" + q.diamondsMined + " diamonds &ein &5" + formatMillis(q.timeSinceDiamond) + "&e.", false);
				
				q.diamondsMined = 0;
				q.timeSinceDiamond = System.currentTimeMillis();
				
			}
			
			block(bb);
			
		}
		
	}
	
	public String formatMillis(long millis) {
		
		float seconds = millis / 1000f;
		
		if(seconds > 60) {
			
			float minutes = (float)Math.floor(seconds / 60);
			
			seconds -= minutes * 60;
			
			if(minutes > 60) {
				
				float hours = (float)Math.floor(minutes / 60);
				
				minutes -= hours * 60;
				
				if(hours > 24) {
					return "over 24 hours";
				} else return hours + " hour" + (hours != 1 ? "s" : "") + " and " + minutes + " minute" + (minutes != 1 ? "s" : "") + " and " + seconds + " second" + (seconds != 1 ? "s" : "");
				
			} else return minutes + " minute" + (minutes != 1 ? "s" : "") + " and " + seconds + " second" + (seconds != 1 ? "s" : "");
			
		} else return seconds + " second" + (seconds != 1 ? "s" : "");
		
	}
	
	public void block(XRDBlockBreak bb) {
		bb.playerData.lastBlockBroken = bb.event.getBlock().getType();
	}
	
}
