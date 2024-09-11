package beeboo.command;

import beeboo.components.Storage;
import beeboo.components.TaskList;
import beeboo.components.Ui;
import beeboo.exception.InvalidDateException;
import beeboo.exception.NoDescriptionException;
import beeboo.task.Deadlines;
import beeboo.task.Events;
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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidDateException, NoDescriptionException {
        switch(type) {
        case "e":
            Events event = Events.createEvent(command);
            if (tasks.addList(event)) {
                storage.saveItem(tasks);
                return ui.addList(event, tasks.getSize());
            } else {
                return ui.duplicateTaskError();
            }
        case "t":
            ToDos todo = ToDos.createToDo(command);
            if (tasks.addList(todo)) {
                storage.saveItem(tasks);
                return ui.addList(todo, tasks.getSize());
            } else {
                return ui.duplicateTaskError();
            }
        case "d":
            Deadlines deadline = Deadlines.createDeadline(command);
            if (tasks.addList(deadline)) {
                storage.saveItem(tasks);
                return ui.addList(deadline, tasks.getSize());
            } else {
                return ui.duplicateTaskError();
            }
        default:
            return "";
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
