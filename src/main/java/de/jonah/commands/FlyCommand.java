package de.jonah.commands;// don´t look at this mess ~Jonah

import de.jonah.resources.Variables;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("FlyCommand.*")) {
                if (args.length == 0){
                    if (player.isFlying() || player.getAllowFlight()) {
                        player.setFlying(false);
                        player.setAllowFlight(false);
                        player.playSound(player.getLocation(), Sound.ITEM_BUNDLE_DROP_CONTENTS, 1f, 1f);
                    } else {
                        player.setAllowFlight(true);
                        player.setFlying(true);
                        player.playSound(player.getLocation(), Sound.ITEM_ELYTRA_FLYING, 1f, 1f);
                    }
                }else if (args.length == 1){
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null){
                        if (target.isFlying() || target.getAllowFlight()) {
                            target.setFlying(false);
                            target.setAllowFlight(false);
                            target.playSound(player.getLocation(), Sound.ITEM_BUNDLE_DROP_CONTENTS, 1f, 1f);
                        } else {
                            target.setAllowFlight(true);
                            target.setFlying(true);
                            target.playSound(player.getLocation(), Sound.ITEM_ELYTRA_FLYING, 1f, 1f);
                        }
                    }
                }

            } else player.sendMessage(Variables.noPerm);
        } else sender.sendMessage(Variables.noPlayer);
        return false;
    }
}

