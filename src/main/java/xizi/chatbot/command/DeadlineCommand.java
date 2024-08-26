package xizi.chatbot.command;

import xizi.chatbot.task.Deadline;
import xizi.chatbot.XiziException;
import xizi.chatbot.Parser;
import xizi.chatbot.task.TaskList;
import xizi.chatbot.Storage;
import xizi.chatbot.Ui;
import xizi.chatbot.task.Task;

import java.io.IOException;
import java.util.regex.Matcher;
import java.time.LocalDateTime;

/**
 * Represents a command to create and manage deadline tasks in the Xizi chatbot.
 * The command parses user input to extract the task description and deadline date/time,
 * and then executes the command by adding the task to the task list and saving it to storage.
 */
public class DeadlineCommand implements Command {
    private final String taskDescription;
    private final LocalDateTime deadline;

    /**
     * Constructs a {@code DeadlineCommand} object by parsing the user input.
     * The user input should follow the format: {@code deadline <description> /by <date time>}.
     *
     * @param userInput the user input string to be parsed
     * @throws XiziException if the user input is not in the correct format
     *                       or if the task description or deadline is empty
     */
    public DeadlineCommand(String userInput) throws XiziException {
        Matcher matcher = CommandType.DEADLINE.matcher(userInput);
        if (matcher.matches()) {
            taskDescription = matcher.group(1).trim();
            String deadlineStr = matcher.group(2).trim();
            if (taskDescription.isEmpty() || deadlineStr.isEmpty()) {
                throw new XiziException("The description or time of a deadline cannot be empty.");
            }
            deadline = Parser.parseDateTime(deadlineStr);
        } else {
            throw new XiziException("Invalid deadline command format. Use: deadline <description> /by <date time>");
        }
    }

    /**
     * Executes the deadline command by creating a new {@code Deadline} task,
     * adding it to the task list, and saving it to storage.
     * The method also updates the user interface to reflect the new task addition.
     *
     * @param actions the {@code TaskList} where the new task will be added
     * @param storage the {@code Storage} used to save the new task
     * @param ui      the {@code Ui} used to display messages to the user
     * @throws IOException   if an I/O error occurs while saving the task
     * @throws XiziException if there are issues related to the task or storage
     */
    @Override
    public void execute(TaskList actions, Storage storage, Ui ui) throws IOException, XiziException {
        Task task = new Deadline(taskDescription, deadline);
        actions.addTask(task);
        storage.appendTask(task);
        ui.showLine();
        ui.printMessage("Got it. I've added this task:");
        ui.printMessage("  " + task);
        ui.printMessage("Now you have " + actions.getSize() + " tasks in the list.");
        ui.showLine();
    }
}
