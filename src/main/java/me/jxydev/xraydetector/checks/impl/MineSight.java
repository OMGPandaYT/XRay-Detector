package me.jxydev.xraydetector.checks.impl;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import me.jxydev.xraydetector.checks.Check;
import me.jxydev.xraydetector.data.PlayerData;
import me.jxydev.xraydetector.events.Event;
import me.jxydev.xraydetector.events.impl.XRDBlockBreak;
import me.jxydev.xraydetector.events.impl.XRDMove;
import me.jxydev.xraydetector.util.RotationUtils;

public class MineSight extends Check {

	double lookDifference = 0; // DEFAULT: 10
	double lookTime = 0; // DEFAULT: 20
 	double testTime = 0; // DEFAULT: 120
	double testTimeIncrease = 0; // DEFAULT: 180
	
	public MineSight(FileConfiguration config) {
		
		super("MineSight", true, config, true);
		
		lookDifference = config.getDouble(path + "look-difference");
		lookTime = config.getDouble(path + "look-time");
		
		testTime = config.getDouble(path + "test-time");
		testTimeIncrease = config.getDouble(path + "test-time-increase");
		
	}
	
	public void onEvent(Event e) {
		
		if(e instanceof XRDMove) {
			
			XRDMove move = (XRDMove)e;
			
			PlayerData pd = move.playerData;
			Player p = pd.getPlayer();
			Location l = p.getLocation();
			
			if(pd.entityTest) {
				
				float rotations[] = RotationUtils.getRotationsFromEntityToEntity(pd, pd.entityTestData);
				
				float yaw = l.getYaw() % 360;
				float pitch = l.getPitch();
				
				if(Math.abs(yaw - rotations[0]) < lookDifference && Math.abs(pitch - rotations[1]) < lookDifference) {
					
					if(++pd.entityTime > lookTime) {
						flag(pd, "&c" + p.getName() + " &eis possibly using Chams or X-Ray.", true);
						pd.entityTime -= 1;
					}
					
				} else {
					pd.entityTime -= pd.entityTime > 3 ? 3 : 0;
					pd.entityTimeActive++;
					if(pd.entityTime > lookTime / 2) {
						pd.entityTest = false;
						killEntity(pd);
					}
					
					if(pd.entityTimeActive > testTime + (pd.entityTime > lookTime / 2 ? testTimeIncrease : 0)) {
						pd.entityTest = false;
						killEntity(pd);
					}
				}
				
			}
			
		} else if (e instanceof XRDBlockBreak) {
			
			XRDBlockBreak bb = (XRDBlockBreak)e;
			
			PlayerData q = bb.playerData;
			
			if(isDiamond(bb.event.getBlock().getType()) && !q.entityTest) {
				
				boolean work = createEntity(q);
				
				if(work) {
					q.entityTest = true;
					q.entityTime = 0;
					q.entityTimeActive = 0;
				}
			} else if (isDiamond(bb.event.getBlock().getType())) {
				
				q.entityTime = 0;
				q.entityTimeActive = 0;
				
			}
			
		}
		
	}

	private boolean createEntity(PlayerData q) {
		return createEntity(q, 3);
	}
	
	private boolean createEntity(PlayerData q, int depth) {
		if(depth == 0)return false;
		Location loc = q.getPlayer().getLocation().clone().add(Math.random() * 10 - 5, 4, Math.random() * 10 - 5);
		
		if(loc.getBlock().getType() == Material.AIR || loc.clone().add(0, 1, 0).getBlock().getType() == Material.AIR) {
			
			return createEntity(q, depth - 1);
			
		} else {
		
			Entity e = q.getPlayer().getWorld().spawnEntity(loc, EntityType.VILLAGER);
			
			e.setInvulnerable(true);
			e.setGravity(false);
			
			q.entityTestData = new PlayerData(e);
		
			return true;
			
		}
		
	}

	private void killEntity(PlayerData pd) {
		pd.entityTestData.getEntity().setInvulnerable(false);
		((LivingEntity) pd.entityTestData.getEntity()).setHealth(0);
	}
	
}
