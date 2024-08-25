package command;

import task.EventTask;
import task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class EventCommand extends Command {

    private final String input;

    private static final String ERR_MSG = """
            Event description cannot be empty.
            
            Usage: event <description> /from dd/MM/yyyy HH:mm /to dd/MM/yyyy HH:mm""";

    public EventCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(ArrayList<Task> todoList) throws DukeException {
        if (input == null) {
            throw new DukeException(ERR_MSG);
        }

        // Extract description string and indices of '/from' and '/to'
        int fromDateIdx = input.indexOf("/from");
        String description = input.substring(0, fromDateIdx).trim();
        if (description.isEmpty()) {
            throw new DukeException(ERR_MSG);
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

        System.out.println("Mission parameters updated. Added new objective:\n\n" + t);
    }
}
