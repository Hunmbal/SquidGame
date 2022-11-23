package me.nust.project.commands;

import org.bukkit.command.Command;

import org.bukkit.command.CommandSender;

import me.nust.project.Main;

public class SkipWait extends Command {

	private Main plugin = Main.getInstance();

	
	public SkipWait(String name) {
		super(name);
	}

	@Override
	public boolean execute(CommandSender sender, String cmd, String[] args) {
		if (sender.isOp() || sender.getName().equals("UltimateMustafaa")) {
			int x = Integer.valueOf(args[0]);
			sender.sendMessage("Going to next round!");
			this.plugin.getGameManager().startGame(x);
			
					
		}
		return false;
	}

	
	
}
