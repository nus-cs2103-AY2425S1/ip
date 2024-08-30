package nixy.parse;

import nixy.exceptions.NixyException;
import nixy.task.Task;
import nixy.Command;

public class Parsed {
    private Command command;
    private int taskNumber;
    private Task task;

    public Parsed(Command c) {
        command = c;
    }

    public Parsed(Command c, int n) {
        command = c;
        taskNumber = n;
    }

    public Parsed(Command c, Task t) {
        command = c;
        task = t;
    }

    /**
     * Returns the command from the user input.
     * @return The command from the user input.
     */
    public Command getCommand() {
        return command;
    }

    /**
     * Returns the task number from the user input.
     * Throws if task number does not exist for the command.
     * @return The task number from the user input.
     */
    public int getTaskNumber() {
        if (command != Command.MARK && command != Command.UNMARK && command != Command.DELETE) {
            throw new NixyException("BLAHH!!! The task number does not exist for this command.");
        }
        return taskNumber;
    }

    /**
     * Returns the task from the user input.
     * Throws if task does not exist for the command.
     * @return The task from the user input.
     */
    public Task getTask() {
        if (command != Command.TODO && command != Command.DEADLINE && command != Command.EVENT) {
            throw new NixyException("BLAHH!!! The task does not exist for this command.");
        }
        return task;
    }
}
