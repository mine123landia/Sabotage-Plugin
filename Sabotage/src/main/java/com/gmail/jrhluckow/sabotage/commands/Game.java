package com.gmail.jrhluckow.sabotage.commands;

import com.gmail.jrhluckow.sabotage.Sabotage;
import com.gmail.jrhluckow.sabotage.game.GameManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Game implements CommandExecutor {
    public Game() {
    }

    public boolean onCommand(CommandSender commandSender, Command cmd, String s, String[] strings) {
        if (commandSender instanceof Player && cmd.getName().equalsIgnoreCase("game") && strings.length == 5 && strings[0].equalsIgnoreCase("create")) {
            GameManager game = new GameManager(strings[2], strings[3], strings[4]);
            Sabotage.games.put(strings[1], game);
            ((Player)commandSender).getPlayer().sendMessage("Sucess:" + game);
        }

        return false;
    }
}
