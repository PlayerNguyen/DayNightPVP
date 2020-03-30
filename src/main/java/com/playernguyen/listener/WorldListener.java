package com.playernguyen.listener;

import com.playernguyen.DayNightPVP;
import com.playernguyen.config.ConfigKey;
import com.playernguyen.event.WorldServerTickEvent;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.flags.StateFlag;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class WorldListener implements Listener {

    private DayNightPVP plugin = DayNightPVP.getInstance();

    @EventHandler
    public void onTick(WorldServerTickEvent e) {
        if (e.getTime() >= plugin.getConfigManager().getLong(ConfigKey.DAY_TIME_FROM)
        && e.getTime() <= plugin.getConfigManager().getLong(ConfigKey.DAY_TIME_TO)) {
            if (e.getTime() == plugin.getConfigManager().getLong(ConfigKey.DAY_TIME_FROM)) {
                // Broadcast
                Bukkit.broadcastMessage(
                        ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getString(ConfigKey.DAY_MESSAGE))
                );
            }
            plugin.getAvailableRegion().forEach(region -> {
                if (plugin.getConfigManager().getBool(ConfigKey.DAY_PVP)) {
                    region.setFlag(Flags.PVP, StateFlag.State.ALLOW);
                } else {
                    region.setFlag(Flags.PVP, StateFlag.State.DENY);
                }
            });
        }


        if (e.getTime() >= plugin.getConfigManager().getLong(ConfigKey.NIGHT_TIME_FROM)
                && e.getTime() <= plugin.getConfigManager().getLong(ConfigKey.NIGHT_TIME_TO)) {
            if (e.getTime() == plugin.getConfigManager().getLong(ConfigKey.NIGHT_TIME_FROM)) {
                // Broadcast
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getString(ConfigKey.NIGHT_MESSAGE)));
            }
            plugin.getAvailableRegion().forEach(region -> {
                if (plugin.getConfigManager().getBool(ConfigKey.NIGHT_PVP)) {
                    region.setFlag(Flags.PVP, StateFlag.State.ALLOW);
                } else {
                    region.setFlag(Flags.PVP, StateFlag.State.DENY);
                }
            });
        }

    }

}
