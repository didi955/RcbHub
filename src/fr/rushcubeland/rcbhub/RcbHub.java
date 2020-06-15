package fr.rushcubeland.rcbhub;

import fr.rushcubeland.rcbhub.commands.CoinsCommand;
import fr.rushcubeland.rcbhub.events.NPCRightClick;
import fr.rushcubeland.rcbhub.events.PlayerChat;
import fr.rushcubeland.rcbhub.events.PlayerJoin;
import fr.rushcubeland.rcbhub.events.PlayerQuit;
import fr.rushcubeland.rcbhub.tasks.ScoreboardReloadTask;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class RcbHub extends JavaPlugin {

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

    }

    private void registerCommands(){
        getCommand("coins").setExecutor(new CoinsCommand(this));
    }

    private void reloadScoreboardTask(){
        ScoreboardReloadTask reloadTask = new ScoreboardReloadTask(this);
        reloadTask.runTaskTimer(this, 0L, 40L);


    }

}
