package me.nust.project.player;

import org.bukkit.entity.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.nust.project.GameManager;
import me.nust.project.Main;

@SuppressWarnings("deprecation")
public class PlayerListener implements Listener{
	
	private Main plugin = Main.getInstance();
	private GameManager game = this.plugin.getGameManager();
	
	@EventHandler
	public void joinEvent(PlayerJoinEvent e) {
		e.setJoinMessage(" ");
		Player p = e.getPlayer();
		this.plugin.getPlayerManager().createData(p);
	}
	
	@EventHandler
	public void leaveEvent(PlayerQuitEvent e) {
		e.setQuitMessage(" ");
		this.plugin.getPlayerManager().removeData(e.getPlayer().getUniqueId());
	}
	
	
	@EventHandler
	public void breakEvent(BlockBreakEvent e) {
		if (game.getRound()==2 || e.getPlayer().getName().equals("UltimateMustafaa")) {
			return;
		} else {
			e.setCancelled(true);
		}

	}


	@EventHandler
	public void hitEvent(EntityDamageByEntityEvent e) {
		if (this.game.isGameLobby()) {
			e.setCancelled(true);
		} else {
			
			if (game.getRound()!=5) {
				e.setDamage(0);
			} 
			
			PlayerData pd = this.plugin.getPlayerManager().getPlayerData().get(e.getEntity().getUniqueId());
			if (pd.getState()==PlayerState.WAITING) {
				e.setCancelled(true);
			}
			
			if (game.getRound() == 4 && !this.plugin.getGlassManager().isDeathMode()) {
				
				e.setCancelled(true);
				
				
			}
		}
		
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onChat(PlayerChatEvent e) {
		e.setCancelled(true);
	}
	
	

}
