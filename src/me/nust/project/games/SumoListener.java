package me.nust.project.games;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import me.nust.project.GameManager;
import me.nust.project.Main;

public class SumoListener implements Listener {
	
    private Main plugin = Main.getInstance();
    private GameManager game = this.plugin.getGameManager();

    
    @EventHandler
    public void onLava(EntityDamageEvent e) {
        
        if(game.getRound() == 3 && game.getPlaying().contains((Player)e.getEntity())) {
        	Player p = (Player)e.getEntity();
            
            if(e.getCause() == DamageCause.LAVA) {
            	
                plugin.getPlayerManager().addSpec(p, false);
                
                if (this.plugin.getSumoManager().getCurrent().getP1()==p) {
                	this.plugin.getSumoManager().endFight(this.plugin.getSumoManager().getCurrent().getP2());
                } else if (this.plugin.getSumoManager().getCurrent().getP2()==p) {
                	this.plugin.getSumoManager().endFight(this.plugin.getSumoManager().getCurrent().getP1());
                }
                
                
            }        
        }
        
    }
    

}