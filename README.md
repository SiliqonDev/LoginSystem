# LOGIN SYSTEM

### A plugin that allows players to set passwords and log in safely!


> ## Commands

This plugin provides 2 new commands -

/login <password>
/password set <password> <confirmPassword>

These commands require no permission nodes.


> ## Configuration

```
# Enable the plugin?
plugin-enabled: true
# 
# Force all players to set a password?
# If this option is disabled, a player can optionally set a password with /password set
force-password: false
# Remind players to set a password for safety?
remind-password: true

# Authors: Siliqon
```

- The plugin also comes with a lang.yml file which allows you to edit all the messages in the plugin.


> ## How it works

If a player logs in and they do not have a password set, they can play freely without bother unless force-password is turned on in which case they will have to set a password upon first login.

A player can change their password at any time as long as they are currently logged in and online.

If a password is set, the player will be prompted to enter it every time they log in.

All passwords are sha-256 hashed meaning that they are super secure!
