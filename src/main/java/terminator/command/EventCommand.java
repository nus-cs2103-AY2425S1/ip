package terminator.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import terminator.task.EventTask;
import terminator.task.Task;

/**
 * Concrete class representing a command to create a EventTask.
 */
public class EventCommand extends Command {

    private static final String ERR_MSG = """
            Event description cannot be empty.\n
            Usage: event <description> /from dd/MM/yyyy HH:mm /to dd/MM/yyyy HH:mm""";

    public EventCommand(String input) {
        super(input);
    }

    /**
     * Creates a new EventTask with the given input and adds it to the task list.
     *
     * @param todoList The task list.
     * @throws TerminatorException if the description of the Event task is empty.
     */
    @Override
    public String execute(ArrayList<Task> todoList) throws TerminatorException {
        if (input == null) {
            throw new TerminatorException(ERR_MSG);
        }

        // Extract description string and indices of '/from' and '/to'
        int fromDateIdx = input.indexOf("/from");
        String description = input.substring(0, fromDateIdx).trim();
        if (description.isEmpty()) {
            throw new TerminatorException(ERR_MSG);
        }
        int toDateIdx = input.indexOf("/to");

        // Extract the date time strings
        String fromDateString = input.substring(fromDateIdx + 6, toDateIdx).trim();
        String toDateString = input.substring(toDateIdx + 4).trim();

        // Create LocalDateTime objects
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime fromDate = LocalDateTime.parse(fromDateString, dateTimeFormatter);
        LocalDateTime toDate = LocalDateTime.parse(toDateString, dateTimeFormatter);

        // Add to TaskList
        Task t = new EventTask(description, fromDate, toDate);
        todoList.add(t);

        return "Mission parameters updated. Added new objective:\n\n" + t;
    }
}
