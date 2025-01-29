package com.siliqon.loginsystem.listeners;

import com.siliqon.loginsystem.LoginSystem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import static com.siliqon.loginsystem.utils.misc.sendMessage;

public class PlayerListener implements Listener {

    private final LoginSystem plugin = LoginSystem.getPlugin(LoginSystem.class);

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        PersistentDataContainer pdata = player.getPersistentDataContainer();

        pdata.set(plugin.loggedinKey, PersistentDataType.BOOLEAN, false);

        if (plugin.config.getForcePassword()) {
            if (!pdata.has(plugin.passwordKey, PersistentDataType.STRING)) {
                sendMessage(player, plugin.lang.getMustSetPassword(), false);
            } else if (pdata.has(plugin.passwordKey, PersistentDataType.STRING)) {
                sendMessage(player, plugin.lang.getEnterPassword(), false);
            }
        } else if (pdata.has(plugin.passwordKey, PersistentDataType.STRING)) {
            sendMessage(player, plugin.lang.getEnterPassword(), false);
        } else if (plugin.config.getRemindPassword()) {
            sendMessage(player, plugin.lang.getSetPasswordReminder(), true);
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        PersistentDataContainer pdata = player.getPersistentDataContainer();

        if (Boolean.TRUE.equals(pdata.get(plugin.loggedinKey, PersistentDataType.BOOLEAN))) {
            return;
        }

        if (plugin.config.getForcePassword()) {
            if (!pdata.has(plugin.passwordKey, PersistentDataType.STRING)) {
                sendMessage(player, plugin.lang.getMustSetPassword(), false);
            } else if (pdata.has(plugin.passwordKey, PersistentDataType.STRING)) {
                sendMessage(player, plugin.lang.getEnterPassword(), false);
            }
        } else if (pdata.has(plugin.passwordKey, PersistentDataType.STRING)) {
            sendMessage(player, plugin.lang.getEnterPassword(), false);
        } else {
            pdata.set(plugin.loggedinKey, PersistentDataType.BOOLEAN, true);
            return;
        }

        e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        PersistentDataContainer pdata = player.getPersistentDataContainer();

        if (Boolean.TRUE.equals(pdata.get(plugin.loggedinKey, PersistentDataType.BOOLEAN))) {
            return;
        }

        if (plugin.config.getForcePassword()) {
            if (!pdata.has(plugin.passwordKey, PersistentDataType.STRING)) {
                sendMessage(player, plugin.lang.getMustSetPassword(), false);
            } else if (pdata.has(plugin.passwordKey, PersistentDataType.STRING)) {
                sendMessage(player, plugin.lang.getEnterPassword(), false);
            }
        } else if (pdata.has(plugin.passwordKey, PersistentDataType.STRING)) {
            sendMessage(player, plugin.lang.getEnterPassword(), false);
        } else {
            pdata.set(plugin.loggedinKey, PersistentDataType.BOOLEAN, true);
            return;
        }

        e.setCancelled(true);
    }
}
