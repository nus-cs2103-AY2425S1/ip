package shoai;

import java.util.Scanner;

/**
 * Entry point of the ShoAI application.
 */
public class ShoAI {

    private TaskList tasks;
    private Storage storage;
    private Parser parser;
    private Ui ui; // Add an instance of Ui
    private boolean welcomed; // Flag to check if welcome message has been shown

    public ShoAI(String filePath) {
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.loadTasks());
        } catch (ShoAIException e) {
            // Handle the exception, e.g., log it or print an error message
            System.out.println("Error loading tasks: " + e.getMessage());
            // Optionally, you can initialize tasks to an empty TaskList
            this.tasks = new TaskList();
        }
        this.parser = new Parser();
        this.ui = new Ui(); // Initialize Ui
        this.welcomed = false; // Initialize the welcomed flag
    }

    /**
     * Starts the ShoAI application.
     */
    public String start() {
        String response;
        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            if (!welcomed) {
                response = ui.showWelcome();
                welcomed = true;
                return response;
            }
            userInput = scanner.nextLine();
            try {
                response = parser.parse(userInput, tasks, storage);
                if (response != null) {
                    return response;
                }
            } catch (ShoAIException e) {
                response = e.getMessage();
                return response;
            }
        }
    }

    /**
     * Provides a response to the user input.
     *
     * @param userInput The input provided by the user.
     * @return The response string from the parser.
     */
    public String getResponse(String userInput) {
        try {
            return parser.parse(userInput, tasks, storage);
        } catch (ShoAIException e) {
            return e.getMessage();
        }
    }
}
