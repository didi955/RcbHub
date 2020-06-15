package fr.rushcubeland.rcbhub.events;

import fr.rushcubeland.rcbapi.RcbAPI;
import fr.rushcubeland.rcbapi.account.Account;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Optional;

public class PlayerChat implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        Player player = e.getPlayer();
        Optional<Account> account = RcbAPI.getInstance().getAccount(player);
        if(account.isPresent()){
            e.setFormat(account.get().getDataRank().getRank().getPrefix() + player.getDisplayName() + " " + e.getMessage());
        }
        else
        {
            e.setCancelled(true);
        }
    }
}
