package de.jonah.Listener;// don´t look at this mess ~Jonah

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class SleepHandler implements Listener {
    @EventHandler
    public void onMove(PlayerBedEnterEvent event) {
        Player player = event.getPlayer();
        int k = (int) (Bukkit.getServer().getOnlinePlayers().size() * (50.0f / 100.0f));

    }
        //if (k<= event.get){
}



