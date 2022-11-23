package me.nust.project;


import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import lombok.Getter;
import lombok.Setter;
import me.nust.project.player.PlayerData;
import me.nust.project.player.PlayerState;
import me.nust.project.utils.CC;
import me.nust.project.utils.Locations;

@Getter @Setter
public class GameManager {

	private Main plugin = Main.getInstance();

	
	private List<Player> playing = new ArrayList<Player>();
	private int alive;
	private int round = 0;
	private boolean gameLobby=true;
	

	
	public void inc() {alive +=1;}
	public void dec() {alive -=1;}
	
	public void addToGame(Player p) {
		playing.add(p);
	}
	public void removeDead(Player p) {
		playing.remove(p);
	}
		
	public void startGame(int x) {
		switch (x) {
		case 0:
			this.round=1;
			this.plugin.getRedLightManager().startRedLight();
			break;
		case 1:
			this.round=2;
			this.plugin.getHoneyManager().startHoneyGame();
			break;
		case 2:
			this.round=3;
			this.plugin.getSumoManager().startSumo();
			break;
		case 3:
			this.round=4;
			this.plugin.getGlassManager().startGlass();;
			break;
		case 4:
			this.round=5;
			this.plugin.getLmsManager().startLms();
			break;
		case 10:
			for (Player p: Bukkit.getOnlinePlayers()) {
				p.setGameMode(GameMode.SURVIVAL);
				this.removeDead(p);
				this.plugin.getPlayerManager().addToGame(p);
				
			}
			break;
		default:
			this.round=1;
			this.plugin.getRedLightManager().startRedLight();
			break;
		}
		this.plugin.getGameManager().gameLobby=false;

	}
	public void endGame() {
		for (Player p: Bukkit.getOnlinePlayers()) {
			if (playing.contains(p)) {
				PlayerData pd = this.plugin.getPlayerManager().getPlayerData().get(p.getUniqueId());
				pd.setState(PlayerState.WAITING);
				p.setWalkSpeed(0.2f);
				p.getInventory().clear();
			}
			p.teleport(Locations.lounge);

		}
		this.plugin.getGameManager().gameLobby=true;
	}

	
	@SuppressWarnings("deprecation")
	public void squidGamesWinner() {
		Player p = this.getPlaying().get(0);
		p.sendTitle(CC.tl("&a&lYOU WON!!!"), "You were the best of all contestants");
		Bukkit.broadcastMessage(CC.tl("&a&l" + p.getName() + " has won the squid games !!!!!!!!!!!"));
		p.playSound(p.getLocation(), Sound.FIREWORK_LARGE_BLAST, 10, 1);
		
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
            	Bukkit.broadcastMessage(CC.tl("\n &e&lThe game have been reset \n"));
            	startGame(10);
            }
        }, 60);
		
	}
	
}
