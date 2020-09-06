package fr.rushcubeland.rcbhub.events;

import fr.rushcubeland.commons.Account;
import fr.rushcubeland.rcbapi.RcbAPI;
import fr.rushcubeland.rcbapi.data.redis.RedisAccess;
import fr.rushcubeland.rcbapi.rank.RankUnit;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;

public class PlayerQuit implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player player = e.getPlayer();

        if(RcbAPI.getInstance().boards.containsKey(player)){
            RcbAPI.getInstance().boards.get(player).destroy();
        }
        Bukkit.getScheduler().runTaskAsynchronously(RcbAPI.getInstance(), new Runnable() {
            @Override
            public void run() {

                final RedissonClient redissonClient = RedisAccess.INSTANCE.getRedissonClient();
                final String key = "account:" + player.getUniqueId().toString();
                final RBucket<Account> accountRBucket = redissonClient.getBucket(key);

                final Account account = accountRBucket.get();

                RankUnit rank = account.getRank();
                if(rank.getPower() <= 45){
                    e.setQuitMessage(rank.getPrefix() + player.getDisplayName() + " §ca quitté le Lobby !");
                }
                else
                {
                    e.setQuitMessage(null);
                }
            }
        });
    }
}
