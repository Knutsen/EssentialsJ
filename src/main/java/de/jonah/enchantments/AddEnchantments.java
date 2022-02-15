package de.jonah.enchantments;// don´t look at this mess ~Jonah

import de.jonah.resources.Variables;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class AddEnchantments implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            String addedEnchantment = Variables.server + "§7Added §5" + strings[0].toUpperCase() + " §7to §e" + (player.getInventory().getItemInMainHand().getType() + "§7.");
            if (player.hasPermission("AddEnchantments.*")) {
                ItemStack item = player.getInventory().getItemInMainHand();
                ItemMeta meta = item.getItemMeta();
                List<String> lore = new ArrayList<String>();
                if (item.getItemMeta() == null) return false;
                if (strings.length == 1) {
                    switch (strings[0].toLowerCase()) {
                        case "telepathy":
                            lore.add("§7Telepathy §kI");
                            if (meta.hasLore())
                                for (String l : meta.getLore())
                                    lore.add(l);
                            meta.setLore(lore);
                            item.setItemMeta(meta);
                            item.addUnsafeEnchantment(CustomEnchants.TELEPATHY, 1); //this line has to be down here --> otherwise item won´t be added
                            player.sendMessage(addedEnchantment);
                            break;
                        case "teleport":
                            lore.add("§7Teleport §kI");
                            if (meta.hasLore())
                                for (String l : meta.getLore())
                                    lore.add(l);
                            meta.setLore(lore);
                            item.setItemMeta(meta);
                            item.addUnsafeEnchantment(CustomEnchants.TELEPORT, 1); //this line has to be down here --> otherwise item won´t be added
                            player.sendMessage(addedEnchantment);
                            break;
                        case "arrowhook":
                            lore.add("§7Arrowhook §kI");
                            if (meta.hasLore())
                                for (String l : meta.getLore())
                                    lore.add(l);
                            meta.setLore(lore);
                            item.setItemMeta(meta);
                            item.addUnsafeEnchantment(CustomEnchants.ARROWHOOK, 1); //this line has to be down here --> otherwise item won´t be added
                            player.sendMessage(addedEnchantment);
                            break;
                        case "explosiveshot":
                            lore.add("§7Arrowhook §kI");
                            if (meta.hasLore())
                                for (String l : meta.getLore())
                                    lore.add(l);
                            meta.setLore(lore);
                            item.setItemMeta(meta);
                            item.addUnsafeEnchantment(CustomEnchants.EXPLOSIVESHOT, 1); //this line has to be down here --> otherwise item won´t be added
                            player.sendMessage(addedEnchantment);
                            break;
                        case "lifesteal":
                            lore.add("§7Lifesteal §kI");
                            if (meta.hasLore())
                                for (String l : meta.getLore())
                                    lore.add(l);
                            meta.setLore(lore);
                            item.setItemMeta(meta);
                            item.addUnsafeEnchantment(CustomEnchants.LIFESTEAL, 1); //this line has to be down here --> otherwise item won´t be added
                            player.sendMessage(addedEnchantment);
                            break;
                    }
                }
            } else player.sendMessage(Variables.noPerm);
        } else sender.sendMessage(Variables.noPlayer);
        return false;
    }
}
