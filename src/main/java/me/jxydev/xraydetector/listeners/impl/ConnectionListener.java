package me.jxydev.xraydetector.listeners.impl;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.jxydev.xraydetector.data.PlayerData;
import me.jxydev.xraydetector.data.PlayerDataManager;

public class ConnectionListener implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		
		PlayerData pd = PlayerDataManager.createPlayer(e.getPlayer());
		
		boolean perm = e.getPlayer().hasPermission("xrd.notify");
		
		pd.alert = perm;
		pd.notify = perm;
		
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		
		PlayerDataManager.deletePlayer(PlayerDataManager.getPlayer(e.getPlayer()));
		
	}

}
