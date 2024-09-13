package command;

import main.Storage;
import main.TaskList;
import main.Ui;

/**
 * This class is used to handle adding tasks
 */
public class AddTaskCommand extends Command {

    private String taskType;

    /**
     * Creates a new AddTaskCommand with the given description and type
     * @param description The description of the task
     * @param type The type of the task
     */
    public AddTaskCommand(String description, String type) {
        super(description);
        this.taskType = type;
    }

    /**
     * Executes the command to add a task to the taskList
     * @param taskList The list of tasks
     * @param ui The user interface
     * @param storage The storage
     * @return The message to be displayed
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String displayMessage;
        assert taskType.equals("todo") || taskType.equals("event") || taskType.equals("deadline") : "Invalid task type";
        switch (taskType) {
        case "todo":
            displayMessage = ui.displayAddMessage(taskList.addTodo(super.getDescription()), taskList.size());
            break;
        case "event":
            displayMessage = ui.displayAddMessage(taskList.addEvent(super.getDescription()), taskList.size());
            break;
        case "deadline":
            displayMessage = ui.displayAddMessage(taskList.addDeadline(super.getDescription()), taskList.size());
            break;
        default:
            return "I don't understand what you are trying to say :(";
        }
        storage.writeStorage(taskList.getTaskList());
        return displayMessage;
    }
}
