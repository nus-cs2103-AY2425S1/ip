package jeff.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import jeff.Storage;
import jeff.TaskList;
import jeff.Ui;
import jeff.exceptions.JeffException;
import jeff.task.Deadline;
import jeff.task.Task;

/**
 * Represents a command that prints tasks due on a specified date.
 * This command is initialized with a date argument in the format "dd/MM/yyyy" and
 * checks the task list for any deadlines that match this date.
 */
public class PrintCommand extends Command {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private String args;

    /**
     * Constructs a {@code PrintCommand} with the specified date arguments.
     * The date must be provided in the "dd/MM/yyyy" format to correctly parse and match tasks.
     *
     * @param args the date argument used to find tasks due on that specific date.
     * @throws JeffException if {@code args} is empty, indicating that no date was provided.
     */
    public PrintCommand(String args) throws JeffException {
        super();

        if (args.isEmpty()) {
            throw new JeffException("You must provide a date after the command!");
        }

        this.args = args;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JeffException {
        try {
            LocalDate date = LocalDate.parse(args, DATE_FORMATTER);
            ui.showMessage("Tasks due on " + date.format(DATE_FORMATTER) + ":");
            boolean isFound = false;
            for (Task task : tasks.getTasks()) {
                // Guard clause
                if (!(task instanceof Deadline deadlineTask)) {
                    continue;
                }

                // Check if deadline date is same as search date
                if (deadlineTask.getDueDate().toLocalDate().equals(date)) {
                    ui.showMessage(deadlineTask.toString());
                    isFound = true;
                }
            }

            if (!isFound) {
                ui.showMessage("No tasks due on this date.");
            }
        } catch (DateTimeParseException e) {
            throw new JeffException("Invalid date format! Please use 'DD/MM/YYYY'.");
        }
    }
}
