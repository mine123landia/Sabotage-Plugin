package com.gmail.jrhluckow.sabotage.playerData;

import com.gmail.jrhluckow.sabotage.game.Teams;
import java.util.UUID;

public class PlayerManager {
    private UUID uuid;
    private String gameName;
    private boolean inGame;
    private boolean isDead;
    private Teams team;

    public PlayerManager(UUID uuid, boolean inGame, boolean isDead) {
        this.uuid = uuid;
        this.inGame = inGame;
        this.isDead = isDead;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public String getGameName() {
        return this.gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public boolean isInGame() {
        return this.inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public Teams getTeam() {
        return this.team;
    }

    public void setTeam(Teams team) {
        this.team = team;
    }

    public boolean isDead() {
        return this.isDead;
    }

    public void setDead(boolean dead) {
        this.isDead = dead;
    }
}

