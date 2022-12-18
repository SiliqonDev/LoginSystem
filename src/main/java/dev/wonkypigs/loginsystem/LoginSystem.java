package dev.wonkypigs.loginsystem;

import com.tchristofferson.configupdater.ConfigUpdater;
import dev.wonkypigs.loginsystem.commands.*;
import dev.wonkypigs.loginsystem.listeners.PlayerJoinLeaveListener;
import org.bstats.bukkit.Metrics;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public final class LoginSystem extends JavaPlugin {

    public String prefix = ChatColor.translateAlternateColorCodes('&', "&8&l[&6LoginSystem&8]&r ");

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        // if config version is old, update it to current version
        File configFile = new File(getDataFolder(), "config.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);

        if (config.getDouble("config-version") != 1.0) {
            config.set("config-version", 1.0);
            try {
                ConfigUpdater.update(this, "config.yml", configFile, Arrays.asList("none"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            // save changes
            try {
                config.save(configFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // reload config
            reloadConfig();
            getLogger().info("Updated config file to latest version");
        }

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
