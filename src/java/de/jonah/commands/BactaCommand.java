package de.jonah.commands;// don´t look at this mess ~Jonah

import de.jonah.resources.Variables;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BactaCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("BactaCommand.*")) {
                if (args.length == 0) {
                    if (player.getFoodLevel() < 20 && player.getHealth() < 20) {
                        player.setHealth(player.getHealthScale());
                        player.setFoodLevel(20);
                        player.setSaturation(10);
                        player.sendMessage(Variables.server + "Health and food has been restored!");
                    } else if (player.getFoodLevel() < 20 && player.getHealth() == 20) {
                        player.setFoodLevel(20);
                        player.setSaturation(10);
                        player.sendMessage(Variables.server + "only food has been restored!");
                    } else if (player.getFoodLevel() == 20 && player.getHealth() < 20) {
                        player.setHealth(player.getHealthScale());
                        player.sendMessage(Variables.server + "only health has been restored!");
                    }

                } else if (args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target.getFoodLevel() < 20 && player.getHealth() < 20) {
                        target.setHealth(target.getHealthScale());
                        target.setFoodLevel(20);
                        target.setSaturation(10);
                        target.sendMessage(Variables.server + "Health and food has been restored by: §e" + player.getDisplayName() + " §7!");
                    } else if (target.getFoodLevel() < 20 && target.getHealth() == 20) {
                        target.setFoodLevel(20);
                        target.setSaturation(10);
                        target.sendMessage(Variables.server + "Only food has been restored by: §e" + player.getDisplayName() + "!");
                    } else if (target.getFoodLevel() == 20 && target.getHealth() < 20) {
                        target.setHealth(target.getHealthScale());
                        target.sendMessage(Variables.server + "Only health has been restored by: §e" + player.getDisplayName() + "!");
                    }
                }
            } else player.sendMessage(Variables.noPerm);
        } else sender.sendMessage(Variables.noPlayer);
        return false;
    }
}
