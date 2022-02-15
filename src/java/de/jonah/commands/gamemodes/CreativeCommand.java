package de.jonah.commands.gamemodes;// don´t look at this mess ~Jonah

import de.jonah.enchantments.CustomEnchants;
import de.jonah.resources.Variables;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class CreativeCommand implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        ItemStack creativetool = new ItemStack(Material.IRON_PICKAXE);
        creativetool.addUnsafeEnchantment(CustomEnchants.TELEPATHY, 1);
        ItemMeta meta = creativetool.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§7Creative §kI");
        meta.setLore(lore);
        creativetool.setItemMeta(meta);

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("CreativeCommand.*")) {
                if (args.length == 0) {
                    if (!((player.getGameMode()) == (GameMode.CREATIVE))) {
                        //if (player.getInventory().getItem(8) == null){
                        player.setInvisible(false);
                        player.setGameMode(GameMode.CREATIVE);
                        player.setAllowFlight(true);
                        player.setFlying(true);
                        player.teleport(player.getLocation().add(0, 1, 0));

                        //}else player.sendMessage(Variables.server + "Please clear the 9th slot in your inventory bar!");

                    } else player.sendMessage(Variables.server + "You are already in creative mode!");
                } else if (args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        if (!((target.getGameMode()) == (GameMode.CREATIVE))) {
                            //if (target.getInventory().getItem(8) == null){
                            player.setInvisible(false);
                            target.setGameMode(GameMode.CREATIVE);
                            player.setAllowFlight(true);
                            target.setFlying(true);
                            target.teleport(player.getLocation().add(0, 1, 0));

                            //}else player.sendMessage(Variables.server + "Someone trys to set you in Creative mode. Please clear the 9th slot in your inventory bar!");

                        } else player.sendMessage(Variables.server + args[0] + "Is already in creative mode!");
                    }
                }
            } else player.sendMessage(Variables.noPerm);
        } else sender.sendMessage(Variables.noPlayer);
        return false;
    }
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event){
        event.setCancelled(true);
    }
    @EventHandler
    public void onDamageTwo(EntityDamageEvent event){
        event.setCancelled(false);
    }
}
