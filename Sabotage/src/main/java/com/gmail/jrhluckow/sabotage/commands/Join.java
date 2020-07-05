package com.gmail.jrhluckow.sabotage.commands;

import com.gmail.jrhluckow.sabotage.Sabotage;
import com.gmail.jrhluckow.sabotage.game.GameManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Join implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            UUID uuidPlayer = ((Player) commandSender).getUniqueId();
            if (strings[0]!=null && Sabotage.playerManagers.containsKey(uuidPlayer)) {
                if (Sabotage.games.containsKey(strings[0]) && Sabotage.playerManagers.get(uuidPlayer).getGameName()==null) {
                    //adiciona pra lista de players no jogo
                    Sabotage.games.get(strings[0]).addPlayer(Sabotage.playerManagers.get(uuidPlayer));
                    // atualiza o PlayerManager do player
                    Sabotage.playerManagers.get(uuidPlayer).setGameName(strings[0]);
                    ((Player) commandSender).getPlayer().sendMessage("Vc entrou!!!");
                    Sabotage.playerListenerManager.notifyListeners();

                }

            }
        }
        return false;
    }
}
