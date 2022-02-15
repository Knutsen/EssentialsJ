package de.jonah.enchantments;// don´t look at this mess ~Jonah

import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.block.Container;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public class BlockBreakListener implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        if (event.getPlayer().getInventory().getItemInMainHand() == null)
            return;
        if (!event.getPlayer().getInventory().getItemInMainHand().hasItemMeta())
            return;
        if (!event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.TELEPATHY))
            return;
        if (event.getPlayer().getGameMode() == GameMode.CREATIVE || event.getPlayer().getGameMode() == GameMode.SPECTATOR)
            return;
        if (event.getPlayer().getInventory().firstEmpty() == -1)
            return;
        if (event.getBlock().getState() instanceof Container)
            return;

        event.setDropItems(false);
        Player player = event.getPlayer();
        Block block = event.getBlock();

        Collection<ItemStack> drops = block.getDrops(player.getInventory().getItemInMainHand());
        if (drops.isEmpty()) return;
            player.getInventory().addItem(drops.iterator().next());
    }
}
