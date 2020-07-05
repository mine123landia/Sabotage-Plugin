package com.gmail.jrhluckow.sabotage.commands;

import com.gmail.jrhluckow.sabotage.Sabotage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class List implements CommandExecutor {
    public List() {
    }

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Sabotage.games.forEach((x, y) -> {
                ((Player)commandSender).getPlayer().sendMessage(x);
            });
        }

        return false;
    }
}

