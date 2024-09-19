package orangecat;

import java.util.ArrayList;
import java.util.List;

import parser.Parser;
import storage.Storage;
import tasks.Task;

/**
 * TheOrangeCat is the main class for the Orange Cat chatbot.
 * It is the entry point for the JavaFX application.
 */
public class TheOrangeCat {

    /**
     * Runs the Orange Cat chatbot.
     */
    public static void run() {
        List<Task> items = new ArrayList<>();
        Storage.loadTasks(items); // Load tasks from file at startup
        Parser.ratchetCatBot(items); // Pass the list of tasks to the chatbot
        Storage.saveTasks(items); // Save tasks to file before exiting
    }
}
