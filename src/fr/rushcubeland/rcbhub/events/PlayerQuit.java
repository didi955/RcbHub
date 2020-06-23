package fr.rushcubeland.rcbhub.events;

import fr.rushcubeland.rcbapi.RcbAPI;
import fr.rushcubeland.rcbapi.account.Account;
import fr.rushcubeland.rcbapi.account.RankUnit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Optional;

public class PlayerQuit implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player player = e.getPlayer();

        if(RcbAPI.getInstance().boards.containsKey(player)){
            RcbAPI.getInstance().boards.get(player).destroy();
        }
        Optional<Account> account = RcbAPI.getInstance().getAccount(player);
        if(account.isPresent()){
            RankUnit rank = account.get().getDataRank().getRank();
            if(rank.getPower() <= 45){
                e.setQuitMessage(rank.getPrefix() + player.getDisplayName() + " §ca quitté le Lobby !");
            }
            else
            {
                e.setQuitMessage(null);
            }
        }
        else
        {
            e.setQuitMessage(null);
        }
        RcbAPI.getInstance().getAccount(player).ifPresent(Account::onLogout);
    }
}
