package de.jonah.Listener;// don´t look at this mess ~Jonah

import de.jonah.main.Main;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class RandomLootHandler implements Listener {

    private void loadConfig() {
        Main.getInstance().getConfig().options().copyDefaults(true);
        Main.getInstance().saveConfig();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        setupLoot(player);

    }

    private void setupLoot(Player player) {
        FileConfiguration cfg = Main.getInstance().getConfig();
        List<String> configItems =cfg.getStringList("Items");

        int index = new Random().nextInt(configItems.size());
        String items = configItems.get(index);

        ItemStack newItem = new ItemStack(Objects.requireNonNull(Material.getMaterial(items.toUpperCase())), 2);

        player.getInventory().addItem(newItem);
    }
}
