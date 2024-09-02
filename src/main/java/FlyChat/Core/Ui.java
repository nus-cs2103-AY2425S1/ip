package FlyChat.Core;

import java.io.IOException;
import java.util.Scanner;

/**
 * Contains methods dealing with user interaction, such as scanning for user input and
 * managing output to System.out.
 */
public class Ui {
    private static final String lineBreak = "\n--------------------\n";
    private Scanner userInput = new Scanner(System.in);


    /**
     * Outputs the application start-up text to the user.
     */
    public void greetUser() {
        System.out.println(lineBreak);
        System.out.println("Hello! I'm FlyChat\nWhat can I do for you?");
        System.out.println(lineBreak);
    }

    /**
     * Outputs the application closing text to the user.
     */
    public void bye() {
        System.out.println(lineBreak);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(lineBreak);
        userInput.close();
    }

    /**
     * Scans for user input and returns the scanned input to the system.
     * @return Scanned user input.
     * @throws IOException If no input is detected.
     */
    public String getNextLine() throws IOException {
        if (userInput.hasNextLine()) {
            String inputString = userInput.nextLine();
            return inputString;
        } else {
            throw new IOException("Scanner is unable to scan any input");
        }
    }

    /**
     * Formats and announces actions taken by the application to the user.
     * @param str String to be announced.
     */
    public void announceString(String str) {
        System.out.println(lineBreak);
        System.out.println(str);
        System.out.println(lineBreak);
    }
}
