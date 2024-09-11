package hana.command;

import hana.HanaException;
import hana.storage.Storage;
import hana.tasklist.TaskList;
import hana.ui.Ui;

/**
 * Command to set the priority of a task.
 */
public class PriorityCommand extends Command {
    private int taskNumber;
    private int priorityLevel;

    /**
     * Constructs a new MarkCommand with input.
     *
     * @param input The input from user.
     */
    public PriorityCommand(String input) throws HanaException {
        String[] parts = input.split(" ", 3);
        if (parts.length < 3 || parts[1].trim().isEmpty()) {
            throw new HanaException("Task number and priority level cannot be empty.");
        }
        this.taskNumber = Integer.parseInt(parts[1].trim());
        this.priorityLevel = Integer.parseInt(parts[2].trim());
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws HanaException {
        taskList.setTaskPriority(taskNumber, priorityLevel);
        ui.printSetPriority(taskList.getTasks().get(taskNumber - 1));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
