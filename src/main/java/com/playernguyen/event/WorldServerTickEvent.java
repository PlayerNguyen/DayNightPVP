package com.playernguyen.event;

import org.bukkit.World;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class WorldServerTickEvent extends Event {

    private static final HandlerList handlerList = new HandlerList();

    private World world;
    private Long time;

    public WorldServerTickEvent(World world, Long time) {
        this.world = world;
        this.time = time;
    }

    public World getWorld() {
        return world;
    }

    public Long getTime() {
        return time;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    public HandlerList getHandlers() {
        return handlerList;
    }

}
