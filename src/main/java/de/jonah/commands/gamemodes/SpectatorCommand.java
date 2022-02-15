package de.jonah.commands.gamemodes;// don´t look at this mess ~Jonah

import de.jonah.resources.Variables;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class SpectatorCommand implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (player.hasPermission("SpectatorCommand.*")){
                if (args.length == 0){
                    player.setInvisible(true);
                    player.setGameMode(GameMode.ADVENTURE);
                    player.setAllowFlight(true);
                    player.setFlying(true);
                    player.teleport(player.getLocation().add(0,1,0));

                }else if (args.length == 1){
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null){
                        target.setInvisible(true);
                        target.setGameMode(GameMode.ADVENTURE);
                        target.setAllowFlight(true);
                        target.setFlying(true);
                        target.teleport(player.getLocation().add(0,1,0));

                    }
                }
            }else sender.sendMessage(Variables.noPerm);
        }else sender.sendMessage(Variables.noPlayer);
        return false;
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event){
        event.setCancelled(true);
    }
    @EventHandler
    public void onDamageTwo(EntityDamageEvent event){
        event.setCancelled(true);
    }
}
