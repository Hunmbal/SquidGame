package me.nust.project.utils;

import org.bukkit.ChatColor;

public class CC {
	
	public static String tl(String x) {
		x = ChatColor.translateAlternateColorCodes('&', x);
		return x;
		
	}

}
