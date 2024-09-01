package orangecat;

import parser.Parser;
import storage.Storage;
import tasks.Task;

import java.util.ArrayList;
import java.util.List;

enum CommandType {
    LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, BYE;
}

public class TheOrangeCat {

    public static void run() {
        List<Task> items = new ArrayList<>();
        Storage.loadTasks(items); // Load tasks from file at startup
        Parser.ratchetCatBot(items); // Pass the list of tasks to the chatbot
        Storage.saveTasks(items); // Save tasks to file before exiting
    }
}
