package fr.rushcubeland.rcbhub;

import fr.rushcubeland.rcbhub.commands.CoinsCommand;
import fr.rushcubeland.rcbhub.events.*;
import fr.rushcubeland.rcbhub.tasks.ScoreboardReloadTask;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class RcbHub extends JavaPlugin {

    private static RcbHub instance;

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("RcbHub enabled");
        registerListeners();
        registerCommands();

        reloadScoreboardTask();
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("RcbHub disabled");
    }

    private void registerListeners(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerJoin(), this);
        pm.registerEvents(new PlayerQuit(), this);
        pm.registerEvents(new PlayerChat(), this);
        pm.registerEvents(new NPCRightClick(), this);
        pm.registerEvents(new PlayerInteract(), this);
        pm.registerEvents(new InventoryClick(), this);
        pm.registerEvents(new PlaceBlock(), this);
        pm.registerEvents(new BreakBlock(), this);
        pm.registerEvents(new DropItem(), this);
        pm.registerEvents(new CreatureSpawn(), this);
        pm.registerEvents(new PickupItem(), this);
        pm.registerEvents(new ChunksLoad(), this);

    }

    private void registerCommands(){
        getCommand("coins").setExecutor(new CoinsCommand(this));
    }

    private void reloadScoreboardTask(){
        ScoreboardReloadTask reloadTask = new ScoreboardReloadTask(this);
        reloadTask.runTaskTimer(this, 0L, 40L);
    }

    public static RcbHub getInstance() {
        return instance;
    }
}
