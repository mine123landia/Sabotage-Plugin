package com.gmail.jrhluckow.sabotage.game;

import com.gmail.jrhluckow.sabotage.Sabotage;
import com.gmail.jrhluckow.sabotage.playerData.PlayerListener;
import com.gmail.jrhluckow.sabotage.playerData.PlayerManager;
import java.util.ArrayList;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Note;
import org.bukkit.Note.Tone;
import org.bukkit.scheduler.BukkitRunnable;

public class GameManager implements PlayerListener {
    public Sabotage plugin = (Sabotage)Sabotage.getPlugin(Sabotage.class);
    private int lobbyCountdown;
    private int playersOnline;
    private int playersNeeded;
    private int playersMax;
    private boolean isStarted;
    private boolean isLobbyCooldownRunning;
    private ArrayList<PlayerManager> players = new ArrayList();
    Location lobbySpawn;
    Location gameSpawn;

    public GameManager(String lobbyCooldown, String playersNeeded, String playersMax) {
        this.lobbyCountdown = Integer.parseInt(lobbyCooldown);
        this.playersNeeded = Integer.parseInt(playersNeeded);
        this.playersMax = Integer.parseInt(playersMax);
        Sabotage.playerListenerManager.addListener(this);
    }

    public GameManager(int lobbyCooldown, int playersNeeded, int playersMax) {
        this.lobbyCountdown = lobbyCooldown;
        this.playersNeeded = playersNeeded;
        this.playersMax = playersMax;
        Sabotage.playerListenerManager.addListener(this);
    }

    public int getPlayersOnline() {
        return this.playersOnline;
    }

    public void addPlayer(PlayerManager playerManager) {
        this.players.add(playerManager);
        ++this.playersOnline;
    }

    public void removePlayer(PlayerManager playerManager) {
        this.players.remove(playerManager);
        --this.playersOnline;
    }

    public void setPlayersOnline(int playersOnline) {
        this.playersOnline = playersOnline;
    }

    public boolean isStarted() {
        return this.isStarted;
    }

    public void setStarted(boolean started) {
        this.isStarted = started;
    }

    public boolean isLobbyCooldownRunning() {
        return this.isLobbyCooldownRunning;
    }

    public void setLobbyCooldownRunning(boolean lobbyCooldownRunning) {
        this.isLobbyCooldownRunning = lobbyCooldownRunning;
    }

    public void addPlayersOnline() {
        ++this.playersOnline;
    }

    public void SetupGame() {
    }

    public void playersNeededCheck() {
        if (this.playersOnline != this.playersMax && this.playersOnline < this.playersMax) {
            this.lobbyCooldown();
        }

    }

    public void lobbyWait() {
    }

    public void lobbyCooldown() {
        (new BukkitRunnable() {
            public void run() {
                int desconto = 0;
                boolean descontox;
                if (GameManager.this.playersNeeded == GameManager.this.playersMax) {
                    GameManager.this.gameStart();
                    GameManager.this.lobbyCountdown = desconto;
                    descontox = false;
                    this.cancel();
                } else if (GameManager.this.lobbyCountdown > 0 && GameManager.this.playersOnline >= GameManager.this.playersNeeded) {
                    GameManager.this.lobbyCountdown--;
                    int var3 = desconto + 1;
                    System.out.println(GameManager.this.lobbyCountdown);
                    GameManager.this.messageActionBar(String.valueOf(GameManager.this.lobbyCountdown), Note.natural(1, Tone.A));
                    GameManager.this.setLobbyCooldownRunning(true);
                } else if (GameManager.this.playersOnline < GameManager.this.playersNeeded) {
                    GameManager.this.lobbyCountdown = GameManager.this.lobbyCountdown + desconto;
                    GameManager.this.setLobbyCooldownRunning(false);
                    descontox = false;
                    this.cancel();
                } else {
                    GameManager.this.lobbyCountdown = desconto;
                    descontox = false;
                    GameManager.this.gameStart();
                    this.cancel();
                }

            }
        }).runTaskTimerAsynchronously(this.plugin, 0L, 20L);
    }

    public void gameStart() {
        this.setStarted(true);
    }

    public void joinListener() {
        if (!this.isLobbyCooldownRunning && !this.isStarted) {
            this.playersNeededCheck();
        }

    }

    public void messageActionBar(String message, Note note) {
        Bukkit.getOnlinePlayers().forEach((p) -> {
            p.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(String.valueOf(this.lobbyCountdown)));
        });
        Bukkit.getOnlinePlayers().forEach((p) -> {
            p.getPlayer().playNote(p.getLocation(), Instrument.PIANO, note);
        });
    }

    public void gameEnd() {
        this.setStarted(false);
    }
}
