package myapp.command;

import myapp.core.BingBongUI;
import myapp.core.Storage;
import myapp.task.Deadline;
import myapp.task.Task;
import myapp.task.TaskList;


import java.time.LocalDateTime;

public class DeadlineCommand extends AddCommand {
    LocalDateTime by;

    public DeadlineCommand(String s, LocalDateTime dt) {
        super(s);
        this.by = dt;
    }

    @Override
    public void execute(TaskList tasks, BingBongUI ui, Storage storage) {
        Task task = new Deadline(description, by);
        tasks.add(task);
        saveTasks(tasks, ui, storage);
        printAddMessage(ui, tasks, task);
    }
}
