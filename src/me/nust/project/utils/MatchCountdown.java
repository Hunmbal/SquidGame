package me.nust.project.utils;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public abstract class MatchCountdown {

    private int time;

    protected BukkitTask task;
    protected final Plugin plugin;


    public MatchCountdown(int time, Plugin plugin) {
        this.time = time;
        this.plugin = plugin;
    }


    public abstract void count(int current);


    public final void start() {
        task = new BukkitRunnable() {

            @Override
            public void run() {
                count(time);
                if (time-- <= 1) cancel();
            }

        }.runTaskTimer(plugin, 0L, 20L);
    }

}