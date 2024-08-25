package thanos.commands;

import thanos.exceptions.InvalidCommandException;
import thanos.tasks.TaskList;
import thanos.tasks.Todo;
import thanos.ui.Ui;

public class TodoCommand extends Command {
    private final String input;

    public TodoCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidCommandException {
        if (this.input.isEmpty()) {
            throw new InvalidCommandException(
                    "No task description provided. Please use the correct format: 'todo [task]'"
            );
        }

        Todo todo = new Todo(this.input);
        taskList.add(todo);
        ui.displayTaskAdded(todo, taskList.size());
    }
}
