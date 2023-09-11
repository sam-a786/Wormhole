package com.cm6123.wormhole.Board;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class BoardChecks {
    @ParameterizedTest
    @ValueSource(ints = {5, 6, 7, 8, 9, 10})
    public void testingBoardSizes(Integer width) throws Exception {
        com.cm6123.wormhole.Board.Board a = new com.cm6123.wormhole.Board.Board(width);
        assertNotNull(a.getBoardLayout());
        assertEquals((width * width), a.getBoardLayout().size());
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 6, 7, 8, 10, 12, 15})
    public void testingTheDice(Integer width) throws Exception {
        com.cm6123.wormhole.Board.Board board = new com.cm6123.wormhole.Board.Board(width);
        int[] res = board.autoRollTheDice();
        assertNotNull(res);
        assertEquals(2, res.length);
        assertTrue(res[0] > 0 && res[0] < 6);
        assertTrue(res[1] > 0 && res[1] < 6);

    }

    @ParameterizedTest
    @ValueSource(ints = {5, 6, 7, 8, 9, 10})
    public void checkingIfThereAreEnoughWormHoles(int width) throws Exception {
        com.cm6123.wormhole.Board.Board a = new com.cm6123.wormhole.Board.Board(width);
        a.generateWormholes(width);
        int[] wormhole = a.getWormhole();
        assertNotNull(wormhole);
        assertEquals(width, wormhole.length);
        int count = 0;
        for (int i = 0; i < wormhole.length; i++) {
            if (wormhole[i] > 0)
                count++;
        }
        assertTrue(count > 0);
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 6, 7, 8, 9, 10})
    public void checkingIfThereAreEnoughEscapes(int width) throws Exception {
        com.cm6123.wormhole.Board.Board a = new com.cm6123.wormhole.Board.Board(width);
        a.generateEscape(width);
        int[] escape = a.getEscape();
        assertNotNull(escape);
        assertEquals(width, escape.length);
        int count = 0;
        for (int i = 0; i < escape.length; i++) {
            if (escape[i] > 0)
                count++;
        }
        assertTrue(count > 0);
    }

}