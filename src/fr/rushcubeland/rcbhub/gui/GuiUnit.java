package fr.rushcubeland.rcbhub.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public enum GuiUnit {

    Main_Menu("§cMenu Principal", 54);

    private String name;
    private int size;
    private Inventory inv;

    private static ArrayList<GuiUnit> Gui = new ArrayList<>();

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
            Gui.add(gui);
        }
    }

    public static void initAllGui(){
        initGui();
        initAllContents();
    }

    private static void initAllContents(){
        MenuPrincipal.initContents();
    }

    public static Optional<GuiUnit> getByName(String name){
        return Arrays.stream(values()).filter(r -> r.getName().equalsIgnoreCase(name)).findFirst();
    }
}
