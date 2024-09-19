package nen.commands;

import nen.utils.TaskList;

/**
 * This class represents Command with multiple inner classes which
 * represent different type of command
 * @author Gan Ren Yick (A0276246X)
 */
public abstract class Command {
    protected String name;
    protected String description = "";
    protected int arg;

    /**
     * Factory method of creating a command
     * @param name of the command
     * @param arg of the command
     * @return type of command
     */
    public static Command of(String name, String arg) {
        switch(name) {
        case "bye":
            return new EndCommand(name);
        case "mark":
            return new MarkCommand(name, arg);
        case "unmark":
            return new UnmarkCommand(name, arg);
        case "delete":
            return new DeleteCommand(name, arg);
        case "list":
            return new ListCommand(name);
        case "find":
            return new FindCommand(name, arg);
        case "sort":
            return new SortCommand(name, arg);
        default:
            return new AddTaskCommand(name);
        }
    }

    /**
     * @return name of the command
     */
    public String getName() {
        return name;
    }

    /**
     * @return description of the command
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return if the command is end command
     */
    public boolean isEnd() {
        return false;
    }
    public abstract void execute(TaskList tasks);
}
