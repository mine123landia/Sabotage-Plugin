package com.gmail.jrhluckow.sabotage.commands;

import com.gmail.jrhluckow.sabotage.Sabotage;
import com.gmail.jrhluckow.sabotage.playerData.PlayerManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Quit implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            if (Sabotage.playerManagers.containsKey(((Player) commandSender).getUniqueId())) {
                PlayerManager playerManager = Sabotage.playerManagers.get(((Player) commandSender).getUniqueId());
                Sabotage.games.get(playerManager.getGameName()).removePlayer(playerManager);
                playerManager.setGameName(null);
            }
        }
        return false;
    }
}
