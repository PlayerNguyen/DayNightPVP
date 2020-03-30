package com.playernguyen;

import com.playernguyen.command.DayNightPVPExecutor;
import com.playernguyen.config.ConfigKey;
import com.playernguyen.config.ConfigManager;
import com.playernguyen.event.WorldServerTickEvent;
import com.playernguyen.listener.WorldListener;
import com.playernguyen.worldguard.WorldGuardAPI;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class DayNightPVP  extends JavaPlugin {

    private static DayNightPVP ins;

    private WorldManager worldManager;
    private WorldTimeManager worldTimeManager;
    private ListenerManager listenerManager;
    private ConfigManager configManager;

    private RegionManager availableRegion;

    private boolean isWorldGuardEnable;

    private WorldGuardAPI worldGuardAPI;

    @Override
    public void onEnable() {
        ins = this;

        this.world();
        this.config();
        this.worldTime();
        this.listener();
        this.worldGuard();
        this.command();

        getConfigManager().getMap().forEach((key, val) -> {
            System.out.println(key + " => " + val.toString());
        });

    }

    private void command() {
        getCommand("daynightpvp").setExecutor(new DayNightPVPExecutor());
    }

    private void worldGuard() {
        this.isWorldGuardEnable = Bukkit.getPluginManager().getPlugin("WorldGuard") != null;
        // Check the region was existed in world or not
        worldGuardAPI = new WorldGuardAPI();
        availableRegion = new RegionManager();
        List<String> worldList = getConfigManager().getListString(ConfigKey.WORLDS);
        List<String> regionList = getConfigManager().getListString(ConfigKey.REGIONS);
        worldList.forEach(worldName -> {
            World world = Bukkit.getWorld(worldName);
            if (world != null) {
                regionList.forEach(region -> {
                    if (!worldGuardAPI.hasRegion(world, region)) {
                        throw new NullPointerException("Cannot found region name " + region);
                    }
                    // If alright, passed and put into available region list
                    getAvailableRegion().add(worldGuardAPI.getRegion(world, region));
                });
            }
        });
    }

    private void config() {
        this.configManager = new ConfigManager();
        configManager.load();
    }

    private void listener() {
        listenerManager = new ListenerManager();
        listenerManager.add(new WorldListener());
        listenerManager.forEach(e->Bukkit.getPluginManager().registerEvents(e, this));
    }

    private void world() {
        this.worldManager = new WorldManager();
        worldManager.addAll(Bukkit.getWorlds());
    }

    private void worldTime() {
        this.worldTimeManager = new WorldTimeManager();
        List<String> worldList = getConfigManager().getListString(ConfigKey.WORLDS);
        Bukkit.getScheduler().runTaskTimer(this, () -> worldManager.forEach((world) -> {
            if (worldList.contains(world.getName())) {
                getWorldTimeManager().put(world, world.getTime());
                Bukkit.getPluginManager().callEvent(new WorldServerTickEvent(world, world.getTime()));
            }
        }), 1, 1);
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public WorldTimeManager getWorldTimeManager() {
        return worldTimeManager;
    }

    public static DayNightPVP getInstance() {
        return ins;
    }

    public WorldManager getWorldManager() {
        return worldManager;
    }

    public ListenerManager getListenerManager() {
        return listenerManager;
    }

    public boolean isWorldGuardEnable() {
        return isWorldGuardEnable;
    }

    public WorldGuardAPI getWorldGuardAPI() {
        return worldGuardAPI;
    }

    public RegionManager getAvailableRegion() {
        return availableRegion;
    }

}
