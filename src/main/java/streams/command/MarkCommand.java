package streams.command;

import streams.exception.StreamsException;
import streams.task.Task;
import streams.task.TaskList;
import streams.util.Storage;
import streams.util.Ui;

public class MarkCommand extends Command {
    private int taskNumber;
    private boolean isDone;

    public MarkCommand(String taskNumberStr, boolean isDone) throws StreamsException {
        try {
            this.taskNumber = Integer.parseInt(taskNumberStr.trim()) - 1;
            this.isDone = isDone;
        } catch (NumberFormatException e) {
            throw new StreamsException("error parsing task number");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws StreamsException {
        Task task = tasks.getTask(taskNumber);
        if (isDone) {
            task.markAsDone();
            ui.showMessage("marked task done: " + task);
        } else {
            task.markAsNotDone();
            ui.showMessage("marked task not done: " + task);
        }
        storage.save(tasks.getTasks());
    }
}