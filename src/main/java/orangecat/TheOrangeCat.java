package orangecat;

import java.util.ArrayList;
import java.util.List;

import parser.Parser;
import storage.Storage;
import tasks.Task;

/**
 * To run the overarching program
 */
public class TheOrangeCat {

    /**
     * Run method that triggers the entire program
     */
    public static void run() {
        List<Task> items = new ArrayList<>();
        Storage.loadTasks(items); // Load tasks from file at startup
        Parser.ratchetCatBot(items); // Pass the list of tasks to the chatbot
        Storage.saveTasks(items); // Save tasks to file before exiting
    }
}
