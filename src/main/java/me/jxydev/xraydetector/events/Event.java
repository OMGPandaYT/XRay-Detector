package me.jxydev.xraydetector.events;

import org.bukkit.entity.Player;

import me.jxydev.xraydetector.data.PlayerData;
import me.jxydev.xraydetector.data.PlayerDataManager;

public class Event {

	public PlayerData playerData;
	
	public Event(Player p) {
		playerData = PlayerDataManager.getPlayer(p);
	}
	
}
