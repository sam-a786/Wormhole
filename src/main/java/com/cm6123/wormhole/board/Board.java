package com.cm6123.wormhole.Board;

import java.util.ArrayList;
import java.util.Random;

public class Board {
    /**
     * Create a properties for the class.
     */
    private int dimentionOfBoard = 0;
    /**
     * Create a properties for the class.
     */
    private ArrayList<Integer> boardLayout = new ArrayList<Integer>();
    /**
     * Create a properties for the class.
     */
    private ArrayList<Integer> checkPosition = new ArrayList<Integer>();

    /**
     * Create an array where the wormholes will be stored.
     */
    private int[] wormhole;
    /**
     * Creating an array where the exit holes are.
     */
    private int[] escape;

    /**
     * This is where the board is created.
     *
     * @param dimentionOfBoard this will take in the value the user gives so then it can create a suitable sized board.
     */
    public Board(final int dimentionOfBoard) {
        this.dimentionOfBoard = dimentionOfBoard;
        for (int index = 0; index < (this.dimentionOfBoard * this.dimentionOfBoard); index++) {
            boardLayout.add(index + 1);
        }
    }

    /**
     * Creating the get method for the layout of the board.
     *
     * @return the layout of the board.
     */
    public ArrayList<Integer> getBoardLayout() {
        return boardLayout;
    }

    /**
     * Creating the dice, so this is what gets rolled if the user decides to have the dice rolled for them.
     *
     * @return 2 intgers which will be used to move the user to a different position.
     */
    public int[] autoRollTheDice() {
        Random rand = new Random();
        int randInt1 = rand.nextInt(6);
        int randInt2 = rand.nextInt(6);
        if (randInt1 == 0) {
            randInt1++;
        }
        if (randInt2 == 0) {
            randInt2++;
        }
        int[] diceValue = new int[2];
        diceValue[0] = randInt1;
        diceValue[1] = randInt2;
        return diceValue;
    }

    /**
     * Here the wormholes are created and then stored into the array.
     *
     * @param width takes in width then generates wormholes in the range of width squared.
     */
    public void generateWormholes(final int width) {
        Random rand = new Random();
        int temp = 0;
        wormhole = new int[width];
        for (int i = 0; i < width; i++) {
            temp = 0;

            while (temp == 0 || temp == 1 || checkPosition.contains(temp)) {
                temp = rand.nextInt(width * width);
            }
            wormhole[i] = temp;
            checkPosition.add(temp);
        }
    }

    /**
     * Here the exit holes are created and then stored into the array.
     *
     * @param width takes in width then generates wxit holes in the range of width squared.
     */
    public void generateEscape(final int width) {
        Random rand = new Random();
        int temp = 0;
        escape = new int[width];
        for (int i = 0; i < width; i++) {
            temp = 0;
            while (temp == 0 || temp == 1 || checkPosition.contains(temp)) {
                temp = rand.nextInt(width * width);
            }
            escape[i] = temp;
            checkPosition.add(temp);
        }

    }

    /**
     * This is the get method for the wormholes so the user can see the wormholes.
     */
    public void getWormholes() {
        System.out.print("Wormholes: ");
        for (int i = 0; i < dimentionOfBoard; i++) {
            System.out.print(wormhole[i] + " ");
        }
        System.out.println("");
    }

    /**
     * This is the get method for the exitholes so the user can see the exit holes.
     */
    public void getEscapes() {
        System.out.print("Escape: ");
        for (int i = 0; i < dimentionOfBoard; i++) {
            System.out.print(escape[i] + " ");
        }
        System.out.println("");
    }

    /**
     * This method checks if the user landed on a wormhole.
     *
     * @param pos Takes in the parameter position.
     * @return gives the new position of the user.
     */
    public int checkWormhole(final int pos) {
        for (int i : wormhole) {
            if (pos == i) {
                //System.out.println(name+"");
                Random rand = new Random();
                int index = rand.nextInt(wormhole.length);
                System.out.println("wormhole at " + i);
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> exit at " + escape[index]);
                return escape[index];
            }
        }
        return pos;
    }

    /**
     * A getter method for Wormhole Array.
     *
     * @return wormholes.
     */
    public int[] getWormhole() {
        return wormhole;
    }

    /**
     * A getter method for Escape Array.
     *
     * @return escapes.
     */
    public int[] getEscape() {
        return escape;
    }
}
