package me.jxydev.xraydetector.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.jxydev.xraydetector.XRD;
import me.jxydev.xraydetector.data.PlayerData;
import me.jxydev.xraydetector.data.PlayerDataManager;


public class AlertCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			PlayerData playerData = PlayerDataManager.getPlayer((Player)sender);
			if(playerData == null) return false;
			
			playerData.alert = !playerData.alert;
			XRD.sendMessage((Player) sender, "XRD will " + (playerData.alert ? "now" : "no longer") + " alert you about people who flag!");
		} else {
			XRD.log("You can not run this command!");
		}
		return true;
	}

}