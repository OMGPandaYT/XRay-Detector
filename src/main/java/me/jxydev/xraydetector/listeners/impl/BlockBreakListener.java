package me.jxydev.xraydetector.listeners.impl;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import me.jxydev.xraydetector.events.impl.XRDBlockBreak;
import me.jxydev.xraydetector.listeners.ListenerManager;

public class BlockBreakListener implements Listener {
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
	
		ListenerManager.onEvent(new XRDBlockBreak(e));
		
	}

}
