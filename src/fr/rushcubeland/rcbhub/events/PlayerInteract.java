package fr.rushcubeland.rcbhub.events;

import fr.rushcubeland.rcbhub.gui.GuiUnit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteract implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player player = e.getPlayer();
        if(e.getItem() == null){
            return;
        }
        ItemStack current = e.getItem();
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR){
            if(current.getType() == Material.DIAMOND){
                GuiUnit.Main_Menu.open(player);

            }
        }

    }
}
