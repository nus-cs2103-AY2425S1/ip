package yapper.resources;

import java.util.Scanner;

import yapper.exceptions.YapperException;

/**
 * Parses user input from the command line.
 */
public class Parser {
    private Scanner sc;

    /**
     * Constructs a Parser that reads from the standard input.
     */
    public Parser() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Parses the next line of input from the user.
     * Splits the line into words based on whitespace.
     *
     * @return an array of strings representing the words in the input line
     * @throws YapperException if there is no input available or if parsing fails
     */
    public String[] parseLine() throws YapperException {
        String input = "";
        if (this.sc.hasNextLine()) {
            input = this.sc.nextLine();
        } else {
            throw new YapperException("Invalid input");
        }
        return input.split("\\s+");
    }
}
