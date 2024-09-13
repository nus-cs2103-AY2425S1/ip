package commands;

import storage.Storage;
import storage.TaskList;
import ui.UI;

/**
 * Represents a command that is invoked when the user inputs an invalid or incomplete command.
 * The InvalidCommand class provides feedback to the user on how to correct their input.
 */
public class InvalidCommand implements Command {
    private final String desc;

    /**
     * Constructs an InvalidCommand with the specified invalid or incomplete input.
     *
     * @param desc the invalid or incomplete input provided by the user
     */
    public InvalidCommand(String desc) {
        this.desc = desc;
    }

    /**
     * Executes the invalid command, providing feedback to the user on how to correct their input.
     * This method handles various cases such as missing arguments and suggests correct usage.
     *
     * @param storage the Storage object for handling task persistence
     * @param master the TaskList object containing the list of tasks
     * @return false, indicating that the application should not terminate
     */
    @Override
    public boolean execute(Storage storage, TaskList master) {
        switch(desc) {
        case "mark":
        case "unmark":
            System.out.println("Friday > Input the task number to mark/unmark the task");
            break;
        case "add":
            System.out.println("Friday > Try doing add <task name>");
            break;
        case "remove":
            System.out.println("Friday > Input the task number (1 - " + master.getSize() + ") to remove the task");
            break;
        case "find":
            System.out.println("Friday > Try doing find <keyword>");
            break;
        default:
            System.out.println("Friday > Hmm...you can't do that. Try add/remove <task> or \"help\" for more options");
            break;
        }
        UI.printLine();
        return false;
    }
}
