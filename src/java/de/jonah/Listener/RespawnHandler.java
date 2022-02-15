package de.jonah.Listener;// don´t look at this mess ~Jonah

import de.jonah.utils.FileConfig;
import de.jonah.utils.LocationConfig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class RespawnHandler implements Listener {
    @EventHandler
    public void onRespawn(PlayerRespawnEvent event){
        Player player = event.getPlayer();

        if(player.getBedSpawnLocation() == null){
            FileConfig spawns = new FileConfig("locations.yml");
            event.setRespawnLocation(LocationConfig.str2loc(spawns.getString("spawn")));
        }
    }
}
