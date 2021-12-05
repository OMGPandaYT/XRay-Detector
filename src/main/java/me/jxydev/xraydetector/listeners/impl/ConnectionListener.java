package me.jxydev.xraydetector.listeners.impl;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.jxydev.xraydetector.data.PlayerDataManager;

public class ConnectionListener implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		
		PlayerDataManager.createPlayer(e.getPlayer());
		
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		
		PlayerDataManager.deletePlayer(PlayerDataManager.getPlayer(e.getPlayer()));
		
	}

}
