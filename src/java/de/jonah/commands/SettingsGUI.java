package de.jonah.commands;// don´t look at this mess ~Jonah

import de.jonah.resources.Variables;
import de.jonah.utils.FileConfig;
import de.jonah.utils.LocationConfig;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class SettingsGUI implements CommandExecutor, Listener {

    Inventory SettingsGUI = Bukkit.createInventory(null, 9 * 1, "SETTINGS");

    ItemStack emptytool = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
    ItemStack tbdtool = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
    ItemStack spawnpointtool = new ItemStack(Material.LIME_BED);
    ItemStack weatherclear = new ItemStack(Material.BUCKET);
    ItemStack weatherrain = new ItemStack(Material.WATER_BUCKET);
    ItemStack weatherthunder = new ItemStack(Material.SALMON_BUCKET);
    ItemStack timeofdaytool = new ItemStack(Material.CLOCK);

    ItemStack backwardstool = new ItemStack(Material.SPRUCE_BUTTON);
    ItemStack forwardstool = new ItemStack(Material.OAK_BUTTON);
    ItemStack closeinventorytool = new ItemStack(Material.REDSTONE_TORCH);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("SettingsGUI.*")) {

                ItemMeta backwards_meta = backwardstool.getItemMeta();
                backwards_meta.setDisplayName("§7Last");
                ArrayList<String> backwards_lore = new ArrayList<>();
                backwards_lore.add("§7Go to the previous page");
                backwards_meta.setLore(backwards_lore);
                backwardstool.setItemMeta(backwards_meta);

                ItemMeta forwards_meta = forwardstool.getItemMeta();
                forwards_meta.setDisplayName("§7Next");
                ArrayList<String> forwards_lore = new ArrayList<>();
                forwards_lore.add("§7Go to the next page");
                forwards_meta.setLore(forwards_lore);
                forwardstool.setItemMeta(forwards_meta);

                ItemMeta close_meta = closeinventorytool.getItemMeta();
                close_meta.setDisplayName("§cExit");
                ArrayList<String> close_lore = new ArrayList<>();
                close_lore.add("§7Close inventory");
                close_meta.setLore(close_lore);
                closeinventorytool.setItemMeta(close_meta);

                ItemMeta empty_meta = emptytool.getItemMeta();
                empty_meta.setDisplayName(" ");
                emptytool.setItemMeta(empty_meta);

                ItemMeta tbd_meta = tbdtool.getItemMeta();
                tbd_meta.setDisplayName("§e§nIn progress");
                tbdtool.setItemMeta(tbd_meta);

                ItemMeta spawnpoint_meta = spawnpointtool.getItemMeta();
                spawnpoint_meta.setDisplayName("Spawn location");
                ArrayList<String> spawnpoint_lore = new ArrayList<>();
                spawnpoint_lore.add("Set the Spawn location!");
                spawnpoint_meta.setLore(spawnpoint_lore);
                spawnpointtool.setItemMeta(spawnpoint_meta);

                ItemMeta sun_meta = weatherclear.getItemMeta();
                sun_meta.setDisplayName("Sun");
                ArrayList<String> sun_lore = new ArrayList<>();
                sun_lore.add("Click to change weather to rain!");
                sun_meta.setLore(sun_lore);
                weatherclear.setItemMeta(sun_meta);

                ItemMeta rain_meta = weatherrain.getItemMeta();
                rain_meta.setDisplayName("Rain");
                ArrayList<String> rain_lore = new ArrayList<>();
                rain_lore.add("Click to change weather to storm!");
                rain_meta.setLore(rain_lore);
                weatherrain.setItemMeta(rain_meta);

                ItemMeta thunder_meta = weatherthunder.getItemMeta();
                thunder_meta.setDisplayName("Storm");
                ArrayList<String> thunder_lore = new ArrayList<>();
                thunder_lore.add("Click to change weather to sun!");
                thunder_meta.setLore(thunder_lore);
                weatherthunder.setItemMeta(thunder_meta);

                ItemMeta timeofday_meta = timeofdaytool.getItemMeta();
                timeofday_meta.setDisplayName("Time of the day");
                ArrayList<String> timeoftheday_lore = new ArrayList<>();
                timeoftheday_lore.add("Change the time!");
                timeofday_meta.setLore(timeoftheday_lore);
                timeofdaytool.setItemMeta(timeofday_meta);

                //FileConfig difficulty = new FileConfig("difficulty.yml");
                //if item clicked... difficulty set ... save in difficultyconfig
                //Bukkit.getWorld("world").setDifficulty(Difficulty.PEACEFUL);

                ItemStack[] menu_items = {emptytool, spawnpointtool, emptytool, weatherclear, emptytool, timeofdaytool, emptytool, tbdtool, emptytool};

                SettingsGUI.setContents(menu_items);

                player.openInventory(SettingsGUI);
                player.playSound(player.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 1f, 1f);

            } else player.sendMessage(Variables.noPerm);
        } else sender.sendMessage(Variables.noPlayer);

        return false;
    }

    @EventHandler
    public void clickEvent(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();

        ItemMeta sun_meta = weatherclear.getItemMeta();
        sun_meta.setDisplayName("Sun");
        ArrayList<String> sun_lore = new ArrayList<>();
        sun_lore.add("Click to change weather to rain!");
        sun_meta.setLore(sun_lore);
        weatherclear.setItemMeta(sun_meta);

        ItemMeta rain_meta = weatherrain.getItemMeta();
        rain_meta.setDisplayName("Rain");
        ArrayList<String> rain_lore = new ArrayList<>();
        rain_lore.add("Click to change weather to storm!");
        rain_meta.setLore(rain_lore);
        weatherrain.setItemMeta(rain_meta);

        ItemMeta thunder_meta = weatherthunder.getItemMeta();
        thunder_meta.setDisplayName("Storm");
        ArrayList<String> thunder_lore = new ArrayList<>();
        thunder_lore.add("Click to change weather to sun!");
        thunder_meta.setLore(thunder_lore);
        weatherthunder.setItemMeta(thunder_meta);

        if (event.getView().getTitle().equalsIgnoreCase("SETTINGS")) {
            int inf = Integer.MAX_VALUE;
            event.setCancelled(true);
            if (event.getCurrentItem() == null) {
                return;
            } else
                switch (event.getCurrentItem().getType()) {
                    case LIME_BED:
                        FileConfig spawns = new FileConfig("locations.yml");
                        spawns.set("spawn", LocationConfig.loc2str(player.getLocation()));
                        spawns.saveConfig();
                        player.sendMessage(Variables.server + "You´ve set the spawn!");
                        //add teleport itemmeta
                        player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
                        break;

                    case BUCKET:
                        event.setCurrentItem(weatherrain);
                        player.getWorld().setStorm(true);
                        player.getWorld().setThundering(false);
                        player.getWorld().setWeatherDuration(inf);
                        //FileConfig difficulty = new FileConfig("difficulty.yml");
                        //difficulty.set("difficulty";
                        break;

                    case WATER_BUCKET:
                        event.setCurrentItem(weatherthunder);
                        player.getWorld().setStorm(true);
                        player.getWorld().setThundering(true);
                        player.getWorld().setWeatherDuration(inf);
                        break;

                    case SALMON_BUCKET:
                        event.setCurrentItem(weatherclear);
                        player.getWorld().setStorm(false);
                        player.getWorld().setThundering(false);
                        player.getWorld().setWeatherDuration(inf);
                        player.getWorld().setClearWeatherDuration(inf);
                        break;
                    case CLOCK:
                        if (player.getWorld().getTime() >13000 || player.getWorld().getTime() > 1300) player.getWorld().setTime(1000);
                        else player.getWorld().setTime(13000);
                        break;
                }
        }
    }
}
