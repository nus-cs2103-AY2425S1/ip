package chatkaki.commands;

import chatkaki.Ui;
import chatkaki.tasks.Task;
import chatkaki.tasks.TaskList;

public class CommandMark extends Command {
    private String[] inputs;

    public CommandMark(String[] inputs) {
        this.inputs = inputs;
    }

    @Override
    public void execute() {
        int index = Integer.parseInt(inputs[1]);
        if (index < 1 || index > TaskList.getSize()) {
            Ui.printMessage("Invalid Task number.");
            return;
        }
        Task task = TaskList.getTask(index - 1);
        task.markAsDone();
    }
}