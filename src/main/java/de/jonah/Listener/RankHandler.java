package de.jonah.Listener;// don´t look at this mess ~Jonah

import de.jonah.main.Main;
import de.jonah.resources.Variables;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class RankHandler implements Listener, CommandExecutor {

    private final FileConfiguration config = Main.getInstance().getConfig();

    public void setRank(Player player, Main.Rank rank) {
        config.set("Ranks." + player.getUniqueId(), rank.toString());
    }

    public Main.Rank getRank(Player player) {
        String val = config.getString("Ranks." + player.getUniqueId());
        return (val == null ? Main.Rank.MEMBER : Main.Rank.valueOf(val));
    }

    public boolean hasRank(Player player, Main.Rank rank) {
        return (getRank(player).compareTo(rank) <= 0);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("RankCommand.*")) {
                if (label.equalsIgnoreCase("unrank")) {
                    setRank(player, Main.Rank.MEMBER);
                }
                Player target = Bukkit.getPlayer(args[0]);
                if (args.length == 2) {
                    if (target != null) {
                        switch (args[1].toLowerCase()) {
                            case "admin":
                                setRank(target, Main.Rank.ADMIN);
                                player.sendMessage(Variables.server + target.getDisplayName() + " §7is now : " + Main.Rank.ADMIN.getPrefix());
                                target.setDisplayName(Main.Rank.ADMIN.getPrefix() + target.getName() + ChatColor.GRAY);
                                break;
                            case "dev":
                                setRank(target, Main.Rank.DEV);
                                player.sendMessage(Variables.server + target.getDisplayName() + " §7is now : " + Main.Rank.DEV.getPrefix());
                                target.setDisplayName(Main.Rank.DEV.getPrefix() + target.getName() + ChatColor.GRAY);
                                break;
                            case "mod":
                                setRank(target, Main.Rank.MOD);
                                player.sendMessage(Variables.server + target.getDisplayName() + " §7is now : " + Main.Rank.MOD.getPrefix());
                                target.setDisplayName(Main.Rank.MOD.getPrefix() + target.getName() + ChatColor.GRAY);
                                break;
                            case "helper":
                                setRank(target, Main.Rank.HELPER);
                                player.sendMessage(Variables.server + target.getDisplayName() + " §7is now : " + Main.Rank.HELPER.getPrefix());
                                target.setDisplayName(Main.Rank.HELPER.getPrefix() + target.getName() + ChatColor.GRAY);
                                break;
                            case "member":
                                setRank(target, Main.Rank.MEMBER);
                                player.sendMessage(Variables.server + target.getDisplayName() + " §7is now : " + Main.Rank.MEMBER.getPrefix());
                                target.setDisplayName(Main.Rank.MEMBER.getPrefix() + target.getName() + ChatColor.GRAY);
                                break;

                        }
                    } else player.sendMessage(args[0] + "is offline");
                } else if(args.length < 2) player.sendMessage(Variables.server + "Pls use /rank §e<player> §e<rank>");
            }
        } else sender.sendMessage(Variables.noPlayer);
        return true;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (getRank(player).equals(Main.Rank.ADMIN)) {
            player.setDisplayName(Main.Rank.ADMIN.getPrefix() + player.getName() + ChatColor.GRAY);
        } else if (getRank(player).equals(Main.Rank.DEV)) {
            player.setDisplayName(Main.Rank.DEV.getPrefix() + player.getName() + ChatColor.GRAY);
        } else if (getRank(player).equals(Main.Rank.MOD)) {
            player.setDisplayName(Main.Rank.MOD.getPrefix() + player.getName() + ChatColor.GRAY);
        } else if (getRank(player).equals(Main.Rank.HELPER)) {
            player.setDisplayName(Main.Rank.HELPER.getPrefix() + player.getName() + ChatColor.GRAY);
        } else if (getRank(player) == null) {
            player.setDisplayName(Main.Rank.MEMBER.getPrefix() + player.getName() + ChatColor.GRAY);
        }
        event.setJoinMessage(Variables.server + player.getDisplayName() + " §7joined the server!");
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Main.getInstance().saveConfig();
        event.setQuitMessage(Variables.server + player.getDisplayName() + " §7left the server!");

    }
}
