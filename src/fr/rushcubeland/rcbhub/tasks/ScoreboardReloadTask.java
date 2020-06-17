package fr.rushcubeland.rcbhub.tasks;

import fr.rushcubeland.rcbapi.RcbAPI;
import fr.rushcubeland.rcbapi.account.Account;
import fr.rushcubeland.rcbapi.network.Network;
import fr.rushcubeland.rcbapi.tools.scoreboard.ScoreboardSign;
import fr.rushcubeland.rcbhub.RcbHub;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;
import java.util.Optional;

public class ScoreboardReloadTask extends BukkitRunnable {

    private RcbHub rcbHub;

    public ScoreboardReloadTask(RcbHub rcbHub) {
        this.rcbHub = rcbHub;
    }

    @Override
    public void run() {

        for(Map.Entry<Player, ScoreboardSign> sign : RcbAPI.getInstance().boards.entrySet()){
            Player player = (Player) sign.getKey();
            Optional<Account> account = RcbAPI.getInstance().getAccount(player);
            if(account.isPresent()){
                sign.getValue().setLine(0, "§f ");
                sign.getValue().setLine(1, "§fCompte: " + account.get().getDataRank().getRank().getPrefix() + "§f" + player.getDisplayName());
                sign.getValue().setLine(2, "§c ");
                sign.getValue().setLine(3, "§fCoins: §c" + account.get().getDataCoins().getCoins() + "§e");
                sign.getValue().setLine(4, "§7 ");
                sign.getValue().setLine(5, "§fJoueurs en ligne: " + Network.getNetworkSlots());
                sign.getValue().setLine(6, "§4 ");
                sign.getValue().setLine(7, "§eplay.rushcubeland.fr");
            }
        }
    }
}
