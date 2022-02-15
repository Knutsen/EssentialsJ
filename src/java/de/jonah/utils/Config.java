package de.jonah.utils;// don´t look at this mess ~Jonah

import de.jonah.main.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class Config {
    private static final FileConfiguration config = Main.getInstance().getConfig();

    public static void loadConfig() {
        File config = new File(Main.getInstance().getDataFolder() + File.separator + "config.yml");
        if (!config.exists()) {
            Main.getInstance().getConfig().options().copyDefaults(true);
            Main.getInstance().saveDefaultConfig();
        }
    }

    public static String name(Player p) {
        String name = config.getString("main.time.test-message");
        return name(p, name);
    }

    private static String name(Player p, String name) {
        name = name.replace("&", "§");
        name = name.replace("%player%", "" + p.getName());
        return name;
    }
}
