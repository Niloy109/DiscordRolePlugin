package com.niloy.discordrole;

import github.scarsz.discordsrv.DiscordSRV;
import github.scarsz.discordsrv.api.events.AccountLinkedEvent;
import github.scarsz.discordsrv.api.events.AccountUnlinkedEvent;
import github.scarsz.discordsrv.dependencies.jda.api.entities.Guild;
import github.scarsz.discordsrv.dependencies.jda.api.entities.Role;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class DiscordLinkListener implements Listener {

    @EventHandler
    public void onLinked(AccountLinkedEvent event) {
        Bukkit.getScheduler().runTask(DiscordRolePlugin.getInstance(), () -> {
            String roleId = DiscordRolePlugin.getInstance().getConfig().getString("linked-role-id");
            Guild guild = DiscordSRV.getPlugin().getMainGuild();
            Role role = guild.getRoleById(roleId);
            if (role != null && event.getPlayer().getDiscordId() != null) {
                guild.addRoleToMember(event.getPlayer().getDiscordId(), role).queue();
            }
        });
    }

    @EventHandler
    public void onUnlinked(AccountUnlinkedEvent event) {
        Bukkit.getScheduler().runTask(DiscordRolePlugin.getInstance(), () -> {
            String roleId = DiscordRolePlugin.getInstance().getConfig().getString("linked-role-id");
            Guild guild = DiscordSRV.getPlugin().getMainGuild();
            Role role = guild.getRoleById(roleId);
            if (role != null && event.getPlayer().getDiscordId() != null) {
                guild.removeRoleFromMember(event.getPlayer().getDiscordId(), role).queue();
            }
        });
    }
}
