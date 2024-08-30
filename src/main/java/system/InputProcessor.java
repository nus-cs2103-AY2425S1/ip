package system;

import java.util.Scanner;

public class InputProcessor {

    Scanner scanner = new Scanner(System.in);

    /**
     * Reads and returns the next line of input from the scanner.
     *
     * @return the next line of input as a String.
     */
    public String read(){
        return scanner.nextLine();
    }
}
