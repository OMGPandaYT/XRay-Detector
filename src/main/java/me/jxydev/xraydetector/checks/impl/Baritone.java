package me.jxydev.xraydetector.checks.impl;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.jxydev.xraydetector.XRD;
import me.jxydev.xraydetector.checks.Check;
import me.jxydev.xraydetector.data.PlayerData;
import me.jxydev.xraydetector.events.Event;
import me.jxydev.xraydetector.events.impl.XRDBlockBreak;
import me.jxydev.xraydetector.util.RotationUtils;

public class Baritone extends Check {

	public float lookDifference = 0; // DEFAULT: 2
	
	public Baritone(FileConfiguration config) {
		
		super("Baritone", true, config, false);
		
		lookDifference = (float)config.getDouble(path + "look-difference");
		
	}
	
	public void onEvent(Event e) {
		
		if(e instanceof XRDBlockBreak) {
			
			XRDBlockBreak bb = (XRDBlockBreak)e;
			
			PlayerData pd = bb.playerData;
			Player p = pd.getPlayer();
			
			if(RotationUtils.isYawClose(pd.lastLastYaw, p.getLocation().getYaw(), lookDifference) && !RotationUtils.isYawClose(pd.lastLastYaw, pd.lastYaw, lookDifference)) {
				pd.baritone++;
				if(pd.baritone >= 1.8) {
					
					flag(pd, "&c" + p.getName() + " &eis near definetly using baritone.", true);
					
				} else {
					
					flag(pd, "&c" + p.getName() + " &e is probably using baritone.", false);
					
				}
				
			} else {
				
				pd.baritone -= pd.baritone > 0.01 ? 0.01 : 0;
				
			}
			
			boolean pitch = (RotationUtils.isPitchClose(pd.lastPos.getPitch(), p.getLocation().getPitch(), 0.01f) &&
					RotationUtils.isNumberAround(p.getLocation().getPitch(), 26, 0.01f));
			
			boolean yaw = (RotationUtils.isNumberAround((float)Math.ceil((pd.lastPos.getYaw()) % 360f) % 90f, 0f, 1f)) &&
					(RotationUtils.isNumberAround((float)Math.ceil((pd.lastPos.getYaw()) % 360f) % 90f, 0f, 1f));
			
			if(
					pitch &&
					yaw
			) {
				
				pd.baritone2++;
				if(pd.baritone2 > 4) {
					
					pd.baritone2 -= 2;
					flag(pd, "&c" + p.getName() + " &emight be using baritone.", true);
					
				}
				
			} else {
				
				pd.baritone2 -= pd.baritone2 > 0.1 ? 0.1 : 0;
				
			}
			
			pd.lastLastYaw = pd.lastYaw;
			pd.lastYaw = p.getLocation().getYaw();
			
		}
		
	}
	
}
