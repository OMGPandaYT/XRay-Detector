package me.jxydev.xraydetector.events.impl;

import org.bukkit.event.block.BlockBreakEvent;

import me.jxydev.xraydetector.events.Event;

public class XRDBlockBreak extends Event{

	public BlockBreakEvent event;
	
	public XRDBlockBreak(BlockBreakEvent e) {
		
		super(e.getPlayer());
		
		this.event = e;
		
	}
	
}
