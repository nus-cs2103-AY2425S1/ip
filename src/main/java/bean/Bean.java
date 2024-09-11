package bean;

import bean.command.Command;
import bean.parser.Parser;
import bean.storage.Storage;
import bean.task.DeadlineTask;
import bean.task.EventTask;
import bean.task.Task;
import bean.task.TodoTask;
import bean.ui.Ui;

import java.util.List;


/**
 * The Bean class represents the main entry point of the application.
 * It handles the interaction between the user, the task list, and the storage.
 */
public class Bean {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Initialises the Bean application with the specified file path for storage.
     *
     * @param filePath The path of the file to be used for storage.
     */
    public Bean(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            System.out.println(ui.getLoadingError());
            tasks = new TaskList();
        }
    }

    /**
     * Returns the Ui instance associated with this Bean.
     * The Ui instance is responsible for handling user interactions,
     * such as displaying messages and reading user input.
     *
     * @return The Ui instance associated with this Bean.
     */
    public Ui getUi() {
        return this.ui;
    }

    /**
     * Processes the user input, executes commands, and returns the response string for the GUI.
     *
     * @param input The user's input command.
     * @return The response string to be displayed in the GUI.
     */
    public String getResponse(String input) {
        try {
            if (input.equals("bye")) {
                return ui.getGoodbye();
            }

            Command command = parser.parseCommand(input);
            String commandType = command.getType();
            String details = command.getDetails();
            switch (command.getType()) {
            case "list":
                return ui.getTasks(tasks.getTasks());

            case "mark":
            case "unmark":
            case "delete":
                int index = Integer.parseInt(command.getDetails()) - 1;
                return handleTaskOperation(commandType, index);
            case "todo":
                return handleTodoTask(details);
            case "deadline":
                return handleDeadlineTask(details);
            case "event":
                return handleEventTask(details);
            case "find":
                List<Task> foundTasks = tasks.findTasks(command.getDetails());
                return ui.getMatchingTasks(foundTasks);

            default:
                return ui.getError("Unknown command");
            }
        } catch (Exception e) {
            return ui.getError(e.getMessage());
        }
    }

    private String handleEventTask(String details) {
        String[] eventParts = details.split(" /from | /to ");
        Task event = new EventTask(eventParts[0], eventParts[1], eventParts[2]);
        tasks.addTask(event);
        storage.save(tasks.getTasks());
        return ui.getTaskAdded(event, tasks.size());
    }

    private String handleDeadlineTask(String details) {
        String[] deadlineParts = details.split(" /by ");
        Task deadline = new DeadlineTask(deadlineParts[0], deadlineParts[1]);
        tasks.addTask(deadline);
        storage.save(tasks.getTasks());
        return ui.getTaskAdded(deadline, tasks.size());
    }

    private String handleTodoTask(String details) {
        Task todo = new TodoTask(details);
        tasks.addTask(todo);
        storage.save(tasks.getTasks());
        return ui.getTaskAdded(todo, tasks.size());
    }

    /**
     * Checks if the given index is valid for the task list.
     *
     * @param index The index to be checked.
     * @return True if the index is valid, false otherwise.
     */
    private boolean isValidIndex(int index) {
        return index >= 0 && index < tasks.size();
    }

    /*
     * Handles operations on tasks like marking, unmarking, and deleting.
     *
     * @param commandType The type of operation to perform.
     * @param index The index of the task to operate on.
     */
    private String handleTaskOperation(String commandType, int index) {
        if (!isValidIndex(index)) {
            return ui.getError("Task index is out of bounds.");
        }
        switch (commandType) {
        case "mark":
            tasks.markTask(index);
            storage.save(tasks.getTasks());
            return ui.getTaskMarked(tasks.getTask(index));

        case "unmark":
            tasks.unmarkTask(index);
            storage.save(tasks.getTasks());
            return ui.getTaskUnmarked(tasks.getTask(index));

        case "delete":
            Task removedTask = tasks.deleteTask(index);
            storage.save(tasks.getTasks());
            return ui.getTaskDeleted(removedTask, tasks.size());

        default:
            return ui.getError("Invalid operation.");
        }
    }
}
