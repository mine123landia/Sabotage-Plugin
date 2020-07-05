package com.gmail.jrhluckow.sabotage.playerData;

import java.util.ArrayList;
import java.util.List;

public class PlayerListenerManager {
    List<PlayerListener> playerListenerList = new ArrayList();

    public PlayerListenerManager() {
    }

    public void addListener(PlayerListener listener) {
        this.playerListenerList.add(listener);
    }

    public void notifyListeners() {
        this.playerListenerList.forEach(PlayerListener::joinListener);
    }
}
