package Johnson.command;

import Johnson.task.ToDo;
import Johnson.utils.Utilities;

/**
 * Represents a command to add a ToDo task.
 */
public class ToDoCommand extends Command {
    /**
     * The command word that identifies a ToDoCommand instance.
     */
    public static final String COMMAND_WORD = "todo";

    /**
     * The message that is displayed when a ToDoCommand instance is executed successfully.
     */
    private static final String COMMAND_MSG = "Get tactical, Chief! Got one more for the list:\n";

    private final ToDo toDo;

    /**
     * Constructs a ToDoCommand with the specified task and tags.
     *
     * @param toDo the name of the task.
     * @param tags the tags of the task.
     */
    public ToDoCommand(String toDo, String... tags) {
        this.toDo = new ToDo(toDo, tags);
    }

    public ToDo getToDo() {
        return this.toDo;
    }

    @Override
    public String executeCommand() {
        Utilities.OutlineMessage(COMMAND_MSG + toDo);
        Command.taskList.addTask(toDo);
        return (COMMAND_MSG + toDo);
    }
}
