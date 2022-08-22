package dev.wonkypigs.loginsystem;

import dev.wonkypigs.loginsystem.commands.*;
import dev.wonkypigs.loginsystem.listeners.PlayerJoinLeaveListener;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class LoginSystem extends JavaPlugin {

    public String prefix = ChatColor.translateAlternateColorCodes('&', "&8&l[&6LoginSystem&8]&r ");

    @Override
    public void onEnable() {
        getLogger().info("LoginSystem has been enabled.");

        getServer().getPluginManager().registerEvents(new PlayerJoinLeaveListener(), this);
        getCommand("login").setExecutor(new LoginCommand());
        getCommand("setpassword").setExecutor(new SetpasswordCommand());
        getCommand("changepassword").setExecutor(new ChangepasswordCommand());
    }

    @Override
    public void onDisable() {
        getLogger().info("LoginSystem has been disabled.");
    }
}
