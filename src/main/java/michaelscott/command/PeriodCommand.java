package michaelscott.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import michaelscott.task.Period;
import michaelscott.task.TaskList;
import michaelscott.utils.MichaelScottException;

/**
 * Represents a command to create a new event task.
 * This class parses the input string to extract the event description, start time, and end time,
 * and creates a new Event task when executed.
 */
public class PeriodCommand implements Command {
    private final String description;
    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Constructs a new PeriodCommand by parsing the given arguments.
     *
     * @param args The string containing the event description, start time, and end time.
     * @throws MichaelScottException If the input format is invalid or the dates cannot be parsed.
     */
    public PeriodCommand(String args) throws MichaelScottException {
        assert args != null : "args to period cannot be null";

        String[] periodParts = args.split(" /start | /end ");
        if (periodParts.length != 3) {
            throw new MichaelScottException(
                    "Please provide the period description, start time (/start), and end time(/end). \n"
                            + "Here is an example: period Collect certificate /start 2024-02-02 /end 2024-02-02"
            );
        }
        description = periodParts[0];
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            startDate = LocalDate.parse(periodParts[1], formatter);
            endDate = LocalDate.parse(periodParts[2], formatter);
        } catch (java.time.format.DateTimeParseException e) {
            throw new MichaelScottException(
                    "Invalid date format: Please use the format YYYY-MM-DD"
            );
        }
    }

    @Override
    public String execute(TaskList tasks) {
        assert tasks != null : "tasks cannot be null";

        Period periodTask = new Period(this.description, this.startDate, this.endDate);
        tasks.addTask(periodTask);
        return "Got it. I've added this task: \n"
                + "   " + periodTask.toString() + "\n"
                + "Now you have " + tasks.size()
                + (tasks.size() > 1 ? " tasks " : " task ") + "in the list.";
    }

    @Override
    public String getSimpleName() {
        return "PeriodCommand";
    }
}
