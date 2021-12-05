package me.jxydev.xraydetector.checks;

import java.util.LinkedList;

public class CheckManager {

	private static LinkedList<Check> checks = new LinkedList<Check>();
	
	public static void registerCheck(Check check) {
		if(checks.contains(check)) return;
		checks.add(check);
	}
	
	public static Object[] getRegisteredChecks() {
		return checks.toArray();
	}
	
}
