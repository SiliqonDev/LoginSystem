package com.siliqon.loginsystem.files;

import de.exlll.configlib.Comment;
import de.exlll.configlib.Configuration;

@Configuration
public class MainConfig {

    @Comment({"Enable the plugin?"})
    private Boolean pluginEnabled = true;

    @Comment({" ", "Force all players to set a password?", "If this option is disabled, a player can optionally set a password with /password set"})
    private Boolean forcePassword = true;
    @Comment({"Remind players to set a password for safety?"})
    private Boolean remindPassword = true;

    public Boolean getRemindPassword() {
        return remindPassword;
    }

    public Boolean getPluginEnabled() {
        return pluginEnabled;
    }

    public Boolean getForcePassword() {
        return forcePassword;
    }
}
