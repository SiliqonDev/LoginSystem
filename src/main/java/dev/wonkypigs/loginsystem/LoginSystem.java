package dev.wonkypigs.loginsystem;

import dev.wonkypigs.loginsystem.commands.*;
import dev.wonkypigs.loginsystem.listeners.PlayerJoinLeaveListener;
import org.bstats.bukkit.Metrics;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class LoginSystem extends JavaPlugin {

    public String prefix = ChatColor.translateAlternateColorCodes('&', "&8&l[&6LoginSystem&8]&r ");

    @Override
    public void onEnable() {
        registerCommands();
        registerListeners();

        int pluginId = 17094;
        Metrics metrics = new Metrics(this, pluginId);

        UpdateChecker updateChecker = new UpdateChecker();
        updateChecker.check();

        getLogger().info("LoginSystem has been enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("LoginSystem has been disabled.");
    }

    public void registerCommands() {
        getCommand("login").setExecutor(new LoginCommand());
        getCommand("setpassword").setExecutor(new SetpasswordCommand());
        getCommand("changepassword").setExecutor(new ChangepasswordCommand());
    }

    public void registerListeners() {
        getServer().getPluginManager().registerEvents(new PlayerJoinLeaveListener(), this);
        getServer().getPluginManager().registerEvents(new UpdateChecker(), this);
    }

    public static LoginSystem getInstance() {
        return getPlugin(LoginSystem.class);
    }
}
