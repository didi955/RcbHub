package fr.rushcubeland.rcbhub.events;

import fr.rushcubeland.rcbapi.RcbAPI;
import fr.rushcubeland.rcbapi.account.Account;
import fr.rushcubeland.rcbapi.account.RankUnit;
import fr.rushcubeland.rcbapi.tools.scoreboard.ScoreboardSign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        Account account = new Account(player.getUniqueId());
        account.onLogin();
        RcbAPI.getInstance().getTablist().sendTabList(player);
        initScoreboardPlayer(player);
        RankUnit rank = account.getDataRank().getRank();
        RcbAPI.getInstance().getTablist().setTabListPlayer(player, rank);
        if(rank.getPower() < 20){
            e.setJoinMessage(rank.getPrefix() + player.getDisplayName() + " §ba rejoin le Lobby !");
        }
        else {
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
        scoreboard.setLine(5, "§fPass de combat: §5Palier 14");
        scoreboard.setLine(6, "§b ");
        scoreboard.setLine(7, "§fJoueurs en ligne: ");
        scoreboard.setLine(8, "§4 ");
        scoreboard.setLine(9, "§eplay.rushcubeland.fr");
        RcbAPI.getInstance().boards.put(player, scoreboard);

    }

}
