package Johnson.command;

import Johnson.task.ToDo;
import Johnson.utils.Utilities;

/**
 * Represents a command to add a ToDo task.
 */
public class ToDoCommand extends Command {
    public static final String COMMAND_WORD = "todo";

    private static final String COMMAND_MSG = "Get tactical, Chief! Got one more for the list:\n";

    private final ToDo toDo;

    public ToDoCommand(String toDo) {
        this.toDo = new ToDo(toDo);
    }

    public ToDo getToDo() {
        return this.toDo;
    }

    @Override
    public String executeCommand() {
        Utilities.OutlineMessage(COMMAND_MSG + toDo);
        return (COMMAND_MSG + toDo);
    }
}
