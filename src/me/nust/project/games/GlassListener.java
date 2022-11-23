package me.nust.project.games;

import org.bukkit.Location;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;

import me.nust.project.GameManager;
import me.nust.project.Main;

public class GlassListener implements Listener {
	
	private Main plugin = Main.getInstance();
    private GameManager game = this.plugin.getGameManager();
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		if (game.getRound() == 4 && game.getPlaying().contains(e.getPlayer())) {
			Location loc = e.getPlayer().getLocation();
			loc.setY(loc.getY()-22);
			if (loc.getBlock().getType() == Material.GLOWSTONE) {
				//remove tiles
				loc = e.getPlayer().getLocation();				
				loc.setY(loc.getY()-1);
				loc.getBlock().setType(Material.AIR);
				loc.setZ(loc.getZ()-1);
				loc.getBlock().setType(Material.AIR);
				loc.setZ(loc.getZ()+2);
				loc.getBlock().setType(Material.AIR);
			}
			
		}
	}
	






	@EventHandler
	public void onFall(EntityDamageEvent e) {
		if (game.getRound() == 4 && game.getPlaying().contains((Player)e.getEntity())) {
			e.setCancelled(true);
			if (e.getCause()==DamageCause.FALL) {
				plugin.getPlayerManager().addSpec((Player)e.getEntity(), false);
			}
			
		}
	}
	

	
	

}
