package chatkaki.commands;

import chatkaki.Ui;
import chatkaki.tasks.TaskList;
import chatkaki.tasks.Task;

/**
 * Represents a command to delete a task.
 */
public class CommandDelete extends Command {

    private String[] inputs;

    /**
     * Constructs a CommandDelete object with the specified inputs.
     *
     * @param inputs The inputs for the command.
     */
    public CommandDelete(String[] inputs) {
        this.inputs = inputs;
    }

    /**
     * Executes the command to delete a task.
     */
    @Override
    public void execute() {
        if (inputs.length <= 1) {
            Ui.printMessage("The description of a delete cannot be empty, add an index");
            return;
        }

        int index = Integer.parseInt(inputs[1]) - 1;
        if (index < 0 || index >= TaskList.getSize()) {
            Ui.printMessage("Index is out of range, there are only " + TaskList.getSize() + " task(s)");
            return;
        }

        Task task = TaskList.deleteTask(index);
        Ui.printMessage("Noted. I've removed this task:\n   " + task + "\nNow you have " + TaskList.getSize()
                + " task" + (TaskList.getSize() == 1 ? "" : "s") + " in the list.");
    }

}
