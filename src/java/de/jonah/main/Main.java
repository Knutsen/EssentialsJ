package de.jonah.main;

import de.jonah.Listener.*;
import de.jonah.commands.*;
import de.jonah.commands.gamemodes.CreativeCommand;
import de.jonah.commands.gamemodes.SpectatorCommand;
import de.jonah.commands.gamemodes.SurvivalCommand;
import de.jonah.commands.timeofday.DayCommand;
import de.jonah.commands.timeofday.NightCommand;
import de.jonah.enchantments.AddEnchantments;
import de.jonah.enchantments.BlockBreakListener;
import de.jonah.enchantments.CustomEnchants;
import de.jonah.enchantments.CustomEnchantsListener;
import de.jonah.resources.Variables;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.Objects;

import static de.jonah.utils.Config.loadConfig;

public final class Main extends JavaPlugin implements Listener {

    private static Main instance;

    public static Main getInstance() {
        return instance;
    }

    public enum Rank {
        ADMIN(ChatColor.RED + "§lAdmin §r§8| " + ChatColor.RED),
        DEV(ChatColor.AQUA + "§lDev §r§8| " + ChatColor.AQUA),
        MOD(ChatColor.GREEN + "§lMod §r§8| " + ChatColor.GREEN),
        HELPER(ChatColor.YELLOW + "§lHelper §r§8| " + ChatColor.YELLOW),
        MEMBER(ChatColor.GRAY + "§lMember §r§8| " + ChatColor.GRAY);

        private String prefix;

        Rank(String prefix) {
            this.prefix = prefix;
        }

        public String getPrefix() {
            return prefix;
        }
    }


    @Override
    public void onEnable() {
        instance = this;
        loadConfig();
        CustomEnchants.register();
        PluginManager pm = Bukkit.getPluginManager();
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective objective = board.registerNewObjective("showhealth", "health");
        objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
        objective.setDisplayName("/ 20");

        for(Player online : Bukkit.getOnlinePlayers()){
            online.setScoreboard(board);
            online.setHealth(online.getHealth()); //Update their health
        }


        System.out.println(Variables.linebreak);
        System.out.println(" ");
        System.out.println("             [EssentialsJ] is starting!");
        System.out.println(" ");
        System.out.println(Variables.linebreak);

        pm.registerEvents(new SettingsGUI(), this);
        pm.registerEvents(new BlockBreakListener(), this);
        pm.registerEvents(new CustomEnchantsListener(), this);
        pm.registerEvents(new RandomLootHandler(), this);
        pm.registerEvents(new MenuHandler(this), this);
        pm.registerEvents(new MovementHandler(), this);
        pm.registerEvents(new RespawnHandler(), this);
        pm.registerEvents(this, this);
        pm.registerEvents(new RankHandler(), this);
        pm.registerEvents(new ChatHandler(), this);

        Objects.requireNonNull(getCommand("setspawn")).setExecutor(new SpawnCommand());
        Objects.requireNonNull(getCommand("spawn")).setExecutor(new SpawnCommand());
        Objects.requireNonNull(getCommand("settings")).setExecutor(new SettingsGUI());
        Objects.requireNonNull(getCommand("add")).setExecutor(new AddEnchantments());
        Objects.requireNonNull(getCommand("fly")).setExecutor(new FlyCommand());
        Objects.requireNonNull(getCommand("creative")).setExecutor(new CreativeCommand());
        Objects.requireNonNull(getCommand("spectator")).setExecutor(new SpectatorCommand());
        Objects.requireNonNull(getCommand("survival")).setExecutor(new SurvivalCommand());
        Objects.requireNonNull(getCommand("heal")).setExecutor(new HealCommand());
        Objects.requireNonNull(getCommand("feed")).setExecutor(new FoodCommand());
        Objects.requireNonNull(getCommand("bacta")).setExecutor(new BactaCommand());
        Objects.requireNonNull(getCommand("night")).setExecutor(new NightCommand());
        Objects.requireNonNull(getCommand("day")).setExecutor(new DayCommand());
        Objects.requireNonNull(getCommand("gui")).setExecutor(new GUICommand(this));
        Objects.requireNonNull(getCommand("rank")).setExecutor(new RankHandler());
        Objects.requireNonNull(getCommand("test")).setExecutor(new DeBugCommand());

        saveDefaultConfig();

    }

    public void onDisable(){
        saveConfig();
    }

