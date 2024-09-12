package beeboo.command;

import beeboo.components.Storage;
import beeboo.components.TaskList;
import beeboo.components.Ui;
import beeboo.exception.InvalidCommandException;
import beeboo.exception.InvalidDateException;
import beeboo.exception.NoDescriptionException;
import beeboo.task.Deadlines;
import beeboo.task.Events;
import beeboo.task.Tasks;
import beeboo.task.ToDos;

/**
 * Represents a command to add a task to the chatbot's task list.
 */
public class AddCommand extends Command {
    private String type;
    private String command;

    /**
     * Constructs an AddCommand.
     *
     * @param type    The type of task to add (e.g., "e" for event, "t" for todo, "d" for deadline).
     * @param command The command string that contains the task description and details.
     */
    public AddCommand(String type, String command) {
        super(command);
        this.command = command;
        this.type = type;
    }

    /**
     * Executes the command to add a task to the task list, update the UI, and save the updated list to storage.
     *
     * @param tasks   The task list of the chatbot.
     * @param ui      The UI of the chatbot.
     * @param storage The storage functionality of the chatbot.
     * @throws InvalidDateException   If the date format in the command is incorrect.
     * @throws NoDescriptionException If the task description is missing.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidDateException, NoDescriptionException ,
            InvalidCommandException {
        Tasks task;
        switch(type) {
        case "e":
            task = Events.createEvent(command);
            break;
        case "t":
            task = ToDos.createToDo(command);
            break;
        case "d":
            task = Deadlines.createDeadline(command);
            break;
        default:
            throw new InvalidCommandException();
        }
        if (tasks.addList(task)) {
            storage.saveItem(tasks);
            return ui.addList(task, tasks.getSize());
        } else {
            return ui.duplicateTaskError();
        }
    }

    /**
     * Returns whether the command is an exit command.
     *
     * @return false, as this command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
