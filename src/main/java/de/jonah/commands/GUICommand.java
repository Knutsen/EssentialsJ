package de.jonah.commands;// don´t look at this mess ~Jonah

import de.jonah.main.Main;
import de.jonah.resources.Variables;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GUICommand implements CommandExecutor {

    Main plugin;

    public GUICommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            plugin.openGUIOne(player);
        } else sender.sendMessage(Variables.noPlayer);
        return true;
    }
}
