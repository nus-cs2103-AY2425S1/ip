package bobbybot.commands;

import bobbybot.*;
import bobbybot.ui.Ui;

import java.io.IOException;

public class CommandTodo extends Command{

    private final String description;

    public CommandTodo(String argument) throws BobbyBotException {
        if (argument.isEmpty()) {
            throw new BobbyBotException("The description of a todo cannot be empty.");
        }
        description = argument;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BobbyBotException {
        Task todo = new ToDo(description);
        tasks.addTask(todo);
        ui.printAddTask(tasks, todo);
        try {
            storage.saveTasksToFile(tasks.toArray());
        } catch (IOException e) {
            throw new BobbyBotException("Error saving to file.");
        }
    }
}
