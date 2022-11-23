package me.nust.project.games;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.nust.project.GameManager;
import me.nust.project.Main;
import me.nust.project.player.PlayerData;
import me.nust.project.player.PlayerState;
import me.nust.project.utils.CC;
import me.nust.project.utils.Locations;
import me.nust.project.utils.MatchCountdown;

public class HoneyManager {
	
	
	private Main plugin = Main.getInstance();
	private GameManager game = this.plugin.getGameManager();
	

	@SuppressWarnings("deprecation")
	public void startHoneyGame() {
		int i = -1;
		Location loc = Locations.honey.clone();
		for (Player p: Bukkit.getOnlinePlayers()) {
			PlayerData pd = plugin.getPlayerManager().getPlayerData().get(p.getUniqueId());
			
			if (game.getPlaying().contains(p)) {
				pd.setState(PlayerState.PLAYING);
				i++;
				
				
				if (i==0) {
					//nothing
				} else if (i < 4) {
					loc.setX(loc.getX()+(8));
				} else if (i == 4) {
					//Z1 ------------
					loc.setZ(loc.getZ()-(8));
					
					
				} else if (i < 8) {
					loc.setX(loc.getX()-(8));
				} else if (i == 8) {
					//Z2 ------------
					loc.setZ(loc.getZ()-(8));
					
					
				}else if (i < 12) {
					loc.setX(loc.getX()+(8));
				} else if (i == 12) {
					//Z3 ------------
					loc.setZ(loc.getZ()-(8));
					
					
				} else if (i < 16) {
					loc.setX(loc.getX()-(8));
				}			
				p.teleport(loc);
				pd.setState(PlayerState.PLAYING);
			} else {
				p.teleport(loc);
				pd.setState(PlayerState.SPECTATING);
			}	
			p.sendTitle(CC.tl("&e&lHoneyComb"), "Break wool to carve the shape");
		}	
		
		
		//COuntdown--------
		new MatchCountdown(65, plugin) {      
			@Override                      
			public void count(int time) {
				if (time == 1) {
					endHoneyGame();
				} else if (time == 10) {
					Bukkit.broadcastMessage(CC.tl("&610 seconds left"));
				}
				
				
			}                              
		}.start();
	}
	
	public void endHoneyGame() {
		for (Player p: Bukkit.getOnlinePlayers()) {
			PlayerData pd = plugin.getPlayerManager().getPlayerData().get(p.getUniqueId());
			if (game.getPlaying().contains(p) && pd.getState() == PlayerState.PLAYING) {
					this.plugin.getPlayerManager().addSpec(p, false);
				
			} 
			
		}
		game.endGame();
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "clone -358 9 122 -328 4 152 -301 4 173");
	}
	
	
	
}
