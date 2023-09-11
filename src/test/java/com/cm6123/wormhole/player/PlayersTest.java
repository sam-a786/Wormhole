package com.cm6123.wormhole.player;

import com.cm6123.wormhole.Players.Players;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PlayersTest {
    @Test
    public void checkingIfTheNameIsReturned() {
        com.cm6123.wormhole.Players.Players player = new Players();
        assertNotNull(player.getPlayerName());
        assertEquals("", player.getPlayerName());
        player.name = "TestPlayer";
        assertNotNull(player.getPlayerName());
        assertEquals("TestPlayer", player.getPlayerName());

    }

}