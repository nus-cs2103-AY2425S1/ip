package skibidi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/** API for generic application messages. */
public class Ui {
    /** Return string for welcome message of the app. */
    public static String getWelcomeMessage() {
        InputStream inputStream = Ui.class.getResourceAsStream("/skibidi-ascii.txt");
        if (inputStream == null) {
            return "Resource file 'skibidi-ascii.txt' not found.";
        }

        StringBuilder message = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                message.append(line);
                message.append("\n");
            }
        } catch (IOException err) {
            System.out.println(err.toString());
        }
        return message.toString();
    }

    /** Return string for exit message of the app. */
    public static String getExitMessage() {
        return "EXITING APPLICATION";
    }

    /** Print separator for demarcating separate command history. */
    public void printSeparator() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    /** Print exit message. */
    public void printExitMessage() {
        System.out.println();
        printSeparator();
        System.out.println(getExitMessage());
        printSeparator();
    }
}
