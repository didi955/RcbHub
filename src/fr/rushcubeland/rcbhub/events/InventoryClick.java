package fr.rushcubeland.rcbhub.events;

import fr.rushcubeland.rcbhub.gui.GuiUnit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClick implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){
        if(e.getInventory() == null || e.getCurrentItem() == null){
            e.setCancelled(true);
            return;
        }
        if(e.getInventory() == GuiUnit.Main_Menu.getInv()){
            e.setCancelled(true);
        }
    }
}
