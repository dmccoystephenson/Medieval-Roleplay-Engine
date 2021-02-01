package rpsystem.EventHandlers;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import rpsystem.MedievalRoleplayEngine;
import rpsystem.Utilities;

import static rpsystem.Utilities.sendMessageToPlayersWithinDistance;

public class AsyncPlayerChatEventHandler implements Listener {

    @EventHandler()
    public void handle(AsyncPlayerChatEvent event) {
        int localChatRadius = MedievalRoleplayEngine.getInstance().getConfig().getInt("localChatRadius");
        if (MedievalRoleplayEngine.getInstance().playersSpeakingInLocalChat.contains(event.getPlayer().getUniqueId())) {
            sendMessageToPlayersWithinDistance(event.getPlayer(), ChatColor.GRAY + "" + String.format("%s: \"%s\"", Utilities.getInstance().getCard(event.getPlayer().getUniqueId()).getName(), event.getMessage()), localChatRadius);
            event.setCancelled(true);
        }
    }

}
