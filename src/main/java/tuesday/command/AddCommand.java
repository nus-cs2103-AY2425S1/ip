package tuesday.command;

import tuesday.task.Deadline;
import tuesday.task.Event;
import tuesday.task.Task;
import tuesday.task.ToDo;
import tuesday.util.Storage;
import tuesday.util.Ui;

/**
 * Represents a command to add a todo, deadline or event task to the task list.
 */
public class AddCommand extends Command {
    // variables
    private final String commandType;
    private final String commandPostfix;

    /**
     * Constructor for AddCommand
     *
     * @param commandType Type of command to be called
     * @param commandPostfix The postfix of the command call
     */
    public AddCommand(String commandType, String commandPostfix) {
        super(commandType);
        this.commandType = commandType;
        this.commandPostfix = commandPostfix;
    }

    /**
     * Checks the command type and invokes its corresponding methods
     *
     * @param task The Task object
     * @param ui The UI object
     * @param storage The Storage object
     */
    @Override
    public void execute(Task task, Ui ui, Storage storage) {
        switch (this.commandType) {
        case "todo":
            ToDo taskItem = new ToDo(this.commandPostfix);
            storage.saveToDatafile(taskItem);
            ui.showTaskCount();
            break;
        case "deadline":
            String[] commandPostfixSplitBy = this.commandPostfix.split("/by ", 2);
            Deadline deadlineItem = new Deadline(commandPostfixSplitBy[0], commandPostfixSplitBy[1]);
            storage.saveToDatafile(deadlineItem);
            ui.showTaskCount();
            break;
        case "event":
            String[] splitFrom = this.commandPostfix.split("/from ", 2);
            String[] splitTo = splitFrom[1].split(" /to ", 2);
            Event eventItem = new Event(splitFrom[0], splitTo[0], splitTo[1]);
            storage.saveToDatafile(eventItem);
            ui.showTaskCount();
            break;
        default:
            break;
        }
    }

    /**
     * Use to exit the program
     *
     * @return false and do not exit
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
