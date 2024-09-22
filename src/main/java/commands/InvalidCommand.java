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
        int s = master.getSize();
        String response;
        switch (description) {
        case "mark":
        case "unmark":
            response = "Friday > Input the task number to mark/unmark the task";
            break;
        case "add":
            response = "Friday > Try doing add <task name>";
            break;
        case "remove":
            if (s == 0) {
                response = "There are no tasks to remove!";
            } else {
                response = "Friday > Input the task number (1 - " + master.getSize() + ") to remove the task";
            }
            break;
        case "find":
            if (s == 0) {
                response = "There are no tasks I can search from!";
            } else {
                response = "Friday > Try doing find <keyword>";
            }
            break;
        default:
            response = "Friday > Hmm...you can't do that. Try \"help\" for more options";
            break;
        }
        return response;
    }
}
