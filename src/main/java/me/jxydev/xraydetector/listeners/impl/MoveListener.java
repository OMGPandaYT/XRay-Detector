package me.jxydev.xraydetector.listeners.impl;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.jxydev.xraydetector.events.impl.XRDMove;
import me.jxydev.xraydetector.listeners.ListenerManager;

public class MoveListener implements Listener {
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		
		ListenerManager.onEvent(new XRDMove(e));
		
	}

}
