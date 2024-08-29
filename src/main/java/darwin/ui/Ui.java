package darwin.ui;

import java.util.Scanner;

/**
 * Ui class to handle user interface.
 */
public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    private static void reply(String txt) {
        String line = "-".repeat(50);
        System.out.println(line);
        System.out.println(txt);
        System.out.println(line);
    }

    /**
     * Sends a message to the user.
     * @param txt message to send
     */
    public void send(String txt) {
        Ui.reply(txt);
    }

    /**
     * Reads a line of input from the user.
     * @return line of input from the user
     */
    public String read() {
        return this.scanner.nextLine();
    }

}
