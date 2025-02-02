package com.siliqon.loginsystem.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Single;
import co.aikar.commands.annotation.Subcommand;
import com.google.common.hash.Hashing;
import com.siliqon.loginsystem.LoginSystem;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.nio.charset.StandardCharsets;

import static com.siliqon.loginsystem.utils.misc.sendMessage;

@CommandAlias("login")
public class LoginCommand extends BaseCommand {

    private final LoginSystem plugin = LoginSystem.getInstance();

    @Default
    public void loginCommand(Player player, @Single String password) {
        PersistentDataContainer pdata = player.getPersistentDataContainer();

        if (!pdata.has(plugin.passwordKey, PersistentDataType.STRING)) {
            sendMessage(player, plugin.lang.getNoPasswordSet(), true);
            return;
        }
        if (Boolean.TRUE.equals(pdata.get(plugin.loggedinKey, PersistentDataType.BOOLEAN))) {
            sendMessage(player, plugin.lang.getAlreadyLoggedIn(), true);
            return;
        }

        final String enteredHash = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
        if (enteredHash.equals(pdata.get(plugin.passwordKey, PersistentDataType.STRING))) {
            sendMessage(player, plugin.lang.getCorrectPassword(), true);
            pdata.set(plugin.loggedinKey, PersistentDataType.BOOLEAN, true);
        } else {
            sendMessage(player, plugin.lang.getWrongPassword(), true);
        }
    }

    @Subcommand("version")
    public void versionCommand(Player player) {
        sendMessage(player, plugin.lang.getCurrentPluginVersion().replace("%version_string%", plugin.getName() + " &d"+plugin.PLUGIN_VERSION), true);
    }
}
