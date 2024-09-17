package bing.command;

import bing.task.ToDo;

/**
 * Represents a command to add a ToDo task.
 */
public class ToDoCommand extends AddCommand {

    /**
     * Constructs a ToDoCommand with the specified task information.
     *
     * @param info The description of the ToDo task.
     */
    public ToDoCommand(String info) {
        super(new ToDo(info));
    }
}
