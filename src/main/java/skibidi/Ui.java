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
        assert inputStream != null : "Resource file 'skibidi-ascii.txt' not found.";

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

    /** Return string separator for demarcating separate command history. */
    public static String getSeparator() {
        return "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    }
}
