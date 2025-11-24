package com.artillexstudios.axafkzone.commands.subcommands;

import com.artillexstudios.axafkzone.utils.FileUtils;
import com.artillexstudios.axafkzone.zones.Zone;
import org.bukkit.command.CommandSender;

import java.util.Collections;

import static com.artillexstudios.axafkzone.AxAFKZone.MESSAGEUTILS;

public enum Delete {
    INSTANCE;

    public void execute(CommandSender sender, Zone zone) {
        FileUtils.delete(zone);
        MESSAGEUTILS.sendLang(sender, "zone.deleted", Collections.singletonMap("%name%", zone.getName()));
    }
}
