package de.jonah.commands;// don´t look at this mess ~Jonah

import de.jonah.resources.Variables;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FoodCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if(player.hasPermission("FoodCommand.*")){
                if (args.length == 0){
                    if(player.getFoodLevel() < 20){
                        player.setFoodLevel(20);
                        player.setSaturation(5);
                        player.sendMessage(Variables.server + "You have been fed!");
                    }else player.sendMessage(Variables.server + "Your hunger bar is already full!");

                }else if (args.length == 1){
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null){
                        if(target.getFoodLevel() < 20){
                            player.setFoodLevel(20);
                            player.setSaturation(5);
                            target.sendMessage(Variables.server + "You have been fed by: §e" + player.getDisplayName() + " §7!");
                        }else player.sendMessage(Variables.server + "§e" + target.getDisplayName() + " hunger bar is already full!");
                    }else player.sendMessage(Variables.server + "Please issue /feed §e<Player>§7!");
                }
            }else player.sendMessage(Variables.noPerm);
        }else sender.sendMessage(Variables.noPlayer);
        return false;
    }
}
