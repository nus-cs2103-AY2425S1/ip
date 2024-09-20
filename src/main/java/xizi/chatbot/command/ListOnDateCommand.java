package xizi.chatbot.command;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import xizi.chatbot.Parser;
import xizi.chatbot.Storage;
import xizi.chatbot.Ui;
import xizi.chatbot.XiziException;
import xizi.chatbot.task.Deadline;
import xizi.chatbot.task.Event;
import xizi.chatbot.task.Task;
import xizi.chatbot.task.TaskList;


/**
 * Represents a command to list all tasks that are scheduled on a specific date and time.
 * This command filters tasks to show only those that are relevant to the given date.
 */
public class ListOnDateCommand implements Command {
    private final LocalDateTime date;

    /**
     * Constructs a ListOnDateCommand based on the user input.
     * Parses the input to extract the date and time for which tasks should be listed.
     *
     * @param userInput The user input containing the date and time.
     * @throws XiziException If the input format is invalid or the date and time cannot be parsed.
     */
    public ListOnDateCommand(String userInput) throws XiziException {
        Matcher matcher = CommandType.LIST_ON.matcher(userInput);
        if (matcher.matches()) {
            String dateTimeStr = matcher.group(1);
            this.date = Parser.parseDateTime(dateTimeStr);
        } else {
            throw new XiziException("Invalid list on command format. Use: list on <date>");
        }
    }

    /**
     * Executes the list on command, displaying all tasks that are scheduled on the specified date.
     * This includes events that overlap with the date and deadlines that are due on that date.
     *
     * @param actions The task list containing all tasks.
     * @param storage The storage handler (not used in this command).
     * @param ui      The user interface for interacting with the user.
     */
    @Override
    public void execute(TaskList actions, Storage storage, Ui ui) {
        ui.showLine();
        try {
            List<Task> tasksOnDate = getTasksOnDate(actions);
            printTasksOnDate(ui, tasksOnDate);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ui.showLine();
    }

    /**
     * Retrieves the tasks scheduled on the specific date, including overlapping events and deadlines due on that date.
     *
     * @param actions The task list containing all tasks.
     * @return A list of tasks scheduled on the specific date.
     */
    private List<Task> getTasksOnDate(TaskList actions) {
        List<Task> tasksOnDate = new ArrayList<>();
        for (Task task : actions.getItems()) {
            if (task instanceof Event) {
                if (isConcurrent((Event) task)) {
                    tasksOnDate.add(task);
                }
            } else if (task instanceof Deadline) {
                if (isDeadlineOnDate((Deadline) task)) {
                    tasksOnDate.add(task);
                }
            }
        }
        return tasksOnDate;
    }

    /**
     * Checks if a deadline is due on the specified date.
     *
     * @param deadline The deadline task to check.
     * @return true if the deadline is on the specified date, false otherwise.
     */
    private boolean isDeadlineOnDate(Deadline deadline) {
        LocalDateTime deadlineDate = deadline.getDdl();
        return deadlineDate.equals(date); // Compare date (ignoring seconds and milliseconds)
    }

    /**
     * Prints the tasks found on the specific date to the user.
     *
     * @param ui          The user interface for displaying messages.
     * @param tasksOnDate The list of tasks scheduled on the specific date.
     */
    private void printTasksOnDate(Ui ui, List<Task> tasksOnDate) {
        if (tasksOnDate.isEmpty()) {
            ui.printMessage("No tasks found on this date.");
            return;
        }
        ui.printMessage("Here are the tasks on the particular day in your list:");
        for (Task task : tasksOnDate) {
            ui.printMessage(task.toString());
        }
    }



    private boolean isConcurrent(Event event) {
        //Start date requirements
        boolean isStartDateBefore = event.getFrom().isBefore(date);
        boolean isStartDateEqual = event.getFrom().equals(date);

        //End date requirement
        boolean isEndDateAfter = event.getTo().isAfter(date);
        boolean isEndDateEqual = event.getTo().equals(date);

        return (isStartDateBefore || isStartDateEqual)
                && (isEndDateAfter || isEndDateEqual);
    }
}


