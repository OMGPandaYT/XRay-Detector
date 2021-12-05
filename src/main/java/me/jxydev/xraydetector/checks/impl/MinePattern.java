package me.jxydev.xraydetector.checks.impl;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockIterator;

import me.jxydev.xraydetector.checks.Check;
import me.jxydev.xraydetector.data.PlayerData;
import me.jxydev.xraydetector.events.Event;
import me.jxydev.xraydetector.events.impl.XRDBlockBreak;

public class MinePattern extends Check {

	double raycastLength = 0; // DEFAULT: 10
	
	public MinePattern(FileConfiguration config) {
		
		super("MinePattern", true, config, false);
		
		raycastLength = config.getDouble(path + "raycast-length");
		
	}
	
	public void onEvent(Event e) {
		
		if(e instanceof XRDBlockBreak) {
			
			XRDBlockBreak bb = (XRDBlockBreak)e;
			if(isDiamond(bb.event.getBlock().getType()))return;
			PlayerData q = bb.playerData;
			
			Player p = q.getPlayer();
			
			double yaw = p.getLocation().getYaw() % 360;
			
			if(yaw >= 150 || yaw <= -160)return;
			if(yaw <= -70 && yaw >= -120)return;
			if(yaw <= 20 && yaw >= -30)return;
			if(yaw >= 70 && yaw <= 130)return;
            Location location = p.getEyeLocation();
            BlockIterator blocksToAdd = new BlockIterator(location, 0, (int)(Math.floor(raycastLength)));
            int count = 0;
            boolean dia = false;
            while(blocksToAdd.hasNext()) {
            	
                Block b = blocksToAdd.next();
                if(isAir(b.getType()))break;
                if(isDiamond(b.getType())) {
                	
                	if(count > 4) {
                		
                		dia = true;
                		if(++q.timesPattern > 3) {
                			flag(q, "&c" + p.getName() + " &ehas a diagonal view to diamonds.", true);
                		}
                		
                	}
                	
                	break;
                }
                
                count++;
                
            }
            if(!dia)q.timesPattern -= q.timesPattern > 0.5 ? 0.5 : 0;
			
		}
		
	}
	
}
