package me.nust.project.games;

import java.util.HashMap;


import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import me.nust.project.GameManager;
import me.nust.project.Main;
import me.nust.project.player.PlayerData;
import me.nust.project.player.PlayerState;
import me.nust.project.utils.CC;


public class HoneyListeners implements Listener {
	
	private Main plugin = Main.getInstance();
	private GameManager game = this.plugin.getGameManager();

	private HashMap<Player, Integer> breakAmount = new HashMap<Player, Integer>();
	private HashMap<Player, Integer> breakTotal = new HashMap<Player, Integer>();
	
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		
		if (game.getRound()==2) {
			e.setCancelled(true);

			switch (e.getBlock().getType()) {
			
			case WOOL:
				@SuppressWarnings("deprecation") 
				byte x = e.getBlock().getData();
				
				switch (x) {
				case (byte)8:
					addData(e.getPlayer(), 15);
					break;
				case 0:
					addData(e.getPlayer(), 19);
					break;
				case 1:
					addData(e.getPlayer(), 15);
					break;
				case 4:
					addData(e.getPlayer(), 11);
					break;
				
				}
				e.getBlock().setType(Material.AIR);

				
				break;			
			default:
				this.plugin.getPlayerManager().addSpec(e.getPlayer(), false);
				break;
			};
			
			;
		}
		
	}
	
	
	@SuppressWarnings("deprecation")
	public void addData(Player p, int x) {
		
		if (!breakAmount.containsKey(p)) {
			breakTotal.put(p, x);
			breakAmount.put(p, 0);
			x=x+1;
			p.sendMessage(CC.tl("&eYou have &6&l" + x + " &eblocks to break"));
			updateData(p);
		} else if (breakAmount.get(p) == breakTotal.get(p)) {
			p.sendTitle(CC.tl("&a&lYou Pass"), "Waiting for others to finish");
			PlayerData pd = this.plugin.getPlayerManager().getPlayerData().get(p.getUniqueId());
			pd.setState(PlayerState.WAITING);
			breakAmount.remove(p);
			breakTotal.remove(p);
		} else {
			updateData(p);
		}
		
	}
	
	public void updateData(Player p) {
		breakAmount.put(p, breakAmount.get(p)+1);
		//p.sendMessage(breakAmount.get(p) + "");
	}
	
	
	

}
