package com.siliqon.loginsystem.files;

import de.exlll.configlib.Configuration;

@Configuration
public class LangFile {

    private String pluginPrefix = "&8[&bLogin&8]&r ";

    private String mustSetPassword = "&cYou must set a password! Use /password set";
    private String passwordSet = "&bYour password has been set.";
    private String enterPassword = "&bPlease enter your password! Use /login <password>";
    private String changedPassword = "&bYour password has been changed.";
    private String wrongPassword = "&cWrong password! Try again.";
    private String correctPassword = "&aCorrect password. Welcome back!";
    private String setPasswordReminder = "&bYou should set a password! Use /password set";
    private String noPasswordSet = "&cYou do not have a password set! Use /password set";
    private String alreadyLoggedIn = "&cYou are already logged in!";
    private String passwordsDoNotMatch = "&cThose passwords do not match! Try again.";
    private String confirmPassword = "&cYou must confirm your password! /password set <password> <confirmPassword>";
    private String cannotChangePassword = "&cYou cannot change your password right now!";
    private String currentPluginVersion = "&bThe current plugin version is %version_string%";

    public String getCurrentPluginVersion() {
        return currentPluginVersion;
    }

    public String getCannotChangePassword() {
        return cannotChangePassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getPasswordsDoNotMatch() {
        return passwordsDoNotMatch;
    }

    public String getAlreadyLoggedIn() {
        return alreadyLoggedIn;
    }

    public String getNoPasswordSet() {
        return noPasswordSet;
    }

    public String getSetPasswordReminder() {
        return setPasswordReminder;
    }

    public String getEnterPassword() {
        return enterPassword;
    }

    public String getPluginPrefix() {
        return pluginPrefix;
    }

    public String getMustSetPassword() {
        return mustSetPassword;
    }

    public String getPasswordSet() {
        return passwordSet;
    }

    public String getChangedPassword() {
        return changedPassword;
    }

    public String getWrongPassword() {
        return wrongPassword;
    }

    public String getCorrectPassword() {
        return correctPassword;
    }
}
