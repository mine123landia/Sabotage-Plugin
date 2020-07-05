package com.gmail.jrhluckow.sabotage.listener;

import com.gmail.jrhluckow.sabotage.Sabotage;
import com.gmail.jrhluckow.sabotage.playerData.PlayerManager;
import java.util.UUID;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnJoinEvent implements Listener {
    public Sabotage plugin = (Sabotage)Sabotage.getPlugin(Sabotage.class);

    public OnJoinEvent() {
    }

    @EventHandler
    public void OnJoinEvent(PlayerJoinEvent e) {
        UUID uuid = e.getPlayer().getUniqueId();
        Sabotage var10000 = this.plugin;
        Sabotage.playerManagers.put(uuid, new PlayerManager(uuid, false, false));
        e.getPlayer().sendMessage("OK, VC ENTROU" + ((PlayerManager)Sabotage.playerManagers.get(uuid)).toString());
    }

    @EventHandler
    public void onQuitEvent(PlayerQuitEvent e) {
        e.getPlayer().performCommand("quit");
        Sabotage.playerManagers.remove(e.getPlayer().getUniqueId());
    }
}
