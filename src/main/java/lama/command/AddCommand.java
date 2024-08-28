package lama.command;


import lama.LamaException;
import lama.Storage;
import lama.TaskList;
import lama.Ui;
import lama.task.Task;

public class AddCommand extends Command {

    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void run(TaskList taskList, Storage storage, Ui ui) throws LamaException {
        taskList.add(task);
        ui.showAddCommand(taskList);
        storage.addTask(task);
    }
}
