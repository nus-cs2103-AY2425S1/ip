package joe.command;

import joe.Commands;
import joe.JoeException;
import joe.Storage;
import joe.Ui;
import joe.task.TaskList;

/**
 * This class is used to mark and unmark task statuses.
 */
public class ToggleCommand extends Command {
    private String[] inputArr;
    private Commands command;

    /**
     * Constructs a {@code ToggleCommand} with the specified command and input array.
     *
     * @param command the command to be toggled
     * @param inputArr the array of input strings associated with the command
     */
    public ToggleCommand(Commands command, String[] inputArr) {
        this.inputArr = inputArr;
        this.command = command;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JoeException {
        int idx = Integer.parseInt(this.inputArr[1]); // gets the task index to mark or unmark
        if (idx > taskList.getSize() || idx < 1) { // check that task index is valid
            throw new JoeException("Please input a valid task index");
        }
        if (command.equals(Commands.MARK)) {
            taskList.markTask(idx);
        } else {
            taskList.unmarkTask(idx);
        }
    }
}