    public static ItemStack emptytool = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
    public static ItemStack tbdtool = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
    public static ItemStack backwardstool = new ItemStack(Material.SPRUCE_BUTTON);
    public static ItemStack forwardstool = new ItemStack(Material.OAK_BUTTON);
    public static ItemStack closeinventorytool = new ItemStack(Material.REDSTONE_TORCH);
    public static ItemStack lavaboots = new ItemStack(Material.LEATHER_BOOTS);
    public static ItemStack waterboots = new ItemStack(Material.LEATHER_BOOTS);
    public static ItemStack smokeboots = new ItemStack(Material.LEATHER_BOOTS);
    public static ItemStack fireboots = new ItemStack(Material.LEATHER_BOOTS);
    public static ItemStack heartboots = new ItemStack(Material.LEATHER_BOOTS);
    public static ItemStack weatherclear = new ItemStack(Material.BUCKET);
    public static ItemStack weatherrain = new ItemStack(Material.WATER_BUCKET);
    public static ItemStack weatherthunder = new ItemStack(Material.SALMON_BUCKET);
    public static ItemStack playerhead = new ItemStack(Material.PLAYER_HEAD);
    public static ItemStack healtool = new ItemStack(Material.BEEF);

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if (this.getConfig().getString(event.getPlayer().getName()) != null) {
            event.getPlayer().setDisplayName(getConfig().getString(event.getPlayer().getName()) + ChatColor.RESET);
        }
    }

    public void openSettingsMenu(Player player) {

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

        ItemStack[] menu_items = {emptytool, spawnpointtool, emptytool, weatherclear, emptytool, timeofdaytool, emptytool, tbdtool, emptytool};

        SettingsGUI.setContents(menu_items);

        player.openInventory(SettingsGUI);
        player.playSound(player.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 1f, 1f);
    }

    public void openGUIOne(Player player) {
        Inventory main_menu = Bukkit.createInventory(player, 9 * 3, "Settings Page: 1");

        ItemStack spawnpointtool = new ItemStack(Material.LIME_BED);
        ItemStack teleporttool = new ItemStack(Material.COMPASS);
        ItemStack timeofdaytool = new ItemStack(Material.CLOCK);
        ItemStack settingstool = new ItemStack(Material.COMPARATOR);
        ItemStack nicktool = new ItemStack(Material.NAME_TAG);
        ItemStack difficultytool = new ItemStack(Material.ZOMBIE_HEAD);
        ItemStack bootstool = new ItemStack(Material.GOLDEN_BOOTS);

        ItemMeta backwards_meta = backwardstool.getItemMeta();
        backwards_meta.setDisplayName("§eLast");
        ArrayList<String> backwards_lore = new ArrayList<>();
        backwards_lore.add("§7Go to the previous page");
        backwards_meta.setLore(backwards_lore);
        backwardstool.setItemMeta(backwards_meta);

        ItemMeta forwards_meta = forwardstool.getItemMeta();
        forwards_meta.setDisplayName("§6Next");
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

        //ItemMeta empty-/tbdtool

        ItemMeta empty_meta = emptytool.getItemMeta();
        empty_meta.setDisplayName(" ");
        emptytool.setItemMeta(empty_meta);

        ItemMeta tbd_meta = tbdtool.getItemMeta();
        tbd_meta.setDisplayName("§e§nIn progress");
        tbdtool.setItemMeta(tbd_meta);

        //other items
        ItemMeta spawnpoint_meta = spawnpointtool.getItemMeta();
        spawnpoint_meta.setDisplayName("Spawn location");
        ArrayList<String> spawnpoint_lore = new ArrayList<>();
        spawnpoint_lore.add("Set the Spawn location");
        spawnpoint_meta.setLore(spawnpoint_lore);
        spawnpointtool.setItemMeta(spawnpoint_meta);

        ItemMeta boots_meta = bootstool.getItemMeta();
        boots_meta.setDisplayName("Shoes");
        ArrayList<String> boots_lore = new ArrayList<>();
        boots_lore.add("Equip funny boots with effects!");
        boots_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        boots_meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        boots_meta.setLore(boots_lore);
        bootstool.setItemMeta(boots_meta);

        ItemMeta settings_meta = settingstool.getItemMeta();
        settings_meta.setDisplayName("Settings");
        ArrayList<String> settings_lore = new ArrayList<>();
        settings_lore.add("Change the settings");
        settings_meta.setLore(settings_lore);
        settingstool.setItemMeta(settings_meta);

        ItemMeta teleport_meta = teleporttool.getItemMeta();
        teleport_meta.setDisplayName("Teleport");
        ArrayList<String> teleport_lore = new ArrayList<>();
        teleport_lore.add("Teleports you to a player or spawn");
        teleport_meta.setLore(teleport_lore);
        teleporttool.setItemMeta(teleport_meta);

        ItemMeta timeoftheday_meta = timeofdaytool.getItemMeta();
        timeoftheday_meta.setDisplayName("Time of the day");
        ArrayList<String> timeoftheday_lore = new ArrayList<>();
        timeoftheday_lore.add("Change the time");
        timeoftheday_meta.setLore(timeoftheday_lore);
        timeofdaytool.setItemMeta(timeoftheday_meta);

        ItemMeta nicktool_meta = nicktool.getItemMeta();
        nicktool_meta.setDisplayName("Nick");
        ArrayList<String> nicktool_lore = new ArrayList<>();
        nicktool_lore.add("Change your Nickname");
        nicktool_meta.setLore(nicktool_lore);
        nicktool.setItemMeta(nicktool_meta);

        ItemMeta difficulty_meta = difficultytool.getItemMeta();
        difficulty_meta.setDisplayName("Difficulty");
        ArrayList<String> difficulty_lore = new ArrayList<>();
        difficulty_lore.add("Change the difficulty");
        difficulty_meta.setLore(difficulty_lore);
        difficultytool.setItemMeta(difficulty_meta);

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

        SkullMeta skull_meta = (SkullMeta) playerhead.getItemMeta();
        skull_meta.setOwner(player.getName());
        skull_meta.setDisplayName("Player´s");
        ArrayList<String> skull_lore = new ArrayList<>();
        skull_lore.add("Click to open a Menu of all Online players!");
        skull_meta.setLore(skull_lore);
        playerhead.setItemMeta(skull_meta);

        ItemStack[] menu_items = {spawnpointtool, emptytool, bootstool, emptytool, teleporttool, emptytool, timeofdaytool, emptytool, settingstool, emptytool, nicktool, emptytool, difficultytool, emptytool, weatherclear, emptytool, playerhead, emptytool, emptytool, emptytool, emptytool, backwardstool, closeinventorytool, forwardstool, emptytool, emptytool, emptytool};
        main_menu.setContents(menu_items);
        player.openInventory(main_menu);
    }

    public void openGUITwo(Player player) {
        Inventory main_menu_two = Bukkit.createInventory(player, 9 * 3, "Settings Page: 2");

        ItemStack flytool = new ItemStack(Material.FEATHER);

        ItemMeta heal_meta = healtool.getItemMeta();
        heal_meta.setDisplayName("Heal");
        ArrayList<String> heal_lore = new ArrayList<>();
        heal_lore.add("The lamb slain for us");
        heal_meta.setLore(heal_lore);
        healtool.setItemMeta(heal_meta);

        ItemMeta fly_meta = flytool.getItemMeta();
        fly_meta.setDisplayName("Fly");
        ArrayList<String> fly_lore = new ArrayList<>();
        fly_lore.add("Sets you flying");
        fly_meta.setLore(fly_lore);
        flytool.setItemMeta(fly_meta);

        ItemStack[] menu_two_items = {healtool, emptytool, flytool, emptytool, tbdtool, emptytool, tbdtool, emptytool, tbdtool, emptytool, tbdtool, emptytool, tbdtool, emptytool, tbdtool, emptytool, tbdtool, emptytool, emptytool, emptytool, emptytool, backwardstool, closeinventorytool, forwardstool, emptytool, emptytool, emptytool};
        main_menu_two.setContents(menu_two_items);
        player.openInventory(main_menu_two);
    }

    public void openBootsMenu(Player player) {

        Inventory bootsinv = Bukkit.createInventory(player, 9 * 3, "Boots Collection");

        LeatherArmorMeta lavaboots_Meta = (LeatherArmorMeta) lavaboots.getItemMeta();
        lavaboots_Meta.setDisplayName("§cLava");
        lavaboots_Meta.setUnbreakable(true);
        lavaboots_Meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        lavaboots_Meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        lavaboots_Meta.addItemFlags(ItemFlag.HIDE_DYE);
        lavaboots_Meta.setColor(Color.RED);
        ArrayList<String> lavaboots_lore = new ArrayList<>();
        lavaboots_lore.add("§7Lava drip particle");
        lavaboots_Meta.setLore(lavaboots_lore);
        lavaboots.setItemMeta(lavaboots_Meta);

        LeatherArmorMeta waterboots_Meta = (LeatherArmorMeta) waterboots.getItemMeta();
        waterboots_Meta.setDisplayName("§9Water");
        waterboots_Meta.setUnbreakable(true);
        waterboots_Meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        waterboots_Meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        waterboots_Meta.addItemFlags(ItemFlag.HIDE_DYE);
        waterboots_Meta.setColor(Color.BLUE);
        ArrayList<String> waterboots_lore = new ArrayList<>();
        waterboots_lore.add("§7Water drip particle");
        waterboots_Meta.setLore(waterboots_lore);
        waterboots.setItemMeta(waterboots_Meta);

        LeatherArmorMeta smokeboots_Meta = (LeatherArmorMeta) smokeboots.getItemMeta();
        smokeboots_Meta.setDisplayName("§8Smoke");
        smokeboots_Meta.setUnbreakable(true);
        smokeboots_Meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        smokeboots_Meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        smokeboots_Meta.addItemFlags(ItemFlag.HIDE_DYE);
        smokeboots_Meta.setColor(Color.GRAY);
        ArrayList<String> smokeboots_lore = new ArrayList<>();
        smokeboots_lore.add("§7Smoke particle");
        smokeboots_Meta.setLore(smokeboots_lore);
        smokeboots.setItemMeta(smokeboots_Meta);

        LeatherArmorMeta fireboots_Meta = (LeatherArmorMeta) fireboots.getItemMeta();
        fireboots_Meta.setDisplayName("§6Fire");
        fireboots_Meta.setUnbreakable(true);
        fireboots_Meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        fireboots_Meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        fireboots_Meta.addItemFlags(ItemFlag.HIDE_DYE);
        fireboots_Meta.setColor(Color.ORANGE);
        ArrayList<String> fireboots_lore = new ArrayList<>();
        fireboots_lore.add("§7Fire particles");
        fireboots_Meta.setLore(fireboots_lore);
        fireboots.setItemMeta(fireboots_Meta);

        LeatherArmorMeta heartboots_Meta = (LeatherArmorMeta) heartboots.getItemMeta();
        heartboots_Meta.setDisplayName("§4Heart");
        heartboots_Meta.setUnbreakable(true);
        heartboots_Meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        heartboots_Meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        heartboots_Meta.addItemFlags(ItemFlag.HIDE_DYE);
        heartboots_Meta.setColor(Color.fromBGR(57, 0, 199));
        ArrayList<String> heartboots_lore = new ArrayList<>();
        heartboots_lore.add("§7Heart particles");
        heartboots_Meta.setLore(heartboots_lore);
        heartboots.setItemMeta(heartboots_Meta);

        ItemStack[] boots_page1 = {lavaboots, emptytool, waterboots, emptytool, smokeboots, emptytool, fireboots, emptytool, heartboots, emptytool, tbdtool, emptytool, tbdtool, emptytool, tbdtool, emptytool, tbdtool, emptytool, tbdtool, emptytool, tbdtool, backwardstool, closeinventorytool, forwardstool, tbdtool, emptytool, tbdtool};
        bootsinv.setContents(boots_page1);
        player.openInventory(bootsinv);
    }

    public void openPlayerList(Player player) {
        Inventory playergui = Bukkit.createInventory(player, 9 * 3, "Online players");

        ArrayList<Player> player_list = new ArrayList<>(player.getServer().getOnlinePlayers());

        for (int i = 0; i < player_list.size(); i++) {

            long time = player_list.get(i).getPlayerTime() / 20;
            int secs = (int) (time % 60);
            time /= 60;
            int mins = (int) (time % 60);
            time /= 60;
            int hours = (int) (time % 24);
            time /= 24;
            int days = (int) time;

            String totalTime = days + " days, " + hours + ":" + mins + ":" + secs + " hours";

            SkullMeta playerhead_meta = (SkullMeta) playerhead.getItemMeta();
            playerhead_meta.setOwner(player_list.get(i).getName());
            playerhead_meta.setDisplayName(player_list.get(i).getDisplayName());
            ArrayList<String> playerhead_lore = new ArrayList<>();

            playerhead_lore.add("§7Online time: " + totalTime);
            playerhead_lore.add("§7Gamemode: " + player_list.get(i).getGameMode());
            playerhead_lore.add("§7Health: " + String.format("%.1f", player_list.get(i).getHealth() / 2));
            playerhead_lore.add("§7Food: " + player_list.get(i).getFoodLevel() / 2 + ".0");
            playerhead_lore.add("§7Level: " + player_list.get(i).getLevel() + ".0");

            Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.instance, new Runnable() {
                int i = 0;

                public void run() {
                    i++;
                    playerhead.setItemMeta(playerhead_meta);
                    player.updateInventory();
                    new BukkitRunnable() {
                        public void run() {
                            playerhead.setItemMeta(playerhead_meta);
                            player.updateInventory();
                        }
                    }.runTaskLater(Main.instance, 1000L);
                }
            }, 0L, 40L);

            playerhead_meta.setLore(playerhead_lore);
            playerhead.setItemMeta(playerhead_meta);
            playergui.addItem(playerhead);
            playergui.setItem(21, backwardstool);
            playergui.setItem(22, closeinventorytool);
            playergui.setItem(23, forwardstool);

            player.openInventory(playergui);
        }

    }

    public void openPlayersMenu(Player player) {
        ArrayList<Player> player_list = new ArrayList<>(player.getServer().getOnlinePlayers());
        for (int i = 0; i < player_list.size(); i++) {
            Inventory playermenu = Bukkit.createInventory(player, 9 * 3, player_list.get(i).getDisplayName());

            int healthscale = (int) player_list.get(i).getHealth() / 2;
            int foodscale = player_list.get(i).getFoodLevel() / 2;
            int levelscale = player_list.get(i).getLevel();


            ItemStack healthindicator = new ItemStack(Material.POTION, 1, (byte) 0);
            ItemStack foodindicator = new ItemStack(Material.MELON_SLICE);
            ItemStack levelindicator = new ItemStack(Material.EXPERIENCE_BOTTLE); //regular click +-1, shift click +-5
            ItemStack freezeplayer = new ItemStack(Material.ICE);
            ItemStack banplayer = new ItemStack(Material.BARRIER);
            ItemStack kickplayer = new ItemStack(Material.BLAZE_ROD);


            ItemMeta hb_meta = healthindicator.getItemMeta();
            hb_meta.setDisplayName("§fHealth: §e" + healthscale);
            ArrayList<String> hb_lore = new ArrayList<>();
            hb_lore.add("Left-click to add one Heart");
            hb_lore.add("Right-click to remove one Heart");
            hb_meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
            hb_lore.add(" ");
            hb_meta.setLore(hb_lore);
            healthindicator.setItemMeta(hb_meta);

            ItemMeta fi_meta = foodindicator.getItemMeta();
            fi_meta.setDisplayName("§fFood: §e" + foodscale);
            ArrayList<String> fi_lore = new ArrayList<>();
            fi_lore.add("Left-click to add one food");
            fi_lore.add("Right-click to remove one food");
            fi_lore.add(" ");
            fi_meta.setLore(fi_lore);
            foodindicator.setItemMeta(fi_meta);

            ItemMeta li_meta = levelindicator.getItemMeta();
            li_meta.setDisplayName("§fLevel: §e" + levelscale);
            ArrayList<String> li_lore = new ArrayList<>();
            li_lore.add("Left-click to add one level");
            li_lore.add("Right-click to remove one level");
            li_lore.add(" ");
            li_meta.setLore(li_lore);
            levelindicator.setItemMeta(li_meta);

            ItemMeta fp_meta = freezeplayer.getItemMeta();
            fp_meta.setDisplayName("§fFreeze: §e" + player_list.get(i).getDisplayName());
            ArrayList<String> fp_lore = new ArrayList<>();
            fp_lore.add("Left click to freeze Player");
            fp_lore.add("Right click to unfreeze Player");
            fp_lore.add(" ");
            fp_meta.setLore(fp_lore);
            freezeplayer.setItemMeta(fp_meta);

            ItemMeta ban_meta = banplayer.getItemMeta();
            ban_meta.setDisplayName("§fBan: §e" + player_list.get(i).getDisplayName());
            ArrayList<String> ban_lore = new ArrayList<>();
            ban_lore.add("Left click to ban player");
            ban_lore.add("Right click to add reason!");
            ban_lore.add(" ");
            ban_meta.setLore(ban_lore);
            banplayer.setItemMeta(ban_meta);



            ItemStack[] opmenu_items = {emptytool, healthindicator, emptytool, foodindicator, emptytool, levelindicator, emptytool, freezeplayer, emptytool, banplayer, emptytool, tbdtool, emptytool,tbdtool,emptytool,tbdtool,emptytool,tbdtool};
            playermenu.setContents(opmenu_items);

            playermenu.setItem(21, backwardstool);
            playermenu.setItem(22, closeinventorytool);
            playermenu.setItem(23, forwardstool);

            player.openInventory(playermenu);
        }
    }
}