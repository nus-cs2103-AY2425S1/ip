package commands;

import storage.Storage;
import storage.TaskList;

/**
 * Represents a command that is invoked when the user inputs an invalid or incomplete command.
 * The InvalidCommand class provides feedback to the user on how to correct their input.
 */
public class InvalidCommand implements Command {
    private final String description;

    /**
     * Constructs an InvalidCommand with the specified invalid or incomplete input.
     *
     * @param description the invalid or incomplete input provided by the user
     */
    public InvalidCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the invalid command, providing feedback to the user on how to correct their input.
     * This method handles various cases such as missing arguments and suggests correct usage.
     *
     * @param storage the Storage object for handling task persistence
     * @param master the TaskList object containing the list of tasks
     * @return String that denotes a response that is displayed to the user
     */
    @Override
    public String execute(Storage storage, TaskList master) {
        return switch (description) {
        case "mark", "unmark" -> "Friday > Input the task number to mark/unmark the task";
        case "add" -> "Friday > Try doing add <task name>";
        case "remove" -> "Friday > Input the task number (1 - " + master.getSize() + ") to remove the task";
        case "find" -> "Friday > Try doing find <keyword>";
        default -> "Friday > Hmm...you can't do that. Try add/remove <task> or \"help\" for more options";
        };
    }
}
