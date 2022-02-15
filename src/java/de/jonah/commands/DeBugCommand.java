package de.jonah.commands;// don´t look at this mess ~Jonah

import de.jonah.resources.Variables;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeBugCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            player.sendMessage(Variables.dev1 + "§l" +player.getName());
            player.sendMessage(" ");
            player.sendMessage(Variables.dev2 + player.getName());
            player.sendMessage(" ");
            player.sendMessage(Variables.dev3 + player.getName());
            player.sendMessage(" ");
            player.sendMessage(Variables.dev4 + "§l" + player.getName());


        }else{

        }
        return false;
    }
}
