package nixy.parse;

import nixy.Command;
import nixy.exceptions.NixyException;
import nixy.task.Task;

/**
 * Parsed class is a class that holds the parsed user input.
 * Input contains a command and task number or task.
 */
public class Parsed {
    private Command command;
    private String stringParam;
    private int taskNumber;
    private Task task;

    public Parsed(Command c) {
        command = c;
    }

    /**
     * Creates a Parsed object with a command and task number.
     * @param c The command from the user input.
     * @param n The task number from the user input.
     */
    public Parsed(Command c, int n) {
        command = c;
        taskNumber = n;
    }

    /**
     * Creates a Parsed object with a command and task.
     * @param c The command from the user input.
     * @param t The task from the user input.
     */
    public Parsed(Command c, Task t) {
        command = c;
        task = t;
    }

    /**
     * Constructor for Parsed object with a command and a String parameter.
     * @param c The command from the user input.
     * @param s The String parameter from the user input.
     */
    public Parsed(Command c, String s) {
        command = c;
        stringParam = s;
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

    /**
     * Returns the String parameter from the user input.
     * Throws if parameter does not exist for the command.
     * @return The String parameter from the user input.
     */
    public String getStringParam() {
        if (command != Command.FIND) {
            throw new NixyException("BLAHH!!! The String parameter does not exist for this command.");
        }
        return stringParam;
    }
}
