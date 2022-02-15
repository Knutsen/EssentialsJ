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

public class SurvivalCommand implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (player.hasPermission("SurvivalCommand.*")){
                if (args.length == 0){
                    player.setInvisible(false);
                    player.setGameMode(GameMode.SURVIVAL);
                    player.setAllowFlight(false);
                    player.setFlying(false);

                }else if (args.length == 1){
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null){
                        target.setInvisible(false);
                        target.setGameMode(GameMode.SURVIVAL);
                        target.setAllowFlight(false);
                        target.setFlying(false);
                    }
                }
            }else sender.sendMessage(Variables.noPerm);
        }else sender.sendMessage(Variables.noPlayer);
        return false;
    }
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event){
        event.setCancelled(false);
    }
    @EventHandler
    public void onDamageTwo(EntityDamageEvent event){
        event.setCancelled(false);
    }
}
