package jeff.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import jeff.Storage;
import jeff.TaskList;
import jeff.Ui;
import jeff.exceptions.JeffException;
import jeff.task.Event;

/**
 * Represents a command that creates and adds an event task to the task list.
 * An event command is initialized with arguments that must contain a description,
 * a start date/time, and an end date/time formatted as "DD/MM/YYYY HHMM" separated by "/from" and "/to".
 *
 * <p>Example of a valid command input: "team meeting /from 01/10/2022 0900 /to 01/10/2022 1100".
 * The command will parse this input and create an event with the description
 * "team meeting", a start time "01/10/2022 0900", and an end time "01/10/2022 1100".</p>
 */
public class EventCommand extends Command {
    private String args;

    /**
     * Constructs a {@code EventCommand} with the specified arguments.
     *
     * @param args the command arguments expected to include a task description
     *             followed by "/from" and the start date/time, then "/to" and the end date/time.
     * @throws JeffException if {@code args} is empty, does not properly include "/from" and "/to",
     *                       or is otherwise malformed.
     */
    public EventCommand(String args) throws JeffException {
        super();
        if (args.isEmpty() || !args.contains("/from") || !args.contains("/to")) {
            throw new JeffException("You must provide a valid event task in the format: task /from date /to date!");
        }
        this.args = args;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JeffException {
        String[] parts = args.split("/from | /to ", 3);
        if (parts.length < 3) {
            throw new JeffException("You did not follow the format for providing an event!"
                    + "\nIt should be: task /from date /to date");
        }
        try {
            tasks.addTask(new Event(
                    parts[0].trim(),
                    LocalDateTime.parse(parts[1].trim(), Storage.getDateTimeFormatter()),
                    LocalDateTime.parse(parts[2].trim(), Storage.getDateTimeFormatter())
            ));
            storage.saveTask(tasks.getTasks());
            ui.showMessage("added: " + tasks.getTask(tasks.size() - 1));
        } catch (DateTimeParseException e) {
            throw new JeffException("You need to format your dates as follows: " + Storage.getDateFormat());
        }
    }
}
