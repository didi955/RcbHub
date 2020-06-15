package fr.rushcubeland.rcbhub.events;

import fr.rushcubeland.rcbapi.RcbAPI;
import fr.rushcubeland.rcbapi.account.Account;
import fr.rushcubeland.rcbapi.account.RankUnit;
import fr.rushcubeland.rcbapi.tools.scoreboard.ScoreboardSign;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Optional;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();

        Optional<Account> account = RcbAPI.getInstance().getAccount(player);
        initScoreboardPlayer(player);
        Bukkit.getScheduler().scheduleSyncDelayedTask(RcbAPI.getInstance(), new Runnable() {
            @Override
            public void run() {
                if(account.isPresent()){
                    RankUnit rank = account.get().getDataRank().getRank();
                    if(rank.getPower() < 20){
                        e.setJoinMessage(rank.getPrefix() + player.getDisplayName());
                    }
                    else
                    {
                        e.setJoinMessage(null);
                    }
                }
                else
                {
                    e.setJoinMessage(null);
                }
            }
        }, 20L);

    }

    private void initScoreboardPlayer(Player player){
        ScoreboardSign scoreboard = new ScoreboardSign(player, "§6§l§nRushcubeland");
        scoreboard.create();
        scoreboard.setLine(0, "§7 ");
        scoreboard.setLine(1, "§fCompte: ");
        scoreboard.setLine(2, "§c ");
        scoreboard.setLine(3, "§fCoins: §c");
        scoreboard.setLine(4, "§7 ");
        scoreboard.setLine(5, "§fJoueurs en ligne: ");
        scoreboard.setLine(6, "§4 ");
        scoreboard.setLine(7, "§eplay.rushcubeland.fr");
        RcbAPI.getInstance().boards.put(player, scoreboard);

    }

}
