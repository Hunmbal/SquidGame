package me.nust.project.games;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.nust.project.GameManager;
import me.nust.project.Main;

public class RedLightListener implements Listener{
	
	private Main plugin = Main.getInstance();
	private GameManager game = this.plugin.getGameManager();
	
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		if (game.getRound() == 1 && game.getPlaying().contains(e.getPlayer())) {
			
			Location loc = e.getPlayer().getLocation();
			loc.setY(loc.getY()-2);			
			Material block = loc.getBlock().getType();
			
			if (this.plugin.getRedLightManager().isRedLight() && block == Material.SAND) {
				this.plugin.getPlayerManager().addSpec(e.getPlayer(), false);
			}
			
		}
	}
	

}
