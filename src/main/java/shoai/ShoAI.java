package shoai;

import java.util.Scanner;

/**
 * Entry point of the ShoAI application.
 */
public class ShoAI {

    private TaskList tasks;
    private Storage storage;
    private Parser parser;

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
    }

    /**
     * Provides a response to the user input.
     *
     * @param userInput The input provided by the user.
     * @return The response string from the parser.
     * Definitely must use
     */
    public String getResponse(String userInput) {
        try {
            return parser.parse(userInput, tasks, storage);
        } catch (ShoAIException e) {
            return e.getMessage();
        }
    }
}
