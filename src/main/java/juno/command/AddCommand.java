package juno.command;
import juno.manager.FileManager;
import juno.manager.TaskManager;
import juno.task.Task;

import java.util.ArrayList;


/**
 * An abstract class for commands that add tasks to the system.
 * (Specifically, for AddTodoCommand, AddDeadlineCommand, and AddEventCommand classes)
 * A subclass of abstract class Command, without the implementation of <code>runCommand()</code>
 * Provides the functionality of managing interactions with
 * TaskManager and FileManager.
 */
public abstract class AddCommand extends Command {

    TaskManager taskManager;
    FileManager fileManager;
    String userInput;
    ArrayList<Task> tasks;

    /**
     * Constructs an AddCommand with the specified user input, TaskManager, and FileManager.
     * Initialises the task list from the TaskManager.
     * This constructor is used by its subclasses and can be called with <code>super</code> keyword.
     *
     * @param userInput The input provided by the user to create the task.
     * @param taskManager The TaskManager instance to handle task specific operations
     * @param fileManager The FileManager instance to handle file operations related to tasks.
     */
    public AddCommand(String userInput, TaskManager taskManager, FileManager fileManager) {
        this.userInput = userInput;
        this.taskManager = taskManager;
        this.fileManager = fileManager;
        this.tasks = taskManager.getTasksArray();
    }
}
