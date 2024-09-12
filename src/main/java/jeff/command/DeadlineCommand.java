package jeff.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import jeff.Storage;
import jeff.TaskList;
import jeff.Ui;
import jeff.exceptions.JeffException;
import jeff.task.Deadline;

/**
 * Represents a command that creates and adds a deadline task to the task list.
 * A deadline command is initialized with arguments that must contain a description
 * and a deadline formatted as "DD/MM/YYYY HHMM" after a "/by" separator.
 *
 * <p>Example of a valid command input: "submit assignment /by 01/10/2022 2359".
 * The command will parse this input and create a task with the description
 * "submit assignment" and the deadline "01/10/2022 2359".</p>
 */
public class DeadlineCommand extends Command {
    private String args;

    /**
     * Constructs a {@code DeadlineCommand} with the specified arguments.
     *
     * @param args the command arguments expected to include a task description
     *             followed by "/by" and the due date and time.
     * @throws JeffException if the {@code args} is empty, does not contain "/by",
     *                       or has other errors.
     */
    public DeadlineCommand(String args) throws JeffException {
        super();
        if (args.isEmpty() || !args.contains("/by")) {
            throw new JeffException("You must provide a valid deadline task in the format: task /by date!");
        }
        this.args = args;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JeffException {
        String[] parts = args.split("/by", 2);
        try {
            tasks.addTask(new Deadline(parts[0].trim(),
                    LocalDateTime.parse(parts[1].trim(), Storage.getDateTimeFormatter())));
            storage.saveTask(tasks.getTasks());
            ui.showMessage("added: " + tasks.getTask(tasks.size() - 1));
        } catch (DateTimeParseException e) {
            throw new JeffException("You need to format your dates as follows: " + Storage.getDateFormat());
        }
    }
}
