package com.gmail.jrhluckow.sabotage;

import com.gmail.jrhluckow.sabotage.commands.Game;
import com.gmail.jrhluckow.sabotage.commands.Join;
import com.gmail.jrhluckow.sabotage.commands.List;
import com.gmail.jrhluckow.sabotage.commands.Quit;
import com.gmail.jrhluckow.sabotage.game.GameManager;
import com.gmail.jrhluckow.sabotage.listener.OnJoinEvent;
import com.gmail.jrhluckow.sabotage.playerData.PlayerListenerManager;
import com.gmail.jrhluckow.sabotage.playerData.PlayerManager;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Sabotage extends JavaPlugin {
    public static HashMap<String, GameManager> games = new HashMap();
    public static HashMap<UUID, PlayerManager> playerManagers = new HashMap();
    ConsoleCommandSender console = Bukkit.getConsoleSender();
    public static PlayerListenerManager playerListenerManager;

    public Sabotage() {
    }

    public void onEnable() {
        playerListenerManager = new PlayerListenerManager();
        this.console.sendMessage("Listener iniciado");
        this.console.sendMessage("GameManager iniciado");
        ((PluginCommand)Objects.requireNonNull(this.getCommand("join"))).setExecutor(new Join());
        this.getCommand("quit").setExecutor(new Quit());
        this.getCommand("game").setExecutor(new Game());
        this.getCommand("list").setExecutor(new List());
        this.getServer().getPluginManager().registerEvents(new OnJoinEvent(), this);
    }

    public void onDisable() {
    }
}