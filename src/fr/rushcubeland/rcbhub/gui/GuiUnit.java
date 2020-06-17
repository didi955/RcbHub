package fr.rushcubeland.rcbhub.gui;

import fr.rushcubeland.rcbapi.tools.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public enum GuiUnit {

    Main_Menu("§cMenu Principal", 64);

    private String name;
    private int size;
    private Inventory inv;

    private static ArrayList<GuiUnit> Gui;

    GuiUnit(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public Inventory getInv(){
        return inv;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public void setInv(Inventory inv) {
        this.inv = inv;
    }

    public void open(Player player){
        player.openInventory(inv);

    }

    public void addItem(Integer slot, ItemStack itemStack){
        inv.setItem(slot, itemStack);

    }

    public static ArrayList<GuiUnit> getGui() {
        return Gui;
    }

    private static void initGui(){
        for(GuiUnit gui : GuiUnit.values()){
            Inventory inv = Bukkit.createInventory(null, gui.getSize(), gui.getName());
            gui.setInv(inv);
            getGui().add(gui);
        }
    }

    public static void initAllGui(){
        initGui();
        initContents();

    }

    private static void initContents(){
        ItemBuilder mycelium = new ItemBuilder(Material.MYCELIUM);
        mycelium.setName("§6MYCELIUM");
        mycelium.setLore("§c ", "§cLINE 2");
        Main_Menu.addItem(15, mycelium.toItemStack());
    }

    public static Optional<GuiUnit> getByName(String name){
        return Arrays.stream(values()).filter(r -> r.getName().equalsIgnoreCase(name)).findFirst();

    }
}
