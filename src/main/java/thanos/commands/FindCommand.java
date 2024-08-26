package thanos.commands;

import java.util.ArrayList;

import thanos.exceptions.InvalidCommandException;
import thanos.tasks.Task;
import thanos.tasks.TaskList;
import thanos.ui.Ui;

public class FindCommand extends Command {
    public FindCommand(String argument) {
        super(argument);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidCommandException {
        if (this.getArgument().isEmpty()) {
            throw new InvalidCommandException(
                    "No keyword provided. Please use the correct format: 'find [keyword]'"
            );
        }

        ArrayList<Task> result = taskList.find(this.getArgument());
        ui.displayTasks(result, "Here are the matching tasks in your list:");
    }
}
