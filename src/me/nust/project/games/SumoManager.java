package me.nust.project.games;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
//import org.bukkit.Location;
import org.bukkit.entity.Player;

import lombok.Getter;
import lombok.Setter;
import me.nust.project.GameManager;
import me.nust.project.Main;
import me.nust.project.player.PlayerData;
import me.nust.project.player.PlayerState;
//import me.nust.project.player.PlayerData;
import me.nust.project.utils.CC;
import me.nust.project.utils.Locations;
import me.nust.project.utils.Match;
import me.nust.project.utils.MatchCountdown;

@Getter @Setter
public class SumoManager {
	
	private Main plugin = Main.getInstance();
	private GameManager game = this.plugin.getGameManager();

	
	private List<Match> mathces = new ArrayList<>();
	private Match current = new Match();
	
	private int index = 0;

	
	public void startSumo() {
		
		
		for(Player p: Bukkit.getOnlinePlayers()) {
			p.teleport(Locations.sumoLobby);	
		
		}
		

		createMathes();
		StartFight();
	}
	
	
	public void StartFight() {
		
		try {
			current = this.mathces.get(index);
		} catch (Exception e) {
			game.endGame();
			return;
		}
		
	    
		
	    
		
			if(current!=null && current.getP1() != null && current.getP2() != null) {
				
				//Countdown of the game
				new MatchCountdown(5, plugin) {      
					@Override                      
					public void count(int time) {
						current.getP1().sendMessage(CC.tl("&eYour fight starts in &6" + time + "&e seconds"));
						current.getP2().sendMessage(CC.tl("&eYour fight starts in &6" + time + "&e seconds"));
						
						if (time==1) {
							TeleportSumo(current.getP1(),current.getP2());
						}
					}                              
				}.start();	
			} else {
				game.endGame();
			}

	}
	
	public void endFight(Player winner) {
		
		winner.teleport(Locations.sumoLobby);
		PlayerData pd = this.plugin.getPlayerManager().getPlayerData().get(winner.getUniqueId());
		pd.setState(PlayerState.WAITING);
		winner.setHealth(20d);
		this.index++;
		StartFight();
	}
	
	
	
	void TeleportSumo(Player p1, Player p2) {
		//private List<Player> = game.getPlaying();
		p1.teleport(Locations.sumo1);
		p2.teleport(Locations.sumo2);
		
		PlayerData pd = this.plugin.getPlayerManager().getPlayerData().get(p1.getUniqueId());
		PlayerData pd2 = this.plugin.getPlayerManager().getPlayerData().get(p2.getUniqueId());
		pd.setState(PlayerState.PLAYING);
		pd2.setState(PlayerState.PLAYING);
	}



	
	public void createMathes() {
		for (int i =0; i < game.getPlaying().size(); i=i+2) {
			
			if (game.getPlaying().get(i) != null && game.getPlaying().get(i+1)!= null) {
				Match match = new Match();
				match.setP1(game.getPlaying().get(i));
				match.setP2(game.getPlaying().get(i+1));
				
				this.getMathces().add(match);
			}
			
		}
	}


}