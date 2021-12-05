package me.jxydev.xraydetector.events.impl;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;

import me.jxydev.xraydetector.events.Event;

public class XRDMove extends Event{

	public Location from, to;
	public PlayerMoveEvent event;
	public Player player;
	
	public XRDMove(PlayerMoveEvent e) {
		
		super(e.getPlayer());
		
		this.event = e;
		
		this.to = event.getTo();
		this.from = event.getFrom();
		
		this.player = event.getPlayer();
		this.playerData.lastPos = from;
		
	}
	
}
