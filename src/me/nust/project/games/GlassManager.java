package me.nust.project.games;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.connorlinfoot.actionbarapi.ActionBarAPI;

import lombok.Getter;
import lombok.Setter;
import me.nust.project.GameManager;
import me.nust.project.Main;
import me.nust.project.player.PlayerData;
import me.nust.project.player.PlayerState;
import me.nust.project.utils.CC;
import me.nust.project.utils.Locations;
import me.nust.project.utils.MatchCountdown;

@Getter @Setter
public class GlassManager {
	
	private Main plugin = Main.getInstance();
	private GameManager game = this.plugin.getGameManager();
	private boolean deathMode;
	
	public void startGlass() {
		
		deathMode=false;
		Bukkit.broadcastMessage(CC.tl("&c&lCross the bridge in 60 seconds or you will die"));
		Bukkit.broadcastMessage(CC.tl("&a&lDeathMode will start after 30 seconds &7(YOu will be able to hit each other)"));
		
		for (Player p: Bukkit.getOnlinePlayers()) {
			PlayerData pd = plugin.getPlayerManager().getPlayerData().get(p.getUniqueId());
			
			p.teleport(Locations.glass);
			
			if (game.getPlaying().contains(p)) {
				pd.setState(PlayerState.PLAYING);
			}
		}
		
		//Countdown of the game
		new MatchCountdown(60, this.plugin) {      
			@Override                      
			public void count(int current) {		
				if (current==40) {
					Bukkit.broadcastMessage(CC.tl("&cDeathmode starting in 10seconds"));
				} else if (current==30) {
					ActionBarAPI.sendActionBarToAllPlayers("§c§lDeathMode §7- You can now hit each other", 195);
					deathMode(true);
				} else if (current==20) {
					ActionBarAPI.sendActionBarToAllPlayers("§a§lSafeMode §7- You can no longer hit each other", 60);
					deathMode(false);
				} else if (current==10) {
					Bukkit.broadcastMessage(CC.tl("&e10seconds remaining"));
				} else if (current==1) {
					endGlassGame();
				}
			}	                         
		}.start();
			
	}
	private void endGlassGame() {
		for (Player p: game.getPlaying()) {
			Location loc = p.getLocation();
			loc.setY(loc.getY()-2);
			if (loc.getBlock().getType()!=Material.NETHER_BRICK) {
				plugin.getPlayerManager().addSpec(p, false);
			}
			
		}
		game.endGame();
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "clone -185 53 -29 -211 51 -23 -212 24 -29");
	}     
	
	public void deathMode(boolean x) {
		this.plugin.getGlassManager().setDeathMode(x);
	}

}
