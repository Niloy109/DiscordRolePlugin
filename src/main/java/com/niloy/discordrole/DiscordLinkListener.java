package com.niloy.discordrole;

import github.scarsz.discordsrv.api.events.AccountLinkedEvent;
import github.scarsz.discordsrv.api.events.AccountUnlinkedEvent;
import github.scarsz.discordsrv.dependencies.jda.api.entities.Guild;
import github.scarsz.discordsrv.dependencies.jda.api.entities.Role;
import github.scarsz.discordsrv.util.DiscordUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class DiscordLinkListener implements Listener {

    @EventHandler
    public void onLinked(AccountLinkedEvent event) {
        DiscordUtil.runTask(() -> {
            String roleId = DiscordRolePlugin.getInstance().getConfig().getString("linked-role-id");
            Guild guild = github.scarsz.discordsrv.DiscordSRV.getPlugin().getMainGuild();
            Role role = guild.getRoleById(roleId);
            if(role != null) guild.addRoleToMember(event.getDiscordId(), role).queue();
        });
    }

    @EventHandler
    public void onUnlinked(AccountUnlinkedEvent event) {
        DiscordUtil.runTask(() -> {
            String roleId = DiscordRolePlugin.getInstance().getConfig().getString("linked-role-id");
            Guild guild = github.scarsz.discordsrv.DiscordSRV.getPlugin().getMainGuild();
            Role role = guild.getRoleById(roleId);
            if(role != null) guild.removeRoleFromMember(event.getDiscordId(), role).queue();
        });
    }
}
