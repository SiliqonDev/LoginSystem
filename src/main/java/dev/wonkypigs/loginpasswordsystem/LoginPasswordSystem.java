package dev.wonkypigs.loginpasswordsystem;

import dev.wonkypigs.loginpasswordsystem.commands.LoginCommand;
import dev.wonkypigs.loginpasswordsystem.commands.SetpasswordCommand;
import dev.wonkypigs.loginpasswordsystem.listeners.PlayerLeaveJoinListener;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class LoginPasswordSystem extends JavaPlugin {

    public String prefix = ChatColor.translateAlternateColorCodes('&', "&8&l[&6LoginSystem&8]&r ");

    @Override
    public void onEnable() {
        getLogger().info("LoginPasswordSystem has been enabled.");

        getServer().getPluginManager().registerEvents(new PlayerLeaveJoinListener(), this);
        getCommand("login").setExecutor(new LoginCommand());
        getCommand("setpassword").setExecutor(new SetpasswordCommand());
    }

    @Override
    public void onDisable() {
        getLogger().info("LoginPasswordSystem has been disabled.");
    }
}
