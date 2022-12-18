package dev.wonkypigs.loginsystem;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class UpdateChecker implements Listener {

    private static final LoginSystem plugin = LoginSystem.getInstance();

    private String url = "https://api.spigotmc.org/legacy/update.php?resource=";
    private String id = "104726";

    private boolean isAvailable;
    private String remoteVersion;

    @EventHandler
    public void on(PlayerJoinEvent event) {
        if (!plugin.getConfig().getBoolean("check-for-updates")) {
            return;
        }
        if (event.getPlayer().isOp()) {
            check();
            if (isAvailable) {
                event.getPlayer().sendMessage("&a&m---------------------------------".replace("&", "§"));
                event.getPlayer().sendMessage("&b&lThere is a new update available for LoginSystem!\n&c&lCurrent version: &d{current}\n&a&lNew version: &d{new}\n&a&lDownload Here: &a{link}"
                        .replace("{current}", plugin.getDescription().getVersion())
                        .replace("{new}", remoteVersion)
                        .replace("{link}", "www.spigotmc.org/resources/104726")
                        .replace("&", "§"));
                event.getPlayer().sendMessage("&a&m---------------------------------".replace("&", "§"));
            }
        }
    }

    public void check() {
        isAvailable = checkUpdate();
    }

    private boolean checkUpdate() {
        plugin.getLogger().info("Checking for updates...");
        try {
            String localVersion = LoginSystem.getInstance().getDescription().getVersion();
            HttpsURLConnection connection = (HttpsURLConnection) new URL(url + id).openConnection();
            connection.setRequestMethod("GET");
            String raw = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();

            if(raw.contains("-")) {
                remoteVersion = raw.split("-")[0].trim();
            } else {
                remoteVersion = raw;
            }

            if(!localVersion.equalsIgnoreCase(remoteVersion))
                return true;

        } catch (IOException e) {
            return false;
        }
        return false;
    }

}