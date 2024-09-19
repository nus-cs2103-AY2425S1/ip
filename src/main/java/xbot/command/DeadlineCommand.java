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
            throw new XBotException("I cannot add a deadline with empty description >.<");
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
                output = ("Here comes another pending deadline! \n" +
                        "I've added this task:\n");
                output = output + (newTask.toString() + "\n");
                output = output + ("And now you have " + list.size() + " tasks in the list!! Jiayouu :D");
                return output;
            } else {
                throw new XBotException("Sorry...I cannot read this date input >_< \n" +
                        "you might want to try these date format instead :0\n" +
                        "D/M/YYYY (e.g. 9/4/2024) or D/M/YYYY HHMM (e.g. 9/4/2024 2359)");
            }
        } else {
            throw new XBotException("Sorry... I do not understand your input... >_< \n" +
                    "could you use this format instead? \n" +
                    "deadline <task> /by <date> (e.g. deadline Assignment 1 /by 9/4/2024 2359)");
        }
    }
}

