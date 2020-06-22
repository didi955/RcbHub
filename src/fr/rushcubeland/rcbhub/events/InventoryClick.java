package fr.rushcubeland.rcbhub.events;

import fr.rushcubeland.rcbhub.gui.MenuPrincipal;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClick implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){
        if(e.getInventory() == null || e.getCurrentItem() == null){
            e.setCancelled(true);
            return;
        }
        if(e.getWhoClicked() instanceof Player){
            Player player = (Player) e.getWhoClicked();
            ItemStack current = e.getCurrentItem();
            if(e.getInventory() == MenuPrincipal.getSpecifiedInv(player)){
                e.setCancelled(true);
                if(current.getType().equals(Material.ACACIA_DOOR)){
                    player.closeInventory();
                }
            }
        }
        else
        {
            return;
        }
    }
}
