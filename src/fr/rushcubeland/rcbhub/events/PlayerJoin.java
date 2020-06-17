package fr.rushcubeland.rcbhub.events;

import fr.rushcubeland.rcbapi.RcbAPI;
import fr.rushcubeland.rcbapi.account.Account;
import fr.rushcubeland.rcbapi.account.RankUnit;
import fr.rushcubeland.rcbapi.tools.scoreboard.ScoreboardSign;
import fr.rushcubeland.rcbhub.RcbHub;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Optional;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();

        Optional<Account> account = RcbAPI.getInstance().getAccount(player);
        initScoreboardPlayer(player);
        if(account.isPresent()){
            RankUnit rank = account.get().getDataRank().getRank();
            RcbHub.getInstance().getServer().getScheduler().runTaskLater(RcbHub.getInstance(), new BukkitRunnable() {
                @Override
                public void run() {
                    if(rank.getPower() < 20){
                        e.setJoinMessage(rank.getPrefix() + player.getDisplayName());
                    }
                    else {
                        e.setJoinMessage(null);
                    }
                }
            }, 60L);

        }
        else
        {
            e.setJoinMessage(null);
        }

    }

    private void initScoreboardPlayer(Player player){
        ScoreboardSign scoreboard = new ScoreboardSign(player, "§6§l§nRushcubeland");
        scoreboard.create();
        scoreboard.setLine(0, "§f ");
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
