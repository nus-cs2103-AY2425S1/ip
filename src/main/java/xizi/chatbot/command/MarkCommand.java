package xizi.chatbot.command;

import java.io.IOException;
import java.util.regex.Matcher;

import xizi.chatbot.Parser;
import xizi.chatbot.Storage;
import xizi.chatbot.Ui;
import xizi.chatbot.XiziException;
import xizi.chatbot.task.TaskList;


/**
 * Represents a command to mark a specific task as done in the task list.
 * This command updates the task's status to indicate that it has been completed.
 */
public class MarkCommand implements Command {
    private final int taskNumber;

    /**
     * Constructs a MarkCommand based on the user input.
     * Parses the input to extract the task number that should be marked as done.
     *
     * @param userInput The user input containing the task number.
     * @throws XiziException If the input format is invalid or the task number cannot be parsed.
     */
    public MarkCommand(String userInput) throws XiziException {
        Matcher matcher = CommandType.MARK.matcher(userInput);
        if (matcher.matches()) {
            taskNumber = Integer.parseInt(matcher.group(1)) - 1;
        } else {
            throw new XiziException("Invalid mark command format. Use: mark <task number>");
        }
    }

    /**
     * Executes the mark command, marking the specified task as done.
     * This method validates the task number, marks the task as done,
     * and updates the storage to reflect the change.
     *
     * @param actions The task list containing all tasks.
     * @param storage The storage handler for saving the updated task list.
     * @param ui      The user interface for interacting with the user.
     * @throws IOException   If an I/O error occurs during saving the tasks.
     * @throws XiziException If the task number is invalid.
     */
    @Override
    public void execute(TaskList actions, Storage storage, Ui ui) throws IOException, XiziException {
        Parser.validateTaskNumber(taskNumber, actions);
        ui.showLine();
        ui.printMessage("Nice! I've marked this task as done: ");
        ui.printMessage(actions.markTask(taskNumber));
        ui.showLine();
        storage.saveTasks(actions.getItems());
    }
}
