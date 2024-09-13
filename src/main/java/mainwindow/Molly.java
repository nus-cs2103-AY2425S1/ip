package mainwindow;

import mollyexception.MollyException;
import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * Molly bot class containing static methods that help Molly interact with user.
 */
public class Molly {

    public static String[] helpCommands = {
            "todo (description) - creates a new todo", "deadline (description) /by (end time or date) - creates a deadline",
            "event (description) /from (start time or date) /to (end time or date) - creates an event",
            "list - shows you all your tasks",
            "mark (task number) / unmark (task number) - marks or unmarks your tasks as done",
            "delete (task number) - removes a task"
    };
    private Storage storage;
    private TaskList taskList;

    private Parser mollyParser;


    public Molly(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(storage.loadTasks());
        this.mollyParser = new Parser(this.storage, this.taskList);
    }


    /**
     * Processes user inputs and manipulates the task array accordingly.
     */
    public String assistUser(String userInput) throws MollyException {
        if (userInput.toLowerCase().equals("bye")) {
            storage.saveTasks(taskList);
            return Ui.sayBye();
        }
        try {
            return mollyParser.handleUserInput(userInput);
        } catch (MollyException e) {
            return e.getMessage();
        }

    }


    public static void main(String[] args) throws MollyException {
        new Molly("./data/Molly.txt").assistUser("");
    }
}
