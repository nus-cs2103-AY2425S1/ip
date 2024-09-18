package xbot.command;

import xbot.TaskList;
import xbot.exception.XBotException;
import xbot.parser.Parser;
import xbot.storage.Storage;
import xbot.task.Deadline;
import xbot.task.Task;
import xbot.ui.Ui;

/**
 * Handles the "deadline" command.
 */
public class DeadlineCommand extends AddCommand {
    @Override
    public String execute(TaskList list, Ui ui, Storage storage, String rest) throws XBotException {
        if (rest.trim().isEmpty()) {
            throw new XBotException("The description of the deadline cannot be empty!");
        }
        String output = addDeadline(list, rest);
        storage.saveTask(list);
        return output;
    }

    /**
     * Adds a new Deadline task to the task list.
     * The task description should be followed by a /by keyword and the deadline.
     *
     * @param rest The description and deadline of the task.
     * @throws XBotException If the input format is invalid or the date is in an incorrect format.
     */
    public String addDeadline(TaskList list, String rest) throws XBotException {
        String[] parts = rest.split("/by", 2);
        if (parts.length == 2) {
            String taskDescription = parts[0].trim();
            String deadline = parts[1].trim();
            if (!deadline.isEmpty() && Parser.isValidDateFormat(deadline)) {
                Task newTask = new Deadline(taskDescription, deadline);
                list.add(newTask);

                String output;
                output = ("Got it. I've added this task:\n");
                output = output + (newTask.toString() + "\n");
                output = output + ("Now you have " + list.size() + " tasks in the list.");
                return output;
            } else {
                throw new XBotException("Invalid date input format. Please use the format: D/M/YYYY");
            }
        } else {
            throw new XBotException("Invalid input format. Please use the format: 'deadline <task> /by <date>'");
        }
    }
}

