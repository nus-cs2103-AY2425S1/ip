package orion.commands;

import orion.chatbot.Storage;
import orion.chatbot.TaskList;
import orion.chatbot.Ui;

import orion.tasks.Task;

public class AddTaskCommand extends Command {

    Task task;

    public AddTaskCommand(Task task) {
        super(false);
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        tasks.addTask(task);
        ui.printAddTask(tasks, task);
    }
}
