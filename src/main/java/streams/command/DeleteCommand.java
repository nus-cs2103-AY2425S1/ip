package streams.command;

import streams.exception.StreamsException;
import streams.task.Task;
import streams.task.TaskList;
import streams.util.Storage;
import streams.util.Ui;

public class DeleteCommand extends Command {
    private int taskNumber;

    public DeleteCommand(String taskNumberStr) throws StreamsException {
        try {
            this.taskNumber = Integer.parseInt(taskNumberStr.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new StreamsException("error parsing task number");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws StreamsException {
        Task removedTask = tasks.getTask(taskNumber);
        tasks.deleteTask(taskNumber);
        ui.showMessage("okkieee..i've removed this task: " + removedTask);
        ui.showMessage("yayyayayy!!!! now you have " + tasks.size() + " tasks in the list");
        storage.save(tasks.getTasks());
    }
}