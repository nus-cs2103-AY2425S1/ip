package R2D2;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Main class that contains the main program for the chatbot. It takes in input from the
 * user and interprets it based on different commands.
 */
public class R2D2 {
    private static final String FILE_PATH = "./data/R2D2.txt";

    public static void main(String[] args) {

        // Opening dialogue for the bot
        String hline = "____________________________________________________________";
        System.out.println(hline);
        System.out.println("Hello! I'm R2D2! *Beep* *Boop*");
        System.out.println("What can I do for you?");
        System.out.println(hline);

        // Reading input from user
        Scanner reader = new Scanner(System.in);

        // Initialize a new database and load tasks
        Storage storage = new Storage(FILE_PATH);
        ArrayList<Task> database = storage.loadTasks();
        InputHandler c3po = new InputHandler(database, storage);

        // Main interaction
        while (true) {
            String input = reader.nextLine();
            try {
                if (input.equals("bye")) {
                    break;
                } else {
                    c3po.overallHandler(input);
                }
            } catch (BuzzException e) {
                System.out.println(hline);
                System.out.println(" " + e.getMessage());
                System.out.println(hline);
            }
        }

        // Standard exit when bye is typed
        System.out.println(hline);
        System.out.println("Bye. Hope to see you again soon! *bzzzt*");
        System.out.println(hline);
    }
}
