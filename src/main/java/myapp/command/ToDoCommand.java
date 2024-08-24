package myapp.command;

import myapp.core.BingBongUi;
import myapp.core.Storage;
import myapp.task.Task;
import myapp.task.TaskList;
import myapp.task.ToDo;

public class ToDoCommand extends AddCommand {

    public ToDoCommand(String s) {
        super(s);
    }

    @Override
    public void execute(TaskList tasks, BingBongUi ui, Storage storage) {
        Task task = new ToDo(description);
        tasks.add(task);
        saveTasks(tasks, ui, storage);
        printAddMessage(ui, tasks, task);
    }
}
