package bean;

import bean.command.Command;
import bean.parser.Parser;
import bean.storage.Storage;
import bean.task.DeadlineTask;
import bean.task.EventTask;
import bean.task.Task;
import bean.task.TodoTask;
import bean.ui.Ui;

/**
 * The Bean class represents the main entry point of the application.
 * It handles the interaction between the user, the task list, and the storage.
 */
import java.util.List;

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
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts the conversation with the user, processes commands, and interacts with the task list.
     */
    public void startConversation() {
        ui.showGreeting();
        while (true) {
            try {
                String input = ui.getUserInput();
                if (input.equals("bye")) {
                    ui.showGoodbye();
                    break;
                }
                Command command = parser.parseCommand(input);

                switch (command.getType()) {

                    case "list":
                        ui.showTasks(tasks.getTasks());
                        break;
                    case "mark":
                    case "unmark":
                    case "delete":
                        int index = Integer.parseInt(command.getDetails()) - 1;
                        handleTaskOperation(command.getType(), index);
                        break;
                    case "todo":
                        tasks.addTask(new TodoTask(command.getDetails()));
                        ui.showTaskAdded(tasks.getTask(tasks.size() - 1), tasks.size());
                        break;
                    case "deadline":
                        String[] deadlineParts = command.getDetails().split(" /by ");
                        tasks.addTask(new DeadlineTask(deadlineParts[0], deadlineParts[1]));
                        ui.showTaskAdded(tasks.getTask(tasks.size() - 1), tasks.size());
                        break;
                    case "event":
                        String[] eventParts = command.getDetails().split(" /from | /to ");
                        tasks.addTask(new EventTask(eventParts[0], eventParts[1], eventParts[2]));
                        ui.showTaskAdded(tasks.getTask(tasks.size() - 1), tasks.size());
                        break;
                    case "find":
                        List<Task> foundTasks = tasks.findTasks(command.getDetails());
                        ui.showMatchingTasks(foundTasks);
                        break;
                    default:
                        ui.showError("Unknown command");
                        break;
                }
                storage.save(tasks.getTasks());
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Checks if the given index is valid for the task list.
     *
     * @param index The index to be checked.
     * @return True if the index is valid, false otherwise.
     */
    private boolean isValidIndex(int index) {
        if (index < 0 || index >= tasks.size()) {
            ui.showError("bean.task.Task index is out of bounds.");
            return false;
        }
        return true;
    }

    /**
     * Handles operations on tasks like marking, unmarking, and deleting.
     *
     * @param commandType The type of operation to perform.
     * @param index The index of the task to operate on.
     */
    private void handleTaskOperation(String commandType, int index) {
        if (isValidIndex(index)) {
            switch (commandType) {
            case "mark":
                tasks.markTask(index);
                ui.showTaskMarked(tasks.getTask(index));
                break;
            case "unmark":
                tasks.unmarkTask(index);
                ui.showTaskUnmarked(tasks.getTask(index));
                break;
            case "delete":
                Task removedTask = tasks.deleteTask(index);
                ui.showTaskDeleted(removedTask, tasks.size());
                break;
            }
        }
    }

    /**
     * The main method that starts the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Bean("data/bean.txt").startConversation();
    }
}
