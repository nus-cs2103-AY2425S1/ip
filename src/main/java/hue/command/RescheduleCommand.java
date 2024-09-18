package hue.command;

import hue.Hue;
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
    private final String[] dateEntry;

    private String[] parts;

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
            parts = fullCommand.split(" ", 3);
            this.taskIndex = Integer.parseInt(parts[1]) - 1;
            this.dateEntry = parts[2].split(" ");
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new HueException("Please provide a valid task number and new date(s)");
        }
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws HueException, IOException {
         assert taskIndex >= 0 && taskIndex < tasks.size() : "The task index is out of range";
         Task task = tasks.get(taskIndex);

         if (task instanceof Deadline) { //accounts for both yyyy-mm-dd and MMM-dd-yyyy
             if (dateEntry.length != 1) {
                 throw new HueException("Please provide a valid date range!");
             }
             ((Deadline) task).reschedule(parts[2].trim());
         }

         if (task instanceof Event) {
             if (dateEntry.length == 2) { // using yyyy-mm-dd
                 ((Event) task).rescheduleEvent(dateEntry[0].trim(), dateEntry[1].trim());
             } else if (dateEntry.length == 4) { //using MMM-dd-yyyy
                 String[] reformattedDates = parts[2].split(" (?=\\d{1,2}/\\d{1,2}/\\d{4})");
                 ((Event) task).rescheduleEvent(reformattedDates[0].trim(), reformattedDates[1].trim());
             } else {
                 throw new HueException("Please provide a valid date range!");
             }
         }
         storage.saveTasks(tasks);
         return ui.showRescheduleSuccess(task, String.join(" to ", dateEntry));
    }



}
