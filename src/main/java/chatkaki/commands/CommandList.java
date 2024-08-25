package chatkaki.commands;

import chatkaki.tasks.TaskList;
import chatkaki.Ui;

/**
 * Represents a command to list all tasks.
 */
public class CommandList extends Command {
    private String[] inputs;

    /**
     * Constructs a CommandList object with the specified inputs.
     *
     * @param inputs The inputs for the command.
     */
    public CommandList(String[] inputs) {
        this.inputs = inputs;
    }

    /**
     * Executes the command to list all tasks.
     */
    @Override
    public void execute() {
        StringBuilder listMessage = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < TaskList.getSize(); i++) {
            listMessage.append("\n ").append(i + 1).append(". ").append(TaskList.getTask(i));
        }
        Ui.printMessage(listMessage.toString());
    }
}
