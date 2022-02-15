package de.jonah.Listener;// don´t look at this mess ~Jonah

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatHandler implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        event.setFormat("%s: %s");
        if (event.getMessage().toUpperCase().contains("§7Jonah is gay")) {
            String NewMessage = event.getMessage().replace("Jonah", event.getPlayer().getDisplayName());
            event.setMessage(NewMessage);
        }
        String ColoredMessage = event.getMessage();
        //ChatColor.translateAlternateColorCodes('&', String);
        event.setMessage(ColoredMessage);

    }
}
