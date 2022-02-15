package de.jonah.Listener;// don´t look at this mess ~Jonah

import de.jonah.main.Main;
import de.jonah.resources.Variables;
import de.jonah.utils.FileConfig;
import de.jonah.utils.LocationConfig;
import me.rayzr522.jsonmessage.JSONMessage;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

import static de.jonah.main.Main.*;

public class MenuHandler implements Listener {

    Main plugin;

    public MenuHandler(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMenuClick(InventoryClickEvent event) {
        if (event.getCurrentItem() != null) {
            Player player = (Player) event.getWhoClicked();
            ArrayList<Player> player_list = new ArrayList<>(player.getServer().getOnlinePlayers());
            for (int i = 0; i < player_list.size(); i++) {

                //Menu list & Strings
                final String MAIN_MENU = "Settings Page: 1";
                final String MAIN_MENU_TWO = "Settings Page: 2";
                final String BOOTS_COLLECTION = "Boots Collection";
                final String ONLINE_PLAYERS = "Online players";
                final String PLAYERS_INV = player_list.get(i).getDisplayName();
                final String HEALTH = "§fHealth ";
                final String FOOD = "§fFood ";
                final String LEVEL = "§fLevel ";

                int healthscale = (int) player_list.get(i).getHealth() / 2;
                int foodscale = player_list.get(i).getFoodLevel() / 2;
                int levelscale = player_list.get(i).getLevel();

                ItemStack item = event.getCurrentItem();
                ItemMeta meta = event.getCurrentItem().getItemMeta();

                if (event.getView().getTitle().equalsIgnoreCase(MAIN_MENU)) {
                    event.setCancelled(true);
                    int inf = Integer.MAX_VALUE;
                    switch (event.getCurrentItem().getType()) {
                        case LIME_BED:
                            FileConfig spawns = new FileConfig("locations.yml");
                            spawns.set("spawn", LocationConfig.loc2str(player.getLocation()));
                            spawns.saveConfig();
                            player.sendMessage(Variables.server + "You´ve set the spawn!");
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
                            break;

                        case GOLDEN_BOOTS:
                            plugin.openBootsMenu(player);
                            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
                            break;

                        case COMPARATOR:
                            plugin.openSettingsMenu(player);
                            break;

                        case CLOCK:
                            if (player.getWorld().getTime() > 13000 || player.getWorld().getTime() > 1300) {
                                player.getWorld().setTime(1000);
                            } else player.getWorld().setTime(13000);
                            break;

                        case REDSTONE_TORCH:
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
                            break;

                        case OAK_BUTTON:
                            plugin.openGUITwo(player);
                            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
                            break;

                        case SPRUCE_BUTTON:
                            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
                            break;

                        case ZOMBIE_HEAD:
                            //player.openInventory(GUIDifficultyPage.difficultyinv);
                            break;

                        case BUCKET:
                            event.setCurrentItem(weatherrain);
                            player.getWorld().setStorm(true);
                            player.getWorld().setThundering(false);
                            player.getWorld().setWeatherDuration(inf);
                            //FileConfig difficulty = new FileConfig("difficulty.yml");
                            //difficulty.set("difficulty";
                            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);

                            break;

                        case WATER_BUCKET:
                            event.setCurrentItem(weatherthunder);
                            player.getWorld().setStorm(true);
                            player.getWorld().setThundering(true);
                            player.getWorld().setWeatherDuration(inf);
                            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
                            break;

                        case SALMON_BUCKET:
                            event.setCurrentItem(weatherclear);
                            player.getWorld().setStorm(false);
                            player.getWorld().setThundering(false);
                            player.getWorld().setWeatherDuration(inf);
                            player.getWorld().setClearWeatherDuration(inf);
                            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
                            break;
                        case PLAYER_HEAD:
                            plugin.openPlayerList(player);
                            player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 1.0f, 1.0f);

                    }
                    return;
                }
                if (event.getView().getTitle().equalsIgnoreCase(MAIN_MENU_TWO)) {
                    event.setCancelled(true);
                    switch (event.getCurrentItem().getType()) {

                        case BEEF:
                            player.setHealth(20);
                            player.setSaturation(20);
                            player.sendMessage(Variables.server + "You´ve been healed");
                            player.spawnParticle(Particle.VILLAGER_HAPPY, player.getLocation(), 5);
                            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
                            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                            player.closeInventory();
                            break;

                        case FEATHER:
                            if (player.getAllowFlight()) {
                                player.setAllowFlight(false);
                                player.sendMessage(Variables.server + "You cant fly anymore!");
                            } else {
                                player.setAllowFlight(true);
                                player.sendMessage(Variables.server + "You can fly now!");
                            }
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
                            break;

                        case REDSTONE_TORCH:
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
                            break;

                        case OAK_BUTTON:
                            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
                            break;

                        case SPRUCE_BUTTON:
                            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
                            plugin.openGUIOne(player);
                            break;
                    }
                }
                if (event.getView().getTitle().equalsIgnoreCase(BOOTS_COLLECTION)) {
                    event.setCancelled(true);
                    if (event.getCurrentItem() == null || event.getCurrentItem().isSimilar(Main.emptytool) || event.getCurrentItem().isSimilar(Main.tbdtool)) {
                        return;
                    } else if (event.getCurrentItem().isSimilar(Main.closeinventorytool) || event.getCurrentItem().isSimilar(Main.forwardstool) || event.getCurrentItem().isSimilar(Main.backwardstool)) {
                        switch (event.getCurrentItem().getType()) {

                            case REDSTONE_TORCH:
                                plugin.openGUIOne(player);
                                player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
                                break;

                            case OAK_BUTTON:
                                player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
                                break;

                            case SPRUCE_BUTTON:
                                player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
                                plugin.openGUITwo(player);
                                break;
                        }

                    } else if (player.getInventory().getBoots() == null) {
                        player.getInventory().setBoots(event.getCurrentItem());
                        player.closeInventory();

                    } else {
                        player.sendMessage(Variables.server + "Please unequip your shoes!");
                        player.closeInventory();
                    }
                }
                if (event.getView().getTitle().equalsIgnoreCase(ONLINE_PLAYERS)) {
                    event.setCancelled(true);
                    switch (event.getCurrentItem().getType()) {
                        case REDSTONE_TORCH:
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
                            break;

                        case OAK_BUTTON:
                            //plugin.openGUITwo(player);
                            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
                            break;

                        case SPRUCE_BUTTON:
                            plugin.openGUIOne(player);
                            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
                            break;

                        case PLAYER_HEAD:
                            plugin.openPlayersMenu(player);
                            String playername = event.getCurrentItem().getItemMeta().getDisplayName();
                    }
                }
                if (event.getView().getTitle().equalsIgnoreCase(PLAYERS_INV)) {
                    event.setCancelled(true);
                    switch (event.getCurrentItem().getType()) {

                        case REDSTONE_TORCH:
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
                            break;

                        case OAK_BUTTON:
                            //plugin.openGUITwo(player);
                            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
                            break;

                        case SPRUCE_BUTTON:
                            plugin.openPlayerList(player);
                            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
                            break;

                        case POTION:
                            if (event.getClick() == ClickType.MIDDLE) {
                                player_list.get(i).setGameMode(GameMode.SURVIVAL);
                            }
                            if (player_list.get(i).getGameMode().equals(GameMode.SURVIVAL)) {
                                if (event.getClick() == ClickType.RIGHT) {
                                    player_list.get(i).setHealth(player_list.get(i).getHealth() - 2);
                                    meta.setDisplayName(HEALTH + ChatColor.YELLOW + healthscale);
                                    item.setItemMeta(meta);
                                }
                                if (event.getClick() == ClickType.SHIFT_RIGHT) {
                                    player_list.get(i).setHealth(0);
                                    meta.setDisplayName(HEALTH + ChatColor.YELLOW + healthscale);
                                    item.setItemMeta(meta);
                                }
                                if (event.getClick() == ClickType.LEFT) {
                                    if (player_list.get(i).getHealth() < 20) {
                                        player_list.get(i).setHealth(player_list.get(i).getHealth() + 2);
                                        meta.setDisplayName(HEALTH + ChatColor.YELLOW + healthscale);
                                        item.setItemMeta(meta);
                                    }
                                }
                                if (event.getClick() == ClickType.SHIFT_LEFT) {
                                    player_list.get(i).setHealth(player_list.get(i).getHealthScale());
                                    meta.setDisplayName(HEALTH + ChatColor.YELLOW + healthscale);
                                    item.setItemMeta(meta);
                                }
                            } else {
                                player.closeInventory();
                                JSONMessage.create(Variables.server + "Do you like to change the gamemode of " + player_list.get(i).getDisplayName() + " §r§7to survival")
                                        .then("§a§nyes ")
                                        .tooltip("Click to go")
                                        .runCommand("/gamemode survival " + player_list.get(i))
                                        .then("§r§7or")
                                        .then("§c§nno§r§7!")
                                        .tooltip("Click to go")
                                        .runCommand("/gui")
                                        .send(player);
                                player.playSound(player.getLocation(), Sound.UI_TOAST_OUT, 1f, 1f);
                            }
                            break;

                        case MELON_SLICE:
                            if (event.getClick() == ClickType.MIDDLE) {
                                player_list.get(i).setGameMode(GameMode.SURVIVAL);
                            }
                            if (event.getClick() == ClickType.RIGHT) {
                                player_list.get(i).setFoodLevel(player_list.get(i).getFoodLevel() - 2);
                                meta.setDisplayName(FOOD + ChatColor.YELLOW + foodscale);
                                item.setItemMeta(meta);
                            }
                            if (event.getClick() == ClickType.SHIFT_RIGHT) {
                                player_list.get(i).setFoodLevel(0);
                                meta.setDisplayName(FOOD + ChatColor.YELLOW + foodscale);
                                item.setItemMeta(meta);
                            }
                            if (event.getClick() == ClickType.LEFT) {
                                if (player_list.get(i).getFoodLevel() < 20) {
                                    player_list.get(i).setFoodLevel(player_list.get(i).getFoodLevel() + 2);
                                    meta.setDisplayName(FOOD + ChatColor.YELLOW + foodscale);
                                    item.setItemMeta(meta);
                                }
                            }
                            if (event.getClick() == ClickType.SHIFT_LEFT) {
                                player_list.get(i).setFoodLevel(20);
                                meta.setDisplayName(FOOD + ChatColor.YELLOW + foodscale);
                                item.setItemMeta(meta);
                            }
                            break;

                        case EXPERIENCE_BOTTLE:
                            if (event.getClick() == ClickType.RIGHT) {
                                if (player_list.get(i).getLevel() >= 1) {
                                    player_list.get(i).setLevel(levelscale - 1);
                                    meta.setDisplayName(LEVEL + ChatColor.YELLOW + (levelscale - 1));
                                    item.setItemMeta(meta);
                                }
                            }
                            if (event.getClick() == ClickType.SHIFT_RIGHT) {
                                if (player_list.get(i).getLevel() >= 5) {
                                    player_list.get(i).setLevel(levelscale - 5);
                                    meta.setDisplayName(LEVEL + ChatColor.YELLOW + (levelscale - 5));
                                    item.setItemMeta(meta);
                                } else if (player_list.get(i).getLevel() < 5) {
                                    player_list.get(i).setLevel(levelscale - levelscale);
                                    meta.setDisplayName(LEVEL + ChatColor.YELLOW + (levelscale - levelscale));
                                    item.setItemMeta(meta);
                                }
                            }
                            if (event.getClick() == ClickType.LEFT) {
                                player_list.get(i).setLevel(levelscale + 1);
                                meta.setDisplayName(LEVEL + ChatColor.YELLOW + (levelscale + 1));
                                item.setItemMeta(meta);
                            }
                            if (event.getClick() == ClickType.SHIFT_LEFT) {
                                player_list.get(i).setLevel(levelscale + 5);
                                meta.setDisplayName(LEVEL + ChatColor.YELLOW + (levelscale + 5));
                                item.setItemMeta(meta);
                            }
                            break;

                        case ICE:
                            if (event.getClick() == ClickType.RIGHT) {


                            }
                            if (event.getClick() == ClickType.LEFT) {

                            }
                    }
                }
            }
        }
    }
}
