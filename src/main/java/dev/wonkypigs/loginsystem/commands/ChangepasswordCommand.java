package dev.wonkypigs.loginsystem.commands;

import dev.wonkypigs.loginsystem.LoginSystem;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;


public class ChangepasswordCommand implements CommandExecutor {

    private final LoginSystem plugin = LoginSystem.getPlugin(LoginSystem.class);

    @Override
    public boolean onCommand(org.bukkit.command.CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        PersistentDataContainer pdata = player.getPersistentDataContainer();
        if(!pdata.has(new NamespacedKey(plugin, "login_password"), PersistentDataType.STRING)) {
            player.sendMessage(plugin.prefix + ChatColor.RED + "You do not have a password set. Type '/setpassword <password>' to set one.");
            return true;
        }
        else if(!pdata.get(new NamespacedKey(plugin, "loggedin"), PersistentDataType.STRING).equalsIgnoreCase("yes")) {
            player.sendMessage(plugin.prefix + ChatColor.RED + "You must be logged in to do that!");
            return true;
        }
        else{
            if(args.length == 2) {
                if(args[0].equals(pdata.get(new NamespacedKey(plugin, "login_password"),PersistentDataType.STRING))) {
                    pdata.set(new NamespacedKey(plugin, "login_password"), PersistentDataType.STRING, args[1]);
                    player.sendMessage(plugin.prefix + ChatColor.GREEN + "Your password has been changed to " + args[1]);
                }
            }
            else{
                player.sendMessage(plugin.prefix + ChatColor.GREEN + "Usage: '/changepassword <old_password> <new_password>'");
            }
        }
        return true;
    }
}
