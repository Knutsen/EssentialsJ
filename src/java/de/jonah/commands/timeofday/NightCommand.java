package de.jonah.commands.timeofday;// don´t look at this mess ~Jonah

import de.jonah.resources.Variables;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NightCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if(player.hasPermission("NightCommand.*")){
                player.getWorld().setTime(13000);
                player.sendMessage(Variables.server + "You set the time to: §0night§7!");
            }else player.sendMessage(Variables.noPerm);
        }else sender.sendMessage(Variables.noPlayer);
        return true;
    }
}
