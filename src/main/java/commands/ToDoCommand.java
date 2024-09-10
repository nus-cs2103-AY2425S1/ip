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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(new ToDo(this.description));
        ui.say("ToDo task has been added.");
        try {
            storage.createFile();
            storage.writeFile(tasks.toString());
            ui.say("File saved.");
        } catch (IOException e) {
            ui.say("File cannot be saved.");
        }
    }
}
