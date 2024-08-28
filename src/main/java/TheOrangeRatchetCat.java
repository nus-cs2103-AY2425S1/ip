import java.util.ArrayList;
import java.util.List;
import storage.*;
import parser.Parser;
import tasks.*;


enum CommandType {
    LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, BYE;
}

public class TheOrangeRatchetCat {

    public static void main(String[] args) {
        List<Task> items = new ArrayList<>();
        Storage.loadTasks(items); // Load tasks from file at startup
        Parser.ratchetCatBot(items); // Pass the list of tasks to the chatbot
        Storage.saveTasks(items); // Save tasks to file before exiting
    }
}
