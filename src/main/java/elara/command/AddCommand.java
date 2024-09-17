package elara.command;

import elara.task.InvalidInputException;
import elara.task.Task;
import elara.utils.Parser;
import elara.utils.Storage;
import elara.utils.TaskList;
import elara.utils.Ui;

/**
 * Represents a command to add tasks (ToDo, Deadline, Event) to the task list.
 * This command is executed in the Elara chatbot based on user input.
 */
public class AddCommand implements Command {
    private final String commandType;
    private final String details;

    /**
     * Constructs an AddCommand object.
     *
     * @param commandType the type of the task to be added (todo, deadline, or event).
     * @param fullInput the full input string from the user.
     */
    public AddCommand(String commandType, String fullInput) {
        this.commandType = commandType;
        this.details = (fullInput.split(" ", 2).length > 1)
                ? fullInput.split(" ", 2)[1].toLowerCase()
                : null;
    }

    /**
     * Executes the command to add a task to the task list.
     *
     * @param taskList the list of tasks to add the new task to.
     * @param ui the user interface used to interact with the user.
     * @param storage the storage system used to save tasks to the file.
     * @throws InvalidInputException if the input format is invalid.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidInputException {
        Task newTask = switch (commandType) {
        case "todo" -> Parser.parseToDo(details);
        case "deadline" -> Parser.parseDeadline(details);
        case "event" -> Parser.parseEvent(details);
        default -> throw new InvalidInputException("Unknown command type: " + commandType);
        };
        if (taskList.isDuplicate(newTask)) {
            ui.showDuplicateMessage(newTask);
            return;
        }

        taskList.addTask(newTask);
        ui.showAddTaskMessage(newTask, taskList);
        storage.write(taskList);
    }
}
