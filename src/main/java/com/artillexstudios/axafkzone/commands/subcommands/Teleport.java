package com.artillexstudios.axafkzone.commands.subcommands;

import com.artillexstudios.axafkzone.zones.Zone;
import com.artillexstudios.axapi.utils.PaperUtils;
import org.bukkit.entity.Player;

import java.util.Collections;

import static com.artillexstudios.axafkzone.AxAFKZone.MESSAGEUTILS;

public enum Teleport {
    INSTANCE;

    public void execute(Player sender, Zone zone) {
        if (zone.getRegion() == null) {
            MESSAGEUTILS.sendLang(sender, "teleport.fail", Collections.singletonMap("%name%", zone.getName()));
            return;
        }
        PaperUtils.teleportAsync(sender, zone.getRegion().getCenter());
        MESSAGEUTILS.sendLang(sender, "teleport.success", Collections.singletonMap("%name%", zone.getName()));
    }
}
