package bob;

import java.util.Scanner;

/**
 * The Bob class is the main class for the Bob chatbot application.
 * It initializes the chatbot's task list, handles user inputs, and interacts
 * with the storage and UI components to process tasks and display responses.
 */
public class Bob {

    /** UI component for displaying messages and handling user interactions */
    private static Ui ui = new Ui();

    /** UI component for displaying messages and handling user interactions */
    private static Storage storage;

    /** UI component for displaying messages and handling user interactions */
    private static TaskList taskList;

    /** UI component for displaying messages and handling user interactions */
    private static final String NAME = "Bob";

    /**
     * The main entry point of the Bob application.
     * Initializes the storage, task list, and UI components, and processes user input.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        storage = new Storage("./data/bob.txt");
        taskList = new TaskList();
        ui.showWelcomeMessage(NAME);

        // Load tasks from storage
        try {
            taskList.setTasks(storage.load());
        } catch (ChatBotException e) {
            ui.showError(e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        String phrase = scanner.nextLine();

        // Main loop to handle user input
        while (!phrase.equals("bye")) {
            try {
                ui.showLine();
                Parser.handleInput(phrase, taskList, ui);
                storage.save(taskList.getTasks());
            } catch (ChatBotException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
                phrase = scanner.nextLine();
            }
        }

        // Show goodbye message when the user exits the program
        ui.showGoodbyeMessage();
    }
}
