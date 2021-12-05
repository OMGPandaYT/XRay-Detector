package me.jxydev.xraydetector.data;

import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.entity.Player;

public class PlayerDataManager {

	private static ConcurrentHashMap<Player, PlayerData> playerData = new ConcurrentHashMap<Player, PlayerData>();
	
	public static void createPlayer(Player p) {
		playerData.put(p, new PlayerData(p));
	}
	
	public static void deletePlayer(PlayerData pd) {
		playerData.remove(pd.getPlayer());
	}
	
	public static PlayerData getPlayer(Player p) {
		PlayerData pd = playerData.get(p);
		if(pd == null)
			return null;
		return pd;
	}
	
}