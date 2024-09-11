package MeowMeow;

import java.util.Scanner;

/**
 * Represents a class that takes in input from the user and returns it
 */
public class Ui {

    private static Scanner s;
    private static String input;

    public Ui(){
        Ui.s = new Scanner(System.in);
    }

    /**
     * Returns the first input from the user.
     * Prints out the starting text from MeowMeow.
     *
     * @return First user input.
     */
    public static String start() {
        System.out.println("Hello! I'm meowmeow.meowmeow\n" + "What can I do for you?\n");
        input = s.nextLine();
        return input;

    }

    /**
     * Returns the latest user input.
     *
     * @return User input.
     */
    public static String next() {
        input = s.nextLine();
        return input;
    }
}