package com.siliqon.loginsystem;

import co.aikar.commands.PaperCommandManager;
import com.siliqon.loginsystem.commands.LoginCommand;
import com.siliqon.loginsystem.commands.PasswordCommand;
import com.siliqon.loginsystem.files.*;
import com.siliqon.loginsystem.listeners.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

import static com.siliqon.loginsystem.utils.Files.initFiles;
import static com.siliqon.loginsystem.utils.misc.log;
import static com.siliqon.loginsystem.utils.misc.logError;

public final class LoginSystem extends JavaPlugin {
    private static LoginSystem INSTANCE; {INSTANCE = this;}
    public final String PLUGIN_VERSION = "v3.0";

    public String PREFIX = ChatColor.translateAlternateColorCodes('&', "&8[&bLogin&8]&r ");

    public NamespacedKey passwordKey = new NamespacedKey(this, "login_password");
    public NamespacedKey loggedinKey = new NamespacedKey(this, "loggedin");

    private PaperCommandManager commandManager;

    public MainConfig config;
    public LangFile lang;

    @Override
    public void onEnable() {
        // init files
        initFiles();

        // plugin enabled ?
        if (!config.getPluginEnabled()) {
            logError("Plugin is disabled in config.yml. Disabling plugin.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        // register stuff
        commandManager = new PaperCommandManager(this);
        registerListeners();
        registerCommands();

        // done
        log(PLUGIN_VERSION + " enabled successfully.");
    }

    @Override
    public void onDisable() {
        // done
        log(PLUGIN_VERSION + " disabled.");
    }

    public void registerCommands() {
        commandManager.registerCommand(new LoginCommand());
        commandManager.registerCommand(new PasswordCommand());
    }

    public void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
    }

    public static LoginSystem getInstance() {
        return INSTANCE;
    }
}
