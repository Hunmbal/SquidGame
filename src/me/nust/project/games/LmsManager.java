package me.nust.project.games;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.nust.project.GameManager;
import me.nust.project.Main;
import me.nust.project.player.PlayerData;
import me.nust.project.player.PlayerState;
import me.nust.project.utils.CC;
import me.nust.project.utils.Items;
import me.nust.project.utils.Locations;
import me.nust.project.utils.MatchCountdown;

public class LmsManager {
	
	private Main plugin = Main.getInstance();
    private GameManager game = this.plugin.getGameManager();
	
	
	public void startLms() {
		game.setGameLobby(true);
		for (Player p: Bukkit.getOnlinePlayers()) {
			
			p.teleport(Locations.lms);
			
			if (game.getPlaying().contains(p)) {
				p.getInventory().addItem(Items.axe);
				p.getInventory().addItem(Items.apple);
			}
		}
		
		new MatchCountdown(5, plugin) {      
			@Override                      
			public void count(int current) {
				Bukkit.broadcastMessage(CC.tl("&eGame starting in " + current));
				if (current == 1) {
					startDM();
				}
			}

			                           
		}.start();
	}
	
	private void startDM() {
		for (Player p: this.game.getPlaying()) {
			PlayerData pd = this.plugin.getPlayerManager().getPlayerData().get(p.getUniqueId());
			pd.setState(PlayerState.PLAYING);
		}
		game.setGameLobby(false);
		
	}   

}
