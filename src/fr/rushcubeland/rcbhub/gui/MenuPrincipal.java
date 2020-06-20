package fr.rushcubeland.rcbhub.gui;

import fr.rushcubeland.rcbapi.tools.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MenuPrincipal {

    public static void initContents(){
        ItemStack dbr = new ItemBuilder(Material.BEACON).setName("§6DeterrentBorder §f[§cRANKED§f]").toItemStack();
        dbr.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        ItemMeta dbrm = dbr.getItemMeta();
        dbrm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        dbrm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        dbr.setItemMeta(dbrm);
        GuiUnit.Main_Menu.addItem(22, dbr);

        ItemStack db = new ItemBuilder(Material.BEACON).setName("§6DeterrentBorder §f[CASUAL]").toItemStack();
        ItemMeta dbm = db.getItemMeta();
        dbm.setDisplayName("§6Deterrent Border §f[CASUAL]");
        db.setItemMeta(dbm);
        GuiUnit.Main_Menu.addItem(21, dbr);

    }

    private void initGlass(Material material){
        ItemStack glass = new ItemBuilder(material).toItemStack();
        for(int i = 0; i == 10; i++){
            GuiUnit.Main_Menu.addItem(i, glass);
        }
        for(int i = 45; i == 53; i++){
            GuiUnit.Main_Menu.addItem(i, glass);
        }
    }
}
