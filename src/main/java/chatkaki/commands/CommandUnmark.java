package chatkaki.commands;

import chatkaki.Ui;
import chatkaki.tasks.Task;
import chatkaki.tasks.TaskList;

public class CommandUnmark extends Command {
    private String[] inputs;

    public CommandUnmark(String[] inputs) {
        this.inputs = inputs;
    }

    @Override
    public String execute() {
        int index = Integer.parseInt(inputs[1]);
        if (index < 1 || index > TaskList.getSize()) {
            return "Invalid Task number.";
        }
        Task task = TaskList.getTask(index - 1);
        return task.markAsUndone();
    }
}