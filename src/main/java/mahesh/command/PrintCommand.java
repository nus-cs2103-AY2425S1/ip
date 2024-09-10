package mahesh.command;

import mahesh.util.TaskList;

/**
 * Represents a command to print the tasks in the TaskList.
 */
public class PrintCommand extends Command {
    private final TaskList list;

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
     *
     * @return a String response with the tasks in the TaskList
     */
    @Override
    public String execute() {
        return list.printList();
    }
}
