package nimbus.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import nimbus.exception.WrongDateTimeFormatException;
import nimbus.task.DeadlineTask;
import nimbus.task.EventTask;
import nimbus.task.Task;
import nimbus.ui.TaskList;
import nimbus.ui.Ui;


/**
 * CheckCommand class is a subclass of Command
 * It is able to check for tasks due on a particular day
 */
public class CheckCommand extends Command {
    private String userInput;
    private ArrayList<Task> tasks;

    /**
     * Creates an CheckCommand Object where taskInput is the task description
     * Obtains the arraylist from taskList
     *
     * @param userInput user input is the date user wants to see which tasks is due on that day
     * @param taskList taskList object that is passed in
     */
    public CheckCommand(String userInput, TaskList taskList) {
        super(userInput, taskList);
        this.userInput = userInput;
        this.tasks = taskList.getTaskList();
    }

    /**
     * prints out the list of tasks due on date provided
     *
     * @throws WrongDateTimeFormatException if date and time format is wrong
     */
    @Override
    public String execute() throws WrongDateTimeFormatException {
        assert tasks != null : "Task list should not be null";
        assert userInput.length() > 5 : "User input is too short to contain date";
        String date = userInput.substring(5).trim();
        LocalDate selectedDate = parseDate(date);

        String output = "";
        int counter = 1;

        for (Task task : this.tasks) {
            if (isTaskOnSelectedDate(task, selectedDate)) {
                output += stringFormatter(counter, task);
                counter++;
            }
        }
        output += ("These tasks are due on " + selectedDate + Ui.HORIZONTAL_LINE);
        return output;
    }

    /**
     * Parses the date to make sure that the date is in the right format
     *
     * @param date the user input date
     * @return LocalDate that represents user input date in correct format
     * @throws WrongDateTimeFormatException if user input date time format is wrong
     */
    private LocalDate parseDate(String date) throws WrongDateTimeFormatException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        try {
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new WrongDateTimeFormatException();
        }
    }

    /**
     * Formats the task string to be shown as a list later on
     *
     * @param counter index of current task
     * @param task current task
     * @return a formatted string for the task
     */
    private String stringFormatter(int counter, Task task) {
        return counter + ". " + task + "\n";
    }

    /**
     * Checks if the task in task list has a due date or happens on selected date
     *
     * @param task current task being checked
     * @param selectedDate user input date to check for
     * @return true if task falls on selected date, false if task do not fall on selected date
     */
    private boolean isTaskOnSelectedDate(Task task, LocalDate selectedDate) {
        if (task instanceof DeadlineTask) {
            LocalDate taskDate = ((DeadlineTask) task).getDeadline().getDateTime().toLocalDate();
            return selectedDate.equals(taskDate);
        } else if (task instanceof EventTask) {
            LocalDate taskDate = ((EventTask) task).getEventDate().getDateTime().toLocalDate();
            return selectedDate.equals(taskDate);
        }
        return false;
    }
}
