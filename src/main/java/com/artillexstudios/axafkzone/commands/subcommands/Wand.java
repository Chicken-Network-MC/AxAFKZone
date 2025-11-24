package com.artillexstudios.axafkzone.commands.subcommands;

import com.artillexstudios.axapi.items.NBTWrapper;
import com.artillexstudios.axapi.items.WrappedItemStack;
import com.artillexstudios.axapi.utils.ItemBuilder;
import org.bukkit.entity.Player;

import static com.artillexstudios.axafkzone.AxAFKZone.LANG;

public enum Wand {
    INSTANCE;

    public void execute(Player sender) {
        WrappedItemStack wrapped = ItemBuilder.create(LANG.getSection("selection-wand")).glow(true).wrapped();
        NBTWrapper wrapper = new NBTWrapper(wrapped);
        wrapper.set("axafkzone-wand", true);
        wrapper.build();
        sender.getInventory().addItem(wrapped.toBukkit());
    }
}
