package tissue.parse;

import java.util.Scanner;

/**
 * Parser class to help scan through the text input.
 */
public class Parser {
    private Scanner scanner;

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Returns the string of the next input.
     *
     * @return Next input string.
     */
    public String retrieveNextString() {
        return scanner.next();
    }

    /**
     * Returns the next input as integer.
     *
     * @return Next input as integer.
     */
    public int retrieveNextInt() {
        return scanner.nextInt();
    }

    /** Closes the scanner. */
    public void close() {
        scanner.close();
    }

    /**
     * Returns subsection of string until the specified character.
     *
     * @param pattern Pattern to scan until.
     * @return Subsection of string.
     */
    public String scanUntil(String pattern) {
        String item = "";
        String temp = scanner.next();
        while (!temp.equals(pattern) && scanner.hasNext()) {
            item += temp + " ";
            temp = scanner.next();
        }
        return item;
    }
}
