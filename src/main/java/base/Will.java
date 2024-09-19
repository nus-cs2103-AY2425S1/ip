package base;

import task.Task;
import task.TaskList;
import utility.Parser;
import utility.Storage;

import java.util.ArrayList;


public class Will {

    private final ArrayList<Task> tasks;
    private final Storage storage;
    private final TaskList taskList;
    private final Parser parser;

    /**
     * Set up the task list, storage, and parser and loads any previously saved tasks from storage into the task list.
     */
    public Will(){
        this.tasks = new ArrayList<>();
        this.storage = new Storage();
        this.taskList = new TaskList();
        this.parser = new Parser();

        storage.load(tasks);
    }

    /**
     * Processes the user's input command and returns the appropriate response.
     *
     * @param input The command input by the user.
     * @return A string representing the result of the command execution.
     */
    public String getResponse(String input) {
        return this.parser.parseUICommand(tasks, input, taskList, parser, storage);
    }
}
