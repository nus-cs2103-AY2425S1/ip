package rizzler.command;

import rizzler.Storage;
import rizzler.task.Deadline;
import rizzler.task.TaskLog;

public class DeadlineCommand extends Command {
    private final Deadline deadline;

    public DeadlineCommand(String deadlineDesc, String deadlineTime) {
        super();
        this.deadline = new Deadline(deadlineDesc, deadlineTime);
    }

    @Override
    public String[] execute(Storage storage, TaskLog taskLog) {
        taskLog.addTask(deadline);
        storage.storeTasks(taskLog);
        return new String[] {"certainly, i'll keep track of this deadline for you ;)",
                "\t" + deadline,
                "now we have " + taskLog.getNumTasks() + " tasks to work on."};
    }
}
