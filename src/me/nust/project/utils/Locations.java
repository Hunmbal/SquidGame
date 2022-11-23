package me.nust.project.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class Locations {

	
	static World world = Bukkit.getWorld("world");
	public static final Location lounge = new Location(world, -185.319, 3, 186.391, -89.5f, 2.1f);
	
	public static final Location sumoLobby = new Location(world, -199.5, 31, 126.5, -179.5f, 6.2f);
	public static final Location sumo1 = new Location(world, -204.5, 31, 90.5, -89.5f, 1.8f);
	public static final Location sumo2 = new Location(world, -194.5, 31, 90.5, 89.5f, 1.8f);
	
	public static final Location lms = new Location(world, -112.5, 21, 92.5, -90.2f, 0.1f);
	
	public static final Location glass = new Location(world, -174.5, 26, -25.5, 90f, 7.8f);
	
	public static final Location light = new Location(world, -35, 4, 167.771, 0.7f, 2.9f);
	public static final Location honey = new Location(world, -298, 6, 200);
	//Location(world, -290, 6, 200) .... goes -8 blocks in x
	/*public static final Location honeycomb = new Location(world, -185.319, 3, 186.391, -89.5f, 2.1f);
	public static final Location sumo = new Location(world, -185.319, 3, 186.391, -89.5f, 2.1f);
	public static final Location tiles = new Location(world, -185.319, 3, 186.391, -89.5f, 2.1f);
	public static final Location final = new Location(world, -185.319, 3, 186.391, -89.5f, 2.1f);*/
	//public static final Location lobby = new Location(world, -51, 8, 46, -89.9f, 9.8f);
	
	public static final Location hair1 = new Location(world, -34, 15, 238);
	public static final Location hair2 = new Location(world, -37, 13, 238);
	
}
