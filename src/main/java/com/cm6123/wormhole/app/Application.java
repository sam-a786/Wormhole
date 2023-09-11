package com.cm6123.wormhole.app;

import com.cm6123.wormhole.Board.Board;
import com.cm6123.wormhole.Players.Players;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class Application {
    public static void main(final String[] args) {
        System.out.println("Welcome to Wormhole.");

        //int widthOfTheBoard =5;
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        while (true) {
            int widthOfTheBoard;
            //Enter the dimension
            while (true) {
                System.out.println("Please enter the width dimension of your board.[5-10]");
                try {
                    widthOfTheBoard = Integer.parseInt(sc.nextLine());
                    if (widthOfTheBoard >= 5 && widthOfTheBoard <= 10) {
                        break;
                    } else {
                        System.out.println("**********Width of board needs to be between 5 and 10**********");
                    }
                } catch (Exception e) {
                    System.out.println("--------------------------------------------------");
                    System.out.println("Please enter an Interger number");
                    System.out.println("Example:-");
                    System.out.println("5");
                    System.out.println("7");
                    System.out.println("--------------------------------------------------");
                    sc.nextLine();
                }
            }
            System.out.println("Thank You!!!");
            //Creation on Board
            Board b = new Board(widthOfTheBoard);
            //Generate Wormhole
            b.generateWormholes(widthOfTheBoard);
            //Generate Escape
            b.generateEscape(widthOfTheBoard);
            System.out.println("Board has " + widthOfTheBoard * widthOfTheBoard + " squares.");
            b.getWormholes();
            b.getEscapes();
            //Enter number of Players
            int nop;
            while (true) {
                try {
                    System.out.println("Please enter the number of players.[2-6]");
                    nop = Integer.parseInt(sc.nextLine());
                    if (nop >= 2 && nop <= 6) {
                        break;
                    } else {
                        System.out.println("**********Number of players needs to be between 2 and 6**********");
                    }
                } catch (Exception e) {
                    System.out.println("--------------------------------------------------");
                    System.out.println("Please enter an Integer number");
                    System.out.println("Example:-");
                    System.out.println("2");
                    System.out.println("6");
                    System.out.println("--------------------------------------------------");
                }
            }
            //Enter name of Players
            Players[] players;
            players = new Players[nop];
            for (int i = 0; i < nop; i++) {
                while (true) {
                    try {
                        System.out.println("Please enter the name of player " + (i + 1));
                        players[i] = new Players();
                        players[i].name = sc.nextLine();
                        Pattern myPattern = Pattern.compile("[^a-z]", Pattern.CASE_INSENSITIVE);
                        Matcher myMatch = myPattern.matcher(players[i].name);
                        boolean check = myMatch.find();
                        if (check) {
                            System.out.println("--------------------------------------------------");
                            System.out.println("Name should not contain any numbers or special characters or spaces");
                            System.out.println("--------------------------------------------------");
                            continue;
                        }
                        int taken = 0;
                        for (int j = 0; j < i; j++) {
                            if (players[j].name.equals(players[i].name)) {
                                System.out.println("Name is already taken");
                                taken = 1;
                            }
                        }
                        if (taken == 1) {
                            continue;
                        }
                        if (players[i].name.length() > 20) {
                            System.out.println("Player name should not exceed 20 letters");
                            continue;
                        }
                        break;
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
            for (int i = 0; i < nop; i++) {
                System.out.println(players[i].name);
            }
            String autoCorrect = new String();
            for (int i = 0; i < nop; i++) {
                //Auto and Manual Dice Roll
                while (true) {
                    System.out.println(players[i].name + "  - do you want to roll the dice, or shall I do it for you? \n Type \"Y\" to roll yourself or \"N\" to let me do it.");
                    autoCorrect = sc.nextLine();
                    if (autoCorrect.equals("y") || autoCorrect.equals("Y")) {
                        players[i].autoRoll = 0;
                        break;
                    } else if (autoCorrect.equals("n") || autoCorrect.equals("N")) {
                        players[i].autoRoll = 1;
                        break;
                    } else {
                        System.out.println("**********Please enter correct option**********");
                    }
                }
            }
            //Lets Play
            System.out.println("Let's Play");
            String again = "y";
            int breakWhile = 1;
            for (int i = 0; i < nop; i++) {
                players[i].position = 1;
            }
            breakWhile = 1;
            while (true) {
                //Dice roll
                int[] diceRoll = new int[2];
                int brk = 1;
                for (int i = 0; i < nop; i++) {
                    while (true) {
                        try {
                            System.out.println(players[i].name + " is on square " + players[i].position);
                            if (players[i].autoRoll == 1) {
                                diceRoll = b.autoRollTheDice();
                                System.out.println(diceRoll[0] + "," + diceRoll[1]);
                                players[i].position = players[i].position + diceRoll[0] + diceRoll[1];
                                System.out.println(players[i].name + " jumped to square " + players[i].position);
                            } else {
                                while (true) {
                                    System.out.println(players[i].name + " - please roll the dice");
                                    String temp = sc.nextLine();
                                    Pattern myPattern = Pattern.compile("^[0-6]{1},[0-6]{1}$", Pattern.CASE_INSENSITIVE);
                                    Matcher myMatch = myPattern.matcher(temp);
                                    boolean check = myMatch.find();
                                    if (check) {
                                        String[] tmp = temp.split(",");
                                        diceRoll[0] = Integer.parseInt(tmp[0]);
                                        diceRoll[1] = Integer.parseInt(tmp[1]);
                                        if (diceRoll[0] >= 1 && diceRoll[0] <= 6 && diceRoll[1] >= 1 && diceRoll[1] <= 6) {
                                            break;
                                        } else {
                                            System.out.println("**********Dice values can only be between 1 to 6**********");
                                        }
                                    } else {
                                        System.out.println("--------------------------------------------------");
                                        System.out.println("Please enter dice values in proper format");
                                        System.out.println("Example:-");
                                        System.out.println("2,3");
                                        System.out.println("1,6");
                                        System.out.println("4,5");
                                        System.out.println("--------------------------------------------------");
                                    }
                                }
                                System.out.println(diceRoll[0] + " " + diceRoll[1]);
                                players[i].position = players[i].position + diceRoll[0] + diceRoll[1];
                                System.out.println(players[i].name + " jumped to square " + players[i].position);
                            }
                            brk = 0;
                        } catch (Exception e) {
                        }
                        if (brk == 0) {
                            break;
                        }
                    }
                    //Check Wormhole
                    players[i].position = b.checkWormhole(players[i].position);
                    //Deciding Winner
                    if (players[i].position >= widthOfTheBoard * widthOfTheBoard) {
                        System.out.println("**********" + players[i].name + " is the Winner**********");
                        breakWhile = 0;
                        break;
                    }
                }
                if (breakWhile == 0) {
                    break;
                }
            }
            while (true) {
                System.out.println("Would you like to play the game again?");
                System.out.println("Type [Y] to play again or type [N] to stop the game.");
                again = sc.nextLine();
                if (again.contains("y") || again.contains("Y")) {
                    break;
                } else if (again.contains("n") || again.contains("N")) {
                    break;
                } else {
                    System.out.println("Please enter correct option.");
                }
            }
            if (again.contains("n") || again.contains("N")) {
                break;
            }
        }
        System.out.println("");
    }
}







