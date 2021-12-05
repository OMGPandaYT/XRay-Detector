package me.jxydev.xraydetector.data;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class PlayerData {

	private Player player;
	private Entity entity;
	
	public PlayerData(Player player) {
		this.player = player;
		lastPos = player.getLocation();
	}
	
	public PlayerData(Entity entity) {
		this.entity = entity;
		lastPos = entity.getLocation();
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Entity getEntity() {
		return entity;
	}
	
	// data
	
	public Location lastPos;
	
	public Material lastBlockBroken;
	
	public long timeSinceDiamond = System.currentTimeMillis();
	
	public int diamondsMined, timesPattern, entityTime, entityTimeActive;
	
	public boolean notify = false, alert = false, entityTest = false;
	
	public PlayerData entityTestData;
	
}
