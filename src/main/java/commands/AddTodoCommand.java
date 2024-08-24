package commands;

import common.Command;
import common.SkibidiException;
import common.Ui;
import storage.TaskStorage;
import storage.Todo;

import java.io.IOException;

public class AddTodoCommand extends Command {
    private String description;

    public AddTodoCommand(String input) throws SkibidiException {
        this.description = input.substring(5).trim();
        if (description.isEmpty()) {
            throw new SkibidiException("OOPS!!! The description of a todo cannot be empty.");
        }
    }

    @Override
    public boolean execute(Ui ui, TaskStorage storage) {
        try {
            Todo todo = new Todo(description);
            storage.addTask(todo);
            ui.printMessage("Got it. I've added this task:\n  " + todo);
        } catch (SkibidiException | IOException e) {
            ui.printMessage(e.getMessage());
        }
        return true;
    }
}
