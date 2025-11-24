package com.artillexstudios.axafkzone.commands.subcommands;

import com.artillexstudios.axafkzone.utils.FileUtils;
import com.artillexstudios.axafkzone.utils.NumberUtils;
import com.artillexstudios.axapi.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.Map;

import static com.artillexstudios.axafkzone.AxAFKZone.CONFIG;
import static com.artillexstudios.axafkzone.AxAFKZone.LANG;
import static com.artillexstudios.axafkzone.AxAFKZone.MESSAGEUTILS;

public enum Reload {
    INSTANCE;

    public void execute(CommandSender sender) {
        Bukkit.getConsoleSender().sendMessage(StringUtils.formatToString("&#CC0055[AxAFKZone] &#FF8855Reloading configuration..."));
        if (!CONFIG.reload()) {
            MESSAGEUTILS.sendLang(sender, "reload.failed", Map.of("%file%", "config.yml"));
            return;
        }
        Bukkit.getConsoleSender().sendMessage(StringUtils.formatToString("&#CC0055╠ &#FF8855Reloaded &fconfig.yml&#FF8855!"));

        if (!LANG.reload()) {
            MESSAGEUTILS.sendLang(sender, "reload.failed", Map.of("%file%", "lang.yml"));
            return;
        }
        Bukkit.getConsoleSender().sendMessage(StringUtils.formatToString("&#CC0055╠ &#FF8855Reloaded &flang.yml&#FF8855!"));

        NumberUtils.reload();
        FileUtils.loadAll();

        Bukkit.getConsoleSender().sendMessage(StringUtils.formatToString("&#CC0055╚ &#FF8855Successful reload!"));
        MESSAGEUTILS.sendLang(sender, "reload.success");
    }
}
