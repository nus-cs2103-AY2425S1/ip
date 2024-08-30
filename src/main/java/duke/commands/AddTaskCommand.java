package duke.commands;

import duke.exceptions.InvalidTodoDescriptionException;
import duke.exceptions.UnknownMessageException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to add a task to the task list in the DailyTasks application.
 * This command parses the user input and attempts to add the task to the task list.
 */
public class AddTaskCommand extends Command {

    /** The user input containing the task description and any relevant details. */
    private final String userInput;

    /**
     * Constructs a new AddTaskCommand with the specified user input.
     *
     * @param userInput The user's input string representing the task to be added.
     */
    public AddTaskCommand(String userInput) {
        super();
        this.userInput = userInput;
    }

    /**
     * Executes the command by adding a task to the task list.
     * If the task description is empty or unrecognized, an appropriate error message is displayed.
     *
     * @param taskList The list of tasks to which the new task will be added.
     * @param ui The user interface used to display messages to the user.
     * @param storage The storage system responsible for saving and loading tasks.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.addTask(this.userInput);
            return "Your task has been added";
        } catch (UnknownMessageException | InvalidTodoDescriptionException e) {
            // ui.showMessage(ui.formatOutputMessage("Please enter a valid task!"));
            return "Please enter a valid task!";
        }
    }
}
