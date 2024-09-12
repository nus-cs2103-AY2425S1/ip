package commands;

import java.io.IOException;

import cook.Storage;
import cook.TaskList;
import cook.Ui;
import exceptions.InvalidInputException;
import tasks.ToDo;

/**
 * ToDoCommand class to process ToDo commands.
 */
public class ToDoCommand extends Command {
    protected String description;

    /**
     * Constructor for ToDoCommand class.
     */
    public ToDoCommand(String description) throws InvalidInputException {
        super("todo");
        this.description = description;
    }

    /**
     * Adds ToDo task and saves file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
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
