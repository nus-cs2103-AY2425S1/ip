package noisy;




/**
 * The main entry point for the Noisy task management application.
 * This class handles the user input loop, processes commands, and interacts with the
 * {@code Parser}, {@code Storage}, {@code TaskList}, and {@code Ui} classes to manage tasks.
 */

public class Noisy {

    Storage storage;
    TaskList taskList;

    public Noisy(TaskList taskList, Storage storage) {
        this.storage = storage;
        this.taskList = taskList;
    }



    public String getResponse(String input) {
        Parser parser = new Parser();
        Ui ui = new Ui();
        return parser.parseInput(input, taskList, storage, ui);
    }
}

