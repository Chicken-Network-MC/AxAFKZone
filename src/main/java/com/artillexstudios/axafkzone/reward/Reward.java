package com.artillexstudios.axafkzone.reward;

import com.artillexstudios.axapi.items.WrappedItemStack;
import com.artillexstudios.axapi.scheduler.Scheduler;
import com.artillexstudios.axapi.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Reward {
    private final List<String> commands;
    private final List<ItemStack> items;
    private final double chance;
    private final String display;

    public Reward(Map<Object, Object> str) {
        final List<String> commands = (List<String>) str.getOrDefault("commands", new ArrayList<>());
        final ArrayList<ItemStack> items = new ArrayList<>();
        Number chance = (Number) str.get("chance");

        String display = null;
        if (str.containsKey("display")) display = (String) str.get("display");

        List<Map<Object, Object>> itemMaps = (List<Map<Object, Object>>) str.getOrDefault("items", new ArrayList<>());
        for (Map<Object, Object> itemMap : itemMaps) {
            WrappedItemStack wrapped = ItemBuilder.create(itemMap).glow(true).wrapped();
            if (wrapped != null) items.add(wrapped.toBukkit());
        }

        this.chance = chance.doubleValue();
        this.items = items;
        this.commands = commands;
        this.display = display;
    }

    public List<String> getCommands() {
        return commands;
    }

    public double getChance() {
        return chance;
    }

    public String getDisplay() {
        return display;
    }

    public void run(Player player) {
        Scheduler.get().run(scheduledTask -> {
            for (String cmd : commands) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd.replace("%player%", player.getName()));
            }

            if (!items.isEmpty()) {
                items.forEach(item -> player.getInventory().addItem(item));
            }
        });
    }

    @Override
    public String toString() {
        return "Reward{" + "commands=" + commands + ", items=" + items + ", chance=" + chance + '}';
    }
}
