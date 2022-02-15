package de.jonah.utils;// don´t look at this mess ~Jonah

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;

public class FileConfig extends YamlConfiguration {

    private final String path;

    public FileConfig(String folder, String filename) {
        this.path = "plugins/" + folder + "/" + filename;

        try {
            load(this.path);
        } catch (InvalidConfigurationException | IOException ex) {
            ex.printStackTrace();
        }
    }
    public FileConfig(String filename){
        this("Settings", filename);
    }

    public void saveConfig(){
        try {
            save(this.path);

        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
