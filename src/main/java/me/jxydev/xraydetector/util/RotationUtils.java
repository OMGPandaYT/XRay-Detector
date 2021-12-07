package me.jxydev.xraydetector.util;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

import me.jxydev.xraydetector.data.PlayerData;

public class RotationUtils {

	public static float[] getRotationsFromEntityToEntity(PlayerData e1, PlayerData e2) {
		
		double deltaX;
	    double deltaY;
	    double deltaZ;
	    if(e2.getEntity() == null) {
	    	Location e = e2.getPlayer().getLocation();
	    	deltaX = e.getX() + e.getX() - e2.lastPos.getX() - e1.lastPos.getX();
	    	deltaY = e.getY() - 3.5D + e2.getPlayer().getEyeHeight() - e1.getPlayer().getLocation().getY() + e1.getPlayer().getEyeHeight();
	    	deltaZ = e.getZ() + e.getZ() - e2.lastPos.getZ() - e1.lastPos.getZ();
	    } else {
	    	Location e = e2.getEntity().getLocation();
	    	deltaX = e.getX() + e.getX() - e2.lastPos.getX() - e1.lastPos.getX();
	    	deltaY = e.getY() - 3.5D + ((LivingEntity) e2.getEntity()).getEyeHeight() - e1.getPlayer().getLocation().getY() + e1.getPlayer().getEyeHeight();
	    	deltaZ = e.getZ() + e.getZ() - e2.lastPos.getZ() - e1.lastPos.getZ();
	    }
	    double distance = Math.sqrt(Math.pow(deltaX, 2.0D) + Math.pow(deltaZ, 2.0D));
	    float yaw = (float)Math.toDegrees(-Math.atan(deltaX / deltaZ));
	    float pitch = (float)-Math.toDegrees(Math.atan(deltaY / distance));
	    if (deltaX < 0.0D && deltaZ < 0.0D) {
	      yaw = (float)(90.0D + Math.toDegrees(Math.atan(deltaZ / deltaX)));
	    } else if (deltaX > 0.0D && deltaZ < 0.0D) {
	      yaw = (float)(-90.0D + Math.toDegrees(Math.atan(deltaZ / deltaX)));
	    } 
	    return new float[] { yaw, pitch };
	}

	public static boolean isYawClose(float d1, float d2, float dist) {
		return isNumberAround(d1, d2, dist);
	}

	public static boolean isPitchClose(float d1, float d2, float dist) {
		return isNumberAround(d1, d2, dist);
	}

	public static boolean isNumberAround(float d1, float d2, float dist) {
		return Math.abs(d1 - d2) <= dist;
	}
	
}
