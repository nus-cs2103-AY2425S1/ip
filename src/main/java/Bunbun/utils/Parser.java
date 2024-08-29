package Bunbun.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This class parses user input for Bunbun.
 *
 * @author Eda Yeo
 * @version CS2103T AY24/25 Semester 1
 */
public class Parser {

    private static ArrayList<String> tokens = new ArrayList<>();

    /**
     * Reads user input.
     *
     * @return String of user input.
     */
    public static String getMessage() {
        Scanner scanner = new Scanner(System.in);
        Parser.tokens.clear();
        String msg = scanner.nextLine();
        Parser.tokens = new ArrayList<>(Arrays.asList(msg.split(" ")));
        return msg;
    }

    /**
     * Returns individual words of a message as an ArrayList.
     *
     * @return ArrayList<String> of individual words of the message string.
     */
    public static ArrayList<String> getTokens() {
        return Parser.tokens;
    }
}
