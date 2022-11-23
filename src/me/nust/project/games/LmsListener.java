package me.nust.project.games;

import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import me.nust.project.GameManager;
import me.nust.project.Main;

public class LmsListener implements Listener {
	
	private Main plugin = Main.getInstance();
    private GameManager game = this.plugin.getGameManager();
    
    @EventHandler
    public void onDeath(EntityDamageEvent e) {
    	if (game.getRound()==5 && (((Damageable)e.getEntity()).getHealth()- e.getFinalDamage())<= 0) {
    		e.setCancelled(true);
    		((Player)e.getEntity()).getInventory().clear();
    		this.plugin.getPlayerManager().addSpec((Player)e.getEntity(), false);
    		if (game.getPlaying().size()<=1) {
    			game.squidGamesWinner();
    		}
    	}
    	
    }

}
