package dev.wonkypigs.loginpasswordsystem.listeners;

import dev.wonkypigs.loginpasswordsystem.LoginPasswordSystem;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class PlayerLeaveJoinListener implements Listener {

    private final LoginPasswordSystem plugin = LoginPasswordSystem.getPlugin(LoginPasswordSystem.class);

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        PersistentDataContainer pdata = player.getPersistentDataContainer();
        pdata.set(new NamespacedKey(plugin, "loggedin"), PersistentDataType.STRING, "no");
        if(!pdata.has(new NamespacedKey(plugin, "login_password"), PersistentDataType.STRING)) {
            player.sendMessage(plugin.prefix + ChatColor.RED + "You do not have a password set. Type '/setpassword <password>' to set one.");
        }
        else{
            player.sendMessage(plugin.prefix + ChatColor.GREEN + "You have a password set. Type '/login <password>' to login.");
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        PersistentDataContainer pdata = player.getPersistentDataContainer();
        if(!pdata.has(new NamespacedKey(plugin, "login_password"), PersistentDataType.STRING)) {
            e.setCancelled(true);
        }
        else if(pdata.get(new NamespacedKey(plugin, "loggedin"), PersistentDataType.STRING).equalsIgnoreCase("no")) {
            e.setCancelled(true);
        }
    }
}
