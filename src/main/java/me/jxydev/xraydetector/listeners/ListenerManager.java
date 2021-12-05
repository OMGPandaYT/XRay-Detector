package me.jxydev.xraydetector.listeners;

import me.jxydev.xraydetector.checks.Check;
import me.jxydev.xraydetector.checks.CheckManager;
import me.jxydev.xraydetector.events.Event;

public class ListenerManager {

	public static void onEvent(Event e) {
		for(Object c : CheckManager.getRegisteredChecks()) {
			((Check)c).onEvent(e);
		}
	}
	
}
