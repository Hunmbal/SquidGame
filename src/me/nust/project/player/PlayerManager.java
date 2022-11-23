package me.nust.project.player;

import java.util.HashMap;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import lombok.Getter;
import me.nust.project.GameManager;
import me.nust.project.Main;
import me.nust.project.utils.CC;
import me.nust.project.utils.Locations;

@Getter
public class PlayerManager {
	
	private Main plugin = Main.getInstance();
	private GameManager game = this.plugin.getGameManager();
	private HashMap<UUID, PlayerData> playerData = new HashMap<UUID, PlayerData>();
	
	
	public void createData(Player p) {
		PlayerData pd = new PlayerData();
		this.playerData.put(p.getUniqueId(), pd);
		//On joining Waiting Lobby
		p.getInventory().clear();
		pd.setGlassIndex(-1);
		
		//check where to teleport
		if (game.getRound()==0) {
			this.addToGame(p);
		} else {
			this.addSpec(p, true);
		}
		
	}
	
	public void removeData(UUID uuid) {
		PlayerData pd = this.playerData.get(uuid);
		if (pd.getState() != PlayerState.SPECTATING) {
			game.dec();
		}
		this.playerData.remove(uuid);
	}
	
	
	public void addToGame(Player p) {
		PlayerData pd = this.playerData.get(p.getUniqueId());
		pd.setState(PlayerState.WAITING);
		p.getInventory().clear();
		
		game.addToGame(p);
		
		//teleport to waiting area
		p.teleport(Locations.lounge);
		
		
	}
	
	@SuppressWarnings("deprecation")
	public void addSpec(Player p, boolean isNew) {
		PlayerData pd = this.playerData.get(p.getUniqueId());
		pd.setState(PlayerState.SPECTATING);
		p.setGameMode(GameMode.SPECTATOR);
		if (isNew) {
			p.teleport(game.getPlaying().get(0));
		} else {
			Bukkit.broadcastMessage(CC.tl(p.getName() + "&c has been remove from the game"));
			p.playSound(p.getLocation(), Sound.EXPLODE, 10, 1);
			p.sendTitle(CC.tl("&c&lYou Died"), "You are now spectating");
			game.dec();
			game.removeDead(p);
		}

		p.getInventory().clear();
		
		
	}
	

}
