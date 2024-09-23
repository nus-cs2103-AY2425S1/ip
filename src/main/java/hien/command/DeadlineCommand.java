package hien.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import hien.exception.HienException;
import hien.main.Storage;
import hien.main.TaskList;
import hien.task.Deadline;
import hien.ui.UI;

/**
 * Represents a command to add a Deadline task to the task list.
 * The command takes user input in the format "deadline <description> /by <yyyy-MM-dd HHmm>"
 * and adds the deadline to the list of tasks.
 */
public class DeadlineCommand extends Command {
    private static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private String input;

    /**
     * Constructs a DeadlineCommand object.
     *
     * @param input  The user input specifying the deadline details.
     * @param isExit Specifies whether this command will terminate the application.
     */
    public DeadlineCommand(String input, boolean isExit) {
        super(isExit);
        this.input = input;
    }

    /**
     * Adds a Deadline task to the task list.
     *
     * @param tasks   The task list to add the Deadline to.
     * @param input   The user input containing the description and due date of the deadline.
     * @param storage The storage object used to save the task list.
     * @param ui      The UI object used to interact with the user.
     * @return        A message indicating the result of adding the deadline.
     * @throws HienException If the input format is incorrect or the date cannot be parsed.
     */
    private String addDeadline(TaskList tasks, String input, Storage storage, UI ui) throws HienException {
        String[] parts = input.substring(8).split(" /by ");
        String msg = "";
        if (parts.length == 2) {
            try {
                LocalDateTime dateTime = LocalDateTime.parse(parts[1].trim(), INPUT_DATE_FORMAT);
                Deadline deadline = new Deadline(parts[0].trim(), dateTime);
                tasks.addTask(deadline);
                storage.save(tasks);
                msg += ui.showMessage(" Got it. I've added this task:");
                msg += ui.showMessage("   " + deadline);
                msg += ui.showMessage(" Now you have " + tasks.size() + " tasks in the list.");
                return msg;
            } catch (DateTimeParseException e) {
                throw new HienException("☹ OOPS!!! The date format is incorrect. Please use: yyyy-MM-dd HHmm");
            }
        } else {
            throw new HienException(" ☹ OOPS!!! The deadline format is incorrect. "
                    + "Please use: deadline <description> /by <yyyy-MM-dd HHmm>");
        }
    }

    /**
     * Executes the command to add a Deadline task.
     *
     * @param tasks   The task list that the command operates on.
     * @param ui      The UI object used to interact with the user.
     * @param storage The storage object used to save tasks.
     * @return        The result of the command execution as a string.
     * @throws HienException If the input format is invalid or date parsing fails.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws HienException {
        return addDeadline(tasks, input, storage, ui);
    }
}