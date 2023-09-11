package com.cm6123.wormhole.Players;

public class Players {

    /**
     * Create a properties for the class.
     */
    private int numberOfPlayers;
    /**
     * Create a properties for the class.
     */
    public String name;
    /**
     * Create a properties for the class.
     */
    public int autoRoll;
    /**
     * Create a properties for the class.
     */
    public int position;

    /**
     * Create a method which gets called into main method.
     */
    public Players() {
        name = "";
        position = 1;
        autoRoll = 0;
    }

    /**
     * Creating a method that will get the players name.
     *
     * @return players name so the user can see the players name.
     */
    public String getPlayerName() {
        return this.name;
    }
}
