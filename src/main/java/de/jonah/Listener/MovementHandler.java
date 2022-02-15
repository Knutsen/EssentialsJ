package de.jonah.Listener;// don´t look at this mess ~Jonah

import de.jonah.main.Main;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MovementHandler implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player player = event.getPlayer();
        if(player.getInventory().getBoots() != null){
            if(player.getInventory().getBoots().isSimilar(Main.lavaboots)){
                player.spawnParticle(Particle.DRIP_LAVA, player.getLocation(), 50);
            }else if (player.getInventory().getBoots().isSimilar(Main.waterboots)){
                player.spawnParticle(Particle.WATER_DROP, player.getLocation(), 50);
            }else if (player.getInventory().getBoots().isSimilar(Main.smokeboots)){
                player.spawnParticle(Particle.SPIT, player.getLocation(), 1);
            }else if(player.getInventory().getBoots().isSimilar(Main.fireboots)){
                player.spawnParticle(Particle.FLAME, player.getLocation(), 1);
            }else if(player.getInventory().getBoots().isSimilar(Main.heartboots)){
                player.spawnParticle(Particle.HEART, player.getLocation(), 1);
            }
        }
    }
}
