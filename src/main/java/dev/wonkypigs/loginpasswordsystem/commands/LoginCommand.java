package dev.wonkypigs.loginpasswordsystem.commands;

import dev.wonkypigs.loginpasswordsystem.LoginPasswordSystem;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class LoginCommand implements CommandExecutor {

    private final LoginPasswordSystem plugin = LoginPasswordSystem.getPlugin(LoginPasswordSystem.class);

    @Override
    public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        Player player = (Player) sender;
        PersistentDataContainer pdata = player.getPersistentDataContainer();
        if(!pdata.has(new NamespacedKey(plugin, "login_password"), PersistentDataType.STRING)) {
            player.sendMessage(plugin.prefix + ChatColor.RED + "You do not have a password set. Type '/setpassword <password>' to set one.");
            return true;
        }
        else if(!pdata.get(new NamespacedKey(plugin, "loggedin"), PersistentDataType.STRING).equalsIgnoreCase("yes")) {
            if(args.length == 1) {
                if(pdata.get(new NamespacedKey(plugin, "login_password"), PersistentDataType.STRING).equalsIgnoreCase(args[0])) {
                    pdata.set(new NamespacedKey(plugin, "loggedin"), PersistentDataType.STRING, "yes");
                    player.sendMessage(plugin.prefix + ChatColor.GREEN + "You have successfully logged in.");
                }
                else{
                    player.sendMessage(plugin.prefix + ChatColor.RED + "Incorrect password.");
                }
            }
            else{
                player.sendMessage(plugin.prefix + ChatColor.RED + "Usage: '/login <password>'");
            }
        }else {
            player.sendMessage(plugin.prefix + ChatColor.GREEN + "You are already logged in.");
        }
        return true;
    }
}
