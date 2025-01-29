package com.siliqon.loginsystem.utils;

import com.siliqon.loginsystem.LoginSystem;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class misc {
    private static final LoginSystem plugin = LoginSystem.getInstance();

    public static void log(String message) {
        plugin.getLogger().info(message);
    }
    public static void logError(String message) {
        plugin.getLogger().severe(message);
    }

    public static void sendMessage(Player player, String message, Boolean prefixed) {
        if (prefixed) player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.PREFIX + message));
        else player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
}
