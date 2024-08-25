package hana.command;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import hana.HanaException;
import hana.storage.Storage;
import hana.task.Deadline;
import hana.task.Event;
import hana.task.Task;
import hana.tasklist.TaskList;
import hana.ui.Ui;

/**
 * Represents a command to find task by date
 * When executed, this command will print tasks from date.
 */
public class FindByDateCommand extends Command {
    private String input;

    /**
     * Constructs a new EventCommand with input.
     *
     * @param input The input from user.
     */
    public FindByDateCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the command to find task by date.
     *
     * @param taskList The list of tasks.
     * @param ui The UI object used to interact with the user.
     * @param storage The storage object to handle reading/writing of tasks.
     * @throws HanaException If an error occurs during command execution.
     */
    @Override
    public void execute(
            TaskList taskList, Ui ui, Storage storage) throws HanaException {
        try {
            String[] parts = input.split(" ", 2);
            if (parts.length < 2) {
                throw new HanaException("Please specify a date.");
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            LocalDate date = LocalDate.parse(parts[1].trim(), formatter);

            ui.printLine();
            ui.printMessage("Tasks occurring on " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":");
            boolean found = false;

            for (Task task : taskList.getTasks()) {
                if (task instanceof Deadline) {
                    LocalDateTime taskDate = ((Deadline) task).getDeadline();
                    if (taskDate.toLocalDate().equals(date)) {
                        ui.printMessage(task.toString());
                        found = true;
                    }
                } else if (task instanceof Event) {
                    LocalDateTime taskDateFrom = ((Event) task).getFrom();
                    LocalDateTime taskDateTo = ((Event) task).getTo();
                    if ((taskDateFrom.toLocalDate().equals(date) || taskDateFrom.toLocalDate().isBefore(date)) && (
                            taskDateTo.toLocalDate().equals(date) || taskDateTo.toLocalDate().isAfter(date))) {
                        ui.printMessage(task.toString());
                        found = true;
                    }
                }
            }
            if (!found) {
                ui.printMessage("No tasks found for this date.");
            }
            ui.printLine();
        } catch (DateTimeParseException e) {
            throw new HanaException("Please enter the date in the correct format: [d/M/yyyy]");
        }
    }
}
