package mahesh.command;

import mahesh.util.TaskList;

/**
 * Represents a command to print the tasks in the TaskList.
 */
public class PrintCommand extends Command {
    private TaskList list;

    /**
     * Constructs a PrintCommand with the specified TaskList.
     *
     * @param list the TaskList whose tasks will be printed
     */
    public PrintCommand(TaskList list) {
        this.list = list;
    }

    /**
     * Executes the PrintCommand by printing the tasks in the TaskList.
     */
    public void execute() {
        list.printList();
    }

    /**
     * Indicates whether the command is an exit command.
     *
     * @return false as PrintCommand is not an exit command
     */
    public boolean isExit() {
        return false;
    }
}
