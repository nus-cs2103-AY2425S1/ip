package bottle.command;

import java.time.LocalDateTime;

import bottle.Storage;
import bottle.Ui;
import bottle.task.Deadline;
import bottle.task.TaskList;

public class addDeadlineTask extends Command {
    private final String description;
    private final LocalDateTime by;

    public addDeadlineTask(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(new Deadline(description, by));
        ui.printTaskAddedMessage(taskList.getTaskList());
        storage.saveTasks(taskList.getTaskList());
    }
}
