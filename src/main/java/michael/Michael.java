package michael;

import java.util.Scanner;
import java.io.IOException;

public class Michael {
    private Ui ui; // Handles user interactions
    private Storage storage; // Handles loading and saving of tasks
    private TaskList tasks; // Handles adding, retrieval and deletion of tasks
    private Parser parser; // Processes user commands
    private final String PATH = "./data/save.txt"; // File path for save file of tasks

    /**
     * Initialises the chatbot.
     *
     */
    public Michael() {
        ui = new Ui();
        storage = new Storage(PATH);
        try {
            tasks = new TaskList(storage.load());
            parser = new Parser(tasks);
        } catch (IOException e) {
            ui.showLoadingError("Can't load tasks!");
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Michael().run();
    }

    /**
     * Runs main logic of program.
     *
     */
    public void run() {
        Scanner user = new Scanner(System.in); // scanner for user input

        // Read user's input
        while (true) {
            String input = user.nextLine().strip();
            if (input.equals("bye")) { // special bye command to exit
                break;
            }

            try {
                String feedback = parser.parse(input);
                ui.feedback(feedback);
            } catch (MichaelException e) {
                ui.showLoadingError(e.getMessage());
            }
        }

        try {
            storage.save();
        } catch (IOException e) {
            ui.feedback("Couldn't save tasks!");
        } finally {
            // Exit
            ui.feedback("Bye. Hope to see you again soon!");
        }
    }
}
