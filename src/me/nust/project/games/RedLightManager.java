package me.nust.project.games;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.connorlinfoot.actionbarapi.ActionBarAPI;
import com.xxmicloxx.NoteBlockAPI.NBSDecoder;
import com.xxmicloxx.NoteBlockAPI.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.Song;

import lombok.Getter;
import lombok.Setter;
import me.nust.project.GameManager;
import me.nust.project.Main;
import me.nust.project.utils.CC;
import me.nust.project.utils.Locations;
import me.nust.project.utils.MatchCountdown;

@SuppressWarnings("deprecation") @Getter @Setter
public class RedLightManager {

	private Main plugin = Main.getInstance();
	private GameManager game = this.plugin.getGameManager();
	
	private boolean redLight = false;
	private int counter = 0;
	
	Song song = NBSDecoder.parse(new File(plugin.getDataFolder()+ "/red.nbs"));
	RadioSongPlayer rsp = new RadioSongPlayer(song);

	// \plugins
	// C:\\Users\\LENOVO\\Desktop\\Server\\plugins\\red.nbs

	public void startRedLight() {
		
		game.setRound(1);
		for(Player p: Bukkit.getOnlinePlayers()) {
				
				p.teleport(Locations.light);	
				rsp.addPlayer(p);
		}	
		
		//COuntdown--------
		new MatchCountdown(5, plugin) {      
			@Override                      
			public void count(int current) {
				Bukkit.broadcastMessage(CC.tl("&eGame starting in " + current));
				
			}                              
		}.start();	
		
		
		
		
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
            	Bukkit.broadcastMessage(CC.tl("&c&l\nWATCH THE TOP OF YOUR SCREEN !!!! GOOOO\n"));
        		
        		for(Player p: game.getPlaying()) {
        			p.teleport(Locations.light);
        		}
        		//Main theme------
        		changeLight();
            }
        }, 100);
        
		
		
		
	}
	private void changeLight() {
		new MatchCountdown(50, plugin) {      
			@Override                      
			public void count(int time) {
				if (time % 10 == 0) {
					ActionBarAPI.sendActionBarToAllPlayers("§a§looooooooo GREEN LIGHT ooooooooo", 95);
					rsp.setPlaying(true);
					redLight = false;
					addHair();
					
				} else if (time % 5 == 0) {
					ActionBarAPI.sendActionBarToAllPlayers("§c§loooooooooo RED LIGHT oooooooooo", 95);
					redLight = true;
					removeHair();	
				}
				
				if (time==1) {
					redLight=false;
	            	game.endGame();
				}
			}                              
		}.start();	
		

		
		
	}
	
	
	public void addHair() {
		Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), 
				"fill -34 15 238 -37 13 238 coal_block");
	}
	
	public void removeHair() {
		Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), 
				"fill -34 15 238 -37 13 238 air");
	}
}
