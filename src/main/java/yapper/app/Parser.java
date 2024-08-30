package yapper.app;

import java.util.Scanner;

import yapper.exceptions.YapperException;

/**
 * Parses user input from the command line.
 */
public class Parser {

    /**
     * Constructs a Parser that reads from the text box.
     */
    public Parser() {
    }

    /**
     * Parses the next line of input from the user.
     * Splits the line into words based on whitespace.
     *
     * @param input  a string to be split apart to identify the command
     * @return an array of strings representing the words in the input line
     * @throws YapperException if there is no input available or if parsing fails
     */
    public String[] parseLine(String input) throws YapperException {
        if (input.isEmpty()) {
            throw new YapperException("Huh?");
        }
        return input.split("\\s+");
    }
}
