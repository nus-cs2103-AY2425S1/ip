package yihuibot.ui;

import yihuibot.executable.Executable;
import yihuibot.executable.Greet;

import yihuibot.exception.parse.ParseException;

/**
 * Deals with interactions with the user. Can be used to parse the user's
 * input using a Parser, return an Executable, or thowing ParseException
 * for invalid user inputs. Also used to properly format print statements
 * of YihuiBot.
 *
 * @author Toh Yi Hui A0259080A
 */
public class Ui {
    private static final String HORIZONTAL_LINE
            = "--------------------------------------------------------------------------------";

    private Parser parser;

    /**
     * Constructor for a new Ui. Takes in a String pattern for formatting
     * date time Strings.
     *
     * @param dateTimeFormat the format pattern of date time Strings.
     * @throws IllegalArgumentException if the pattern is not valid.
     */
    public Ui(String dateTimeFormat) throws IllegalArgumentException {
        parser = new Parser(dateTimeFormat);
    }

    /**
     * Parse the user's input using Parser.
     *
     * @param input the user's input.
     * @return an Executable.
     * @throws ParseException when the user's input is invalid.
     */
    public Executable parse(String input) throws ParseException {
        return parser.parse(input);
    }

    /**
     * Prints a greeting message.
     *
     * @param name the name of the bot.
     */
    public void greet(String name) {
        Greet greet = new Greet(name);
        greet.execute();
        prettyPrint(greet.getOutput());
    }

    /**
     * Print the Strings wrapped in horizontal lines.
     *
     * @param strings the array of Strings to print out.
     */
    public void prettyPrint(String... strings) {
        if (strings == null || strings.length == 0) {
            return;
        }

        System.out.println(HORIZONTAL_LINE);
        for (String s : strings) {
            System.out.println(s);
        }
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Print the warnings in a nice format.
     *
     * @param strings the array of Strings to print out.
     */
    public void warningPrint(String... strings) {
        if (strings == null || strings.length == 0) {
            return;
        }

        System.out.println("warning: " + strings[0]);
        String space = "         ";
        for (int i = 1; i < strings.length; i++) {
            System.out.println(space + strings[i]);
        }
    }

    /**
     * Print the errors in a nice format.
     *
     * @param strings the array of Strings to print out.
     */
    public void errorPrint(String... strings) {
        if (strings == null || strings.length == 0) {
            return;
        }

        System.out.println("Error: " + strings[0]);
        String space = "       ";
        for (int i = 1; i < strings.length; i++) {
            System.out.println(space + strings[i]);
        }
    }
}
