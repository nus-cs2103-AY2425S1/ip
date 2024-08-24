package myapp.command;

import myapp.core.BingBongException;
import myapp.core.BingBongUi;
import myapp.core.DateTimeHandler;
import myapp.core.Storage;
import myapp.task.Deadline;
import myapp.task.Event;
import myapp.task.Task;
import myapp.task.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ListOnCommand extends ListCommand {
    LocalDate queryDate;
    public ListOnCommand(String date) throws BingBongException {
        super();
        try {
            queryDate = DateTimeHandler.parseDate(date);
        } catch (DateTimeParseException e) {
            throw new BingBongException("Invalid date format. Please use the format: d/M/yyyy.");
        }
    }

    @Override
    public void execute(TaskList tasks, BingBongUi ui, Storage storage) {

        StringBuilder list = new StringBuilder("Tasks on " + DateTimeHandler.format(queryDate) + ":\n");
        boolean hasTasks = false;
        int count = 1;

        for (Task task : tasks) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getBy().toLocalDate().equals(queryDate)) {
                    list.append(count).append(". ").append(deadline).append("\n");
                    hasTasks = true;
                    count++;
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.getFrom().toLocalDate().equals(queryDate) ||
                        event.getTo().toLocalDate().equals(queryDate)) {
                    list.append(count).append(". ").append(event).append("\n");
                    hasTasks = true;
                    count++;
                }
            }
        }

        if (!hasTasks) {
            ui.showResponse("No tasks found on " + DateTimeHandler.format(queryDate) + ".");
        } else {
            ui.showResponse(list.toString());
        }
    }
}
