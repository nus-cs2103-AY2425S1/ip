package xizi.chatbot.command;

import java.io.IOException;
import java.util.regex.Matcher;

import xizi.chatbot.Storage;
import xizi.chatbot.Ui;
import xizi.chatbot.XiziException;
import xizi.chatbot.task.Task;
import xizi.chatbot.task.TaskList;
import xizi.chatbot.task.Todo;


/**
 * Represents a command to add a new todo task to the task list.
 * This command allows the user to specify a task description for a basic todo item.
 */
public class TodoCommand implements Command {
    private final String taskDescription;

    /**
     * Constructs a TodoCommand based on the user input.
     * Parses the input to extract the task description for the todo item.
     *
     * @param userInput The user input containing the task description.
     * @throws XiziException If the input format is invalid or the description is empty.
     */
    public TodoCommand(String userInput) throws XiziException {
        Matcher matcher = CommandType.TODO.matcher(userInput);
        if (matcher.matches()) {
            taskDescription = matcher.group(1).trim();
            if (taskDescription.isEmpty()) {
                throw new XiziException("The description of a todo cannot be empty.");
            }
        } else {
            throw new XiziException("Invalid todo command format. Use: todo <task description>");
        }
    }

    /**
     * Executes the todo command, adding the specified todo task to the task list.
     * This method adds the new task, updates the storage, and provides feedback to the user.
     *
     * @param actions The task list containing all tasks.
     * @param storage The storage handler for saving the updated task list.
     * @param ui      The user interface for interacting with the user.
     * @throws IOException   If an I/O error occurs during saving the tasks.
     * @throws XiziException If the task cannot be added.
     */
    @Override
    public void execute(TaskList actions, Storage storage, Ui ui) throws IOException, XiziException {
        Task task = new Todo(taskDescription);
        // Check if the task already exists in the task list
        if (actions.getItems().contains(task)) {
            ui.showLine();
            ui.printMessage("This task already exists in the list:");
            ui.printMessage("  " + task);
            ui.showLine();
            return;
        }
        actions.addTask(task);
        storage.appendTask(task);
        ui.showLine();
        ui.printMessage("Got it. I've added this task:");
        ui.printMessage("  " + task);
        ui.printMessage("Now you have " + actions.getSize() + " tasks in the list.");
        ui.showLine();
    }
}

