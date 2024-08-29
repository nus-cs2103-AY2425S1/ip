package justbot.command;

import java.time.LocalDateTime;

import justbot.storage.Storage;
import justbot.task.Deadline;
import justbot.task.Task;
import justbot.task.TaskList;
import justbot.ui.Ui;

public class DeadlineCommand extends Command {
    private Deadline deadlineTask;

    public DeadlineCommand(String description, LocalDateTime by) {
        this.deadlineTask = new Deadline(description, by);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(this.deadlineTask);
        ui.addTaskMessage(taskList, deadlineTask);
        storage.saveTasks(taskList);
    }

    @Override
    public Task getTask() {
        return this.deadlineTask;
    }
}
