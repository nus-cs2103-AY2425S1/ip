package xizi.chatbot.command;

import java.io.IOException;
import java.util.regex.Matcher;

import xizi.chatbot.Parser;
import xizi.chatbot.Storage;
import xizi.chatbot.Ui;
import xizi.chatbot.XiziException;
import xizi.chatbot.task.TaskList;



/**
 * Represents a command to unmark a task as not done in the task list.
 * This command allows the user to specify a task by its number and unmark it.
 */
public class UnmarkCommand implements Command {
    private final int taskNumber;

    /**
     * Constructs an UnmarkCommand based on the user input.
     * Parses the input to extract the task number to be unmarked.
     *
     * @param userInput The user input containing the task number to unmark.
     * @throws XiziException If the input format is invalid or the task number is missing.
     */
    public UnmarkCommand(String userInput) throws XiziException {
        Matcher matcher = CommandType.UNMARK.matcher(userInput);
        if (matcher.matches()) {
            taskNumber = Integer.parseInt(matcher.group(1)) - 1;
        } else {
            throw new XiziException("Invalid unmark command format. Use: unmark <task number>");
        }
    }

    /**
     * Executes the unmark command, marking the specified task as not done in the task list.
     * This method updates the task status, saves the updated task list, and provides feedback to the user.
     *
     * @param actions The task list containing all tasks.
     * @param storage The storage handler for saving the updated task list.
     * @param ui      The user interface for interacting with the user.
     * @throws IOException   If an I/O error occurs during saving the tasks.
     * @throws XiziException If the task number is invalid or the task cannot be unmarked.
     */
    @Override
    public void execute(TaskList actions, Storage storage, Ui ui) throws IOException, XiziException {
        Parser.validateTaskNumber(taskNumber, actions);
        ui.showLine();
        ui.printMessage("OK, I've marked this task as not done yet:");
        ui.printMessage(actions.unmarkTask(taskNumber));
        ui.showLine();
        storage.saveTasks(actions.getItems());
    }
}
