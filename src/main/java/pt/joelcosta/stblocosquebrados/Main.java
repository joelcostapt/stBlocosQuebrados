package pt.joelcosta.stblocosquebrados;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pt.joelcosta.stblocosquebrados.commands.BlocosCommand;
import pt.joelcosta.stblocosquebrados.listeners.PlayerEvents;
import pt.joelcosta.stblocosquebrados.managers.PlayerManager;
import pt.joelcosta.stblocosquebrados.utils.MySQL;

public final class Main extends JavaPlugin {

    public static MySQL mySQL;
    public static PlayerManager playerManager;
    public static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        loadConfig();
        mySQL = new MySQL(getConfig().getString("MySQL.User"), getConfig().getString("MySQL.Password"), getConfig().getString("MySQL.Host"), getConfig().getInt("MySQL.Port"), getConfig().getString("MySQL.Database"));
        playerManager = new PlayerManager();

        getCommand("blocos").setExecutor(new BlocosCommand());
        Bukkit.getPluginManager().registerEvents(new PlayerEvents(), this);
    }

    @Override
    public void onDisable() {
    }

    private void loadConfig(){
        getConfig().options().copyDefaults(false);
        saveDefaultConfig();
    }

    public static Main getInstance() {
        return instance;
    }

    public static MySQL getMySQL() {
        return mySQL;
    }

    public static PlayerManager getPlayerManager() {
        return playerManager;
    }
}
