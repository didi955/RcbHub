package fr.rushcubeland.rcbhub.events;

import fr.rushcubeland.rcbapi.RcbAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChat implements Listener {

    @EventHandler(priority = EventPriority.LOW)
    public void onChat(AsyncPlayerChatEvent e){
        Player player = e.getPlayer();

        RcbAPI.getInstance().getAccountCallback(player, account -> {

            e.setFormat(account.getRank().getPrefix() + player.getDisplayName() + ": " + e.getMessage());

        });

    }
}
