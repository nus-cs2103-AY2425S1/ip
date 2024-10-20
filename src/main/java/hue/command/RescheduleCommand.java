package hue.command;

import hue.HueException;
import hue.ui.Ui;
import hue.storage.Storage;
import hue.task.Task;
import hue.task.Deadline;
import hue.task.Event;
import hue.task.TaskList;

import java.io.IOException;

public class RescheduleCommand extends Command {
    private final int taskIndex;
    private final String[] parts;
    /**
     * Constructs a {@code RescheduleCommand} by parsing the task index and new date(s) from the given input.
     * The expected format is:
     * <pre>
     * reschedule [taskIndex] [newDate]
     * </pre>
     * For example:
     * <pre>
     * reschedule 2 2024-09-01
     * </pre>
     * or, for events with two dates:
     * <pre>
     * reschedule 3 2024-09-01 2024-09-05
     * </pre>
     *
     * @param fullCommand The full command string input by the user, including the task index and the new date(s).
     * @throws HueException If the input is invalid, such as missing or incorrect task index or date format.
     */
    public RescheduleCommand(String fullCommand) throws HueException {
        try {

            this.parts = fullCommand.split("/by | /from | /to");
            String[] beforeBy = parts[0].trim().split(" ");
            taskIndex = Integer.parseInt(beforeBy[1]) - 1;
            if (parts[1].trim().isEmpty()) {
                throw new HueException("Please provide a valid task number and new date(s)");
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new HueException("Please provide a valid task number and new date(s)");
        }
    }


    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws HueException, IOException {
         if (taskIndex < 0 || taskIndex >= tasks.size()) {
             throw new HueException("Please provide a valid task number to reschedule.");
         }
         Task task = tasks.get(taskIndex);

         if (task instanceof Deadline) {
            rescheduleDeadline((Deadline) task);
         }

         if (task instanceof Event) {
            rescheduleEvent((Event) task);
         }
         storage.saveTasks(tasks);
         return ui.showRescheduleSuccess(task, String.join(" to ", parts));
    }

    public void rescheduleDeadline(Deadline deadline) throws HueException {
        deadline.reschedule(parts[1].trim());
    }

    public void rescheduleEvent(Event event) throws HueException{
        String[] eventDate = parts;
        String from = eventDate[1].trim();
        String to = eventDate[2].trim();
        event.rescheduleEvent(from, to);
    }



}
