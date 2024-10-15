package chatkaki.commands;

import chatkaki.Ui;
import chatkaki.tasks.Task;
import chatkaki.tasks.TaskList;

/**
 * Represents a command to mark a task as done.
 */
public class CommandMark extends Command {
    private String[] inputs;

    /**
     * Constructs a CommandMark object with the specified inputs.
     *
     * @param inputs The inputs for the command.
     */
    public CommandMark(String[] inputs) {
        this.inputs = inputs;
    }

    /**
     * Executes the command to mark a task as done.
     */
    @Override
    public String execute() {
        int index = Integer.parseInt(inputs[1]);
        if (index < 1 || index > TaskList.getSize()) {
            return "Invalid Task number.";
        }
        Task task = TaskList.getTask(index - 1);
        return task.markAsDone();
    }
}