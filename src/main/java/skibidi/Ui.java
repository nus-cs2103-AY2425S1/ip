package skibidi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Ui {
    public void displayWelcome() {
        InputStream inputStream = Ui.class.getResourceAsStream("/skibidi-ascii.txt");
        if (inputStream == null) {
            System.out.println("Resource file 'skibidi-ascii.txt' not found.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException err) {
            System.out.println(err.toString());
        }
    }

    public void printSeparator() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public void printExitMessage() {
        System.out.println();
        printSeparator();
        System.out.println("EXITING APPLICATION");
        printSeparator();
    }
}
