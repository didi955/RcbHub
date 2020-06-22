package fr.rushcubeland.rcbhub.gui;

import fr.rushcubeland.rcbapi.RcbAPI;
import fr.rushcubeland.rcbapi.account.Account;
import fr.rushcubeland.rcbapi.tools.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.HashMap;
import java.util.Optional;

public class MenuPrincipal {

    private static HashMap<Player, Inventory> GUI = new HashMap<>();

    public static void OpenInv(Player player){

        Inventory inventory = Bukkit.createInventory(null, 54, "§cMenu Principal");
        initGlass(inventory, Material.CYAN_STAINED_GLASS_PANE);

        ItemStack dbr = new ItemBuilder(Material.BEACON).setName("§6DeterrentBorder §f[§cRANKED§f]").toItemStack();
        dbr.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        ItemMeta dbrm = dbr.getItemMeta();
        dbrm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        dbr.setItemMeta(dbrm);
        inventory.setItem(23, dbr);

        ItemStack db = new ItemBuilder(Material.BEACON).setName("§6DeterrentBorder §f[CASUAL]").toItemStack();
        inventory.setItem(21, db);

        Optional<Account> account = RcbAPI.getInstance().getAccount(player);
        if(account.isPresent()){
            ItemStack headp = new ItemBuilder(Material.PLAYER_HEAD).setName("§6Informations:").setLore("§c ", "§fGrade: " + account.get().getDataRank().getRank().getPrefix(), "§fCoins: §c" + account.get().getDataCoins().getCoins() + " §e⛁", "§fPalier Pass de combat: §a14", "§f   ", "§aPlus d'avantages ?", "§ehttps://store.rushcubeland.fr").toItemStack();
            SkullMeta headpm = (SkullMeta) headp.getItemMeta();
            headpm.setOwningPlayer(player);
            headp.setItemMeta(headpm);
            inventory.setItem(27, headp);
        }

        ItemStack jump = new ItemBuilder(Material.GOLDEN_BOOTS).setName("§bParcours").setLore("§b ","§7Saute de bloc en bloc pour terminer le parcours", "§4 ", "§e➤ Se teleporter").toItemStack();
        inventory.setItem(35, jump);

        ItemStack info = new ItemBuilder(Material.COMPASS).setName("§6Hub").setLore(" ", "§7Le hub est le lieu principal du serveur,", "§7il vous permet de vous amuser avec vos amis,", "§7d'explorer ses divers endroits ou de vous poser", "§7à coté de l'eau", " ", "§f➤ Il y a §e" + Bukkit.getOnlinePlayers().size() + " §fjoueurs sur le hub").toItemStack();
        inventory.setItem(4, info);

        ItemStack close = new ItemBuilder(Material.ACACIA_DOOR).setName("§cFermer").toItemStack();
        inventory.setItem(49, close);



        GUI.put(player, inventory);
        player.openInventory(inventory);

    }

    private static void initGlass(Inventory inventory, Material material){
        ItemStack glass = new ItemBuilder(material).toItemStack();
        glass.getItemMeta().addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        inventory.setItem(0, glass);
        inventory.setItem(1, glass);
        inventory.setItem(7, glass);
        inventory.setItem(8, glass);
        inventory.setItem(9, glass);
        inventory.setItem(17, glass);
        inventory.setItem(36, glass);
        inventory.setItem(45, glass);
        inventory.setItem(46, glass);
        inventory.setItem(52, glass);
        inventory.setItem(44, glass);
        inventory.setItem(53, glass);
    }

    public static Inventory getSpecifiedInv(Player player){
        if(GUI.containsKey(player)){
            return GUI.get(player);
        }
        return null;
    }
}
