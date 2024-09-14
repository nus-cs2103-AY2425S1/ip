package commands;

import java.io.IOException;

import cook.Storage;
import cook.TaskList;
import tasks.ToDo;

/**
 * ToDoCommand class to process ToDo commands.
 */
public class ToDoCommand extends Command {
    protected String description;

    /**
     * Constructs ToDoCommand object.
     *
     * @param description Description of the ToDo Task.
     */
    public ToDoCommand(String description) {
        super("todo");
        this.description = description;
    }

    /**
     * Adds ToDo task and saves tasks to file.
     *
     * @param tasks List of Task objects.
     * @param storage Class to save and load tasks on the local drive.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        ToDo toDo = new ToDo(this.description);
        if (tasks.detectDuplicate(toDo)) {
            return "There is already another task with the same description.";
        }
        StringBuilder content = new StringBuilder();
        tasks.addTask(toDo);
        content.append("ToDo task has been added.\n");
        try {
            storage.writeFile(tasks);
            content.append("File saved.");
            return content.toString();
        } catch (IOException e) {
            content.append("File cannot be saved.");
            return content.toString();
        }
    }
}
