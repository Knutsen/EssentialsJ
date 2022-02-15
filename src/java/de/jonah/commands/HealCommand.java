package de.jonah.commands;// don´t look at this mess ~Jonah

import de.jonah.resources.Variables;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("HealCommand.*")) {
                if (args.length == 0) {
                    if (player.getHealth() < 20) {
                        player.setHealth(20);
                        player.sendMessage(Variables.server + "You have been healed!");
                        player.spawnParticle(Particle.HEART, player.getLocation().add(1,1,0),50);
                    } else player.sendMessage(Variables.server + "Your health bar is already full!");

                } else if (args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        if (target.getHealth() < 20) {
                            target.setHealth(20);
                            target.sendMessage(Variables.server + "You have been healed by: §e" + player.getDisplayName() + " §7!");
                            target.spawnParticle(Particle.HEART, target.getLocation(),50);
                        } else player.sendMessage(Variables.server + "§e" +  target.getDisplayName() + " health bar is already full!");
                    }else player.sendMessage(Variables.server + "Please issue /heal §e<Player>§7!");
                }
            } else player.sendMessage(Variables.noPerm);
        } else sender.sendMessage(Variables.noPlayer);
        return false;
    }
}
