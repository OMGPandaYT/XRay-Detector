package me.jxydev.xraydetector.data;

import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.entity.Player;

public class PlayerDataManager {

	private static ConcurrentHashMap<Player, PlayerData> playerData = new ConcurrentHashMap<Player, PlayerData>();
	
	public static PlayerData createPlayer(Player p) {
		PlayerData pd = new PlayerData(p);
		playerData.put(p, pd);
		return pd;
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