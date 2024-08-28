package commands;

import exceptions.DownyException;
import exceptions.InvalidFormatException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

public class MarkCommand implements Command {

    public final String taskNumber;
    public MarkCommand(String taskNumber) {
        this.taskNumber = taskNumber;
    }
    public void execute(Storage storage, TaskList tasks, Ui ui) throws DownyException {
        try {
            int taskNum = Integer.parseInt(this.taskNumber);
            tasks.completeTaskInList(taskNum);
            Task t = tasks.getTask(taskNum - 1);
            storage.markComplete(t);
            ui.displayCompletedTask(t);
        } catch (NumberFormatException e) {
            throw new InvalidFormatException("Task number has to be a positive integer.");
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidFormatException("Task number does not exist.");
        }
    }
    public boolean isExit() {
        return false;
    }

    public String getTaskNumber() {
        return this.taskNumber;
    }

}
