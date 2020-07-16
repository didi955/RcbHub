package fr.rushcubeland.rcbhub.events;

import fr.rushcubeland.rcbapi.tools.ItemBuilder;
import fr.rushcubeland.rcbhub.RcbHub;
import fr.rushcubeland.rcbhub.gui.MenuPrincipal;
import fr.rushcubeland.rcbhub.messaging.BukkitSend;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;

public class PlayerInteract implements Listener {

    private final HashMap<Player, Boolean> dataTartareShadow = new HashMap<>();

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player player = e.getPlayer();
        if(e.getItem() == null){
            return;
        }
        ItemStack current = e.getItem();
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR){
            if(current.getType().equals(Material.COMPASS)){
                MenuPrincipal.OpenInv(player);
            }
            if(current.getType().equals(Material.COMPARATOR)){
                BukkitSend.CmdToProxy(player, "opt");
            }
            if(current.getType().equals(Material.CLOCK)){
                if(Bukkit.getOnlinePlayers().size() <= 1){
                    return;
                }
                if(dataTartareShadow.containsKey(player)){
                    if(dataTartareShadow.get(player).equals(true)){
                        dataTartareShadow.put(player, false);
                        ItemStack magicClock = new ItemBuilder(Material.CLOCK).setName("§7Ombre de Tartare: §cDésactivé").setLore("§f ", "").toItemStack();
                        player.getInventory().setItem(4, magicClock);
                        for(Player pls : Bukkit.getOnlinePlayers()){
                            player.showPlayer(RcbHub.getInstance(), pls);
                        }
                    }
                    else {
                        dataTartareShadow.put(player, true);
                        ItemStack magicClock = new ItemBuilder(Material.CLOCK).setName("§7Ombre de Tartare: §aActivé").setLore("§f ", "").toItemStack();
                        player.getInventory().setItem(4, magicClock);
                        for(Player pls : Bukkit.getOnlinePlayers()){
                            player.hidePlayer(RcbHub.getInstance(), pls);
                        }
                    }
                }
                else
                {
                    dataTartareShadow.put(player, true);
                    ItemStack magicClock = new ItemBuilder(Material.CLOCK).setName("§7Ombre de Tartare: §aActivé").setLore("§f ", "").toItemStack();
                    player.getInventory().setItem(4, magicClock);
                    for(Player pls : Bukkit.getOnlinePlayers()){
                        player.hidePlayer(RcbHub.getInstance(), pls);
                    }
                }
                player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 30, 1));
                player.playSound(player.getLocation(), Sound.ENTITY_PHANTOM_AMBIENT, SoundCategory.AMBIENT, 1F, 1F);
            }
        }
    }
}
