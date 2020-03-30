package com.playernguyen.worldguard;

import com.playernguyen.DayNightPVP;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.World;

import java.util.Map;
import java.util.Objects;

public class WorldGuardAPI {

    private DayNightPVP plugin = DayNightPVP.getInstance();

    public WorldGuardAPI () {
        if (!plugin.isWorldGuardEnable()) {
            throw new NullPointerException("WorldGuard not found...");
        }
    }

    public RegionContainer getRegionContainer () {
        return WorldGuard.getInstance().getPlatform().getRegionContainer();
    }

    public Map<String, ProtectedRegion> getRegions(World world) {
        return Objects.requireNonNull(getRegionContainer().get(BukkitAdapter.adapt(world)))
                .getRegions();
    }

    public ProtectedRegion getRegion (World world, String name) {
        ProtectedRegion protectedRegion
                = Objects.requireNonNull(getRegionContainer().get(BukkitAdapter.adapt(world))).getRegion(name);
        return protectedRegion;
    }

    public boolean hasRegion(World world, String name) {
        return getRegion(world, name) != null;
    }

    public DayNightPVP getPlugin() {
        return plugin;
    }
}
