package tissue.parse;

import java.util.NoSuchElementException;
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
     * @throws NoSuchElementException If no more tokens are available.
     */
    public String retrieveNextString() throws NoSuchElementException {
        return scanner.next().strip();
    }

    /**
     * Returns the string until the next line.
     *
     * @return String until next line.
     * @throws NoSuchElementException If no more tokens are available.
     */
    public String retrieveUntilNextLine() throws NoSuchElementException {
        String str = scanner.nextLine().strip();
        if (str.isEmpty()) {
            throw new NoSuchElementException();
        }
        return str;
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
    public String scanUntil(String pattern) throws NoSuchElementException {
        String item = "";
        String temp = scanner.next();
        while (!temp.equals(pattern) && scanner.hasNext()) {
            item += temp + " ";
            temp = scanner.next();
        }
        item = item.strip();
        if (item.isEmpty()) {
            throw new NoSuchElementException();
        }
        return item;
    }
}
