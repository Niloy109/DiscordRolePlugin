package com.niloy.discordrole;

import org.bukkit.plugin.java.JavaPlugin;

public class DiscordRolePlugin extends JavaPlugin {

    private static DiscordRolePlugin instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        getLogger().info("DiscordRolePlugin Enabled!");
        getServer().getPluginManager().registerEvents(new DiscordLinkListener(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("DiscordRolePlugin Disabled!");
    }

    public static DiscordRolePlugin getInstance() {
        return instance;
    }
}
