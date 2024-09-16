package xizi.chatbot.command;

import java.io.IOException;
import java.util.regex.Matcher;

import xizi.chatbot.Parser;
import xizi.chatbot.Storage;
import xizi.chatbot.Ui;
import xizi.chatbot.XiziException;
import xizi.chatbot.task.Task;
import xizi.chatbot.task.TaskList;


/**
 * Represents a command to delete a task from the task list.
 * This command is constructed using user input and is responsible for
 * identifying and removing the specified task from the task list.
 */
public class DeleteCommand implements Command {
    private final int taskNumber;

    /**
     * Constructs a DeleteCommand based on the user input.
     * Parses the input to extract the task number.
     *
     * @param userInput The input provided by the user, expected to match the delete command format.
     * @throws XiziException If the input format is invalid or the task number is not provided.
     */
    public DeleteCommand(String userInput) throws XiziException {
        Matcher matcher = CommandType.DELETE.matcher(userInput);
        if (matcher.matches()) {
            taskNumber = Integer.parseInt(matcher.group(1)) - 1;
        } else {
            throw new XiziException("Invalid delete command format. Use: delete <task number>");
        }
    }

    /**
     * Executes the delete command, removing the specified task from the task list.
     * Validates the task number before deletion, updates the storage,
     * and provides feedback to the user via the UI.
     *
     * @param actions The task list containing the tasks.
     * @param storage The storage handler for saving the updated task list.
     * @param ui The user interface for interacting with the user.
     * @throws IOException If an I/O error occurs during saving.
     * @throws XiziException If the task number is invalid or any other error occurs.
     */
    @Override
    public void execute(TaskList actions, Storage storage, Ui ui) throws IOException, XiziException {
        Parser.validateTaskNumber(taskNumber, actions);
        Task deleted = actions.deleteTask(taskNumber);
        storage.saveTasks(actions.getItems());
        ui.showLine();
        ui.printMessage("Noted. I've removed this task:");
        ui.printMessage("  " + deleted);
        ui.printMessage("Now you have " + actions.getSize() + " tasks in the list.");
        ui.showLine();
    }
}

