package bobbybot.commands;

import bobbybot.*;
import bobbybot.ui.Ui;

import java.io.IOException;

public class CommandTodo extends Command{

    private final String description;

    public CommandTodo(String argument) throws DukeException{
        if (argument.isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        description = argument;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task todo = new ToDo(description);
        tasks.addTask(todo);
        ui.printAddTask(tasks, todo);
        try {
            storage.saveTasksToFile(tasks.toArray());
        } catch (IOException e) {
            throw new DukeException("Error saving to file.");
        }
    }
}
