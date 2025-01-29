    package com.siliqon.loginsystem.commands;

    import co.aikar.commands.BaseCommand;
    import co.aikar.commands.annotation.CommandAlias;
    import co.aikar.commands.annotation.Default;
    import co.aikar.commands.annotation.Optional;
    import co.aikar.commands.annotation.Subcommand;
    import com.google.common.hash.Hashing;
    import com.siliqon.loginsystem.LoginSystem;
    import org.bukkit.entity.Player;
    import org.bukkit.persistence.PersistentDataContainer;
    import org.bukkit.persistence.PersistentDataType;

    import java.nio.charset.StandardCharsets;

    import static com.siliqon.loginsystem.utils.misc.sendMessage;

    @CommandAlias("password|pw")
    public class PasswordCommand extends BaseCommand {

        private final LoginSystem plugin = LoginSystem.getPlugin(LoginSystem.class);

        @Default
        @Subcommand("set")
        public void passwordCommand(Player player, String password, @Optional String confirmPassword) {
            PersistentDataContainer pdata = player.getPersistentDataContainer();
            if ((!pdata.has(plugin.loggedinKey) || Boolean.FALSE.equals(pdata.get(plugin.loggedinKey, PersistentDataType.BOOLEAN))) && pdata.has(plugin.passwordKey, PersistentDataType.STRING)) {
                sendMessage(player, plugin.lang.getCannotChangePassword(), true);
                return;
            }

            if (confirmPassword == null) {
                sendMessage(player, plugin.lang.getConfirmPassword(), true);
                return;
            }

            if (!password.equals(confirmPassword)) {
                sendMessage(player, plugin.lang.getPasswordsDoNotMatch(), true);
                return;
            }

            String enteredHash = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();

            if (pdata.has(plugin.passwordKey, PersistentDataType.STRING)) {
                sendMessage(player, plugin.lang.getChangedPassword(), true);
            } else {
                sendMessage(player, plugin.lang.getPasswordSet(), true);
            }
            pdata.set(plugin.passwordKey, PersistentDataType.STRING, enteredHash);
        }
    }
