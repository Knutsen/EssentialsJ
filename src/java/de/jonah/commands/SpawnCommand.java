package de.jonah.commands;// don´t look at this mess ~Jonah

import de.jonah.utils.FileConfig;
import de.jonah.utils.LocationConfig;
import de.jonah.resources.Variables;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("groot.spawn.*")) {
                FileConfig spawns = new FileConfig("locations.yml");
                if (s.equalsIgnoreCase("setspawn")) {
                    spawns.set("spawn", LocationConfig.loc2str(player.getLocation()));
                    spawns.saveConfig();
                    player.sendMessage(Variables.server + "You´ve set the spawn!");
                }
                if (spawns.contains("spawn")) {
                    LocationConfig.teleport(player, LocationConfig.str2loc(spawns.getString("spawn")));
                } else player.sendMessage(Variables.server + "No spawn has been set for this world!");

            } else
                player.sendMessage(Variables.noPerm);
            return true;

        } else
            System.out.println(Variables.noPlayer);
        return false;
    }
}
