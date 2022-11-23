package me.nust.project;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;
import lombok.Setter;
import me.nust.project.commands.SkipWait;
import me.nust.project.games.GlassListener;
import me.nust.project.games.GlassManager;
import me.nust.project.games.HoneyListeners;
import me.nust.project.games.HoneyManager;
import me.nust.project.games.LmsListener;
import me.nust.project.games.LmsManager;
import me.nust.project.games.RedLightListener;
import me.nust.project.games.RedLightManager;
import me.nust.project.games.SumoListener;
import me.nust.project.games.SumoManager;
import me.nust.project.player.PlayerListener;
import me.nust.project.player.PlayerManager;
import net.minecraft.server.v1_8_R3.MinecraftServer;

@Getter @Setter
public class Main extends JavaPlugin {
	
	@Getter
	private static Main instance;
	private PlayerManager playerManager;
	private GameManager gameManager;
	
	private RedLightManager redLightManager;
	private HoneyManager honeyManager;
	private SumoManager sumoManager;
	private GlassManager glassManager;
	private LmsManager lmsManager;
	
	public void onEnable() {
		instance = this;


		System.out.print(ChatColor.RED.toString() + ChatColor.BOLD + "NUST Squid Game Project");

		registerManagers();
		registerListeners();
		registerCommands();
	}
	

	public void onDisable() {
		//nothing
	}
	


	
	private void registerManagers() {
		this.gameManager = new GameManager();
		this.playerManager = new PlayerManager();
		
		this.redLightManager = new RedLightManager();
		this.honeyManager = new HoneyManager();
		this.sumoManager = new SumoManager();
		this.glassManager = new GlassManager();
		this.lmsManager = new LmsManager();
	}
	
	private void registerListeners() {
		
		Arrays.<Listener>asList(new Listener[] { 
				new PlayerListener(),
				
				new RedLightListener(),
				new HoneyListeners(),
				new SumoListener(),
				new GlassListener(),
				new LmsListener()
		 }).forEach(x -> getServer().getPluginManager().registerEvents(x, (Plugin)this));
		
	}


	private void registerCommands() {
		 Arrays.<Command>asList(new Command[] { 
				 new SkipWait("skip")
		  }).forEach(command -> registerCommand(command, getName()));
	}
	  
	public void registerCommand(Command cmd, String fallbackPrefix) {
		 (MinecraftServer.getServer()).server.getCommandMap().register(cmd.getName(), fallbackPrefix, cmd);
	}




	

	
}
