package system;

import java.util.Scanner;

/**
 * The InputProcessor class is responsible for reading user input strings from the command line
 * via the Scanner class.
 */
public class InputProcessor {

    private Scanner scanner = new Scanner(System.in);

    /**
     * Reads and returns the next line of input from the scanner.
     *
     * @return the next line of input as a String.
     */
    public String read() {
        return scanner.nextLine();
    }
}
