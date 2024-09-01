package yihuibot.ui;

import yihuibot.exception.parse.ParseException;
import yihuibot.executable.Executable;

/**
 * Deals with interactions with the user. Can be used to parse the user's
 * input using a Parser, return an Executable, or thowing ParseException
 * for invalid user inputs. Also used to properly format print statements
 * of YihuiBot.
 *
 * @author Toh Yi Hui A0259080A
 */
public class Ui {
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
     * Return the array of Strings in a nice format.
     *
     * @param strings the array of Strings to print out.
     * @return the nicely formatted String.
     */
    public String prettyString(String... strings) {
        if (strings == null || strings.length == 0) {
            return "";
        }

        String result = strings[0];
        for (int i = 1; i < strings.length; i++) {
            result += "\n" + strings[i];
        }
        return result;
    }
}
