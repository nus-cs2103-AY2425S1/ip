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
     * @throws WrongDateTimeFormatException
     */
    @Override
    public String execute() throws WrongDateTimeFormatException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        String date = userInput.substring(5).trim();
        try {
            LocalDate selectedDate = LocalDate.parse(date, formatter);
            String output = "";
            int counter = 1;

            for (Task task : this.tasks) {
                if (task instanceof DeadlineTask) {
                    DeadlineTask deadlineTask = (DeadlineTask) task;
                    LocalDate taskDate = deadlineTask.getDeadline().getDateTime().toLocalDate();
                    if (selectedDate.equals(taskDate)) {
                        output += counter + ". " + deadlineTask + "\n";
                        counter++;
                    }
                } else if (task instanceof EventTask) {
                    EventTask eventTask = (EventTask) task;
                    LocalDate taskDate = eventTask.getEventDate().getDateTime().toLocalDate();
                    if (selectedDate.equals(taskDate)) {
                        output += counter + ". " + eventTask + "\n";
                        counter++;
                    }
                }
            }
            output += ("These tasks are due on "
                    + selectedDate.toString() + Ui.HORIZONTAL_LINE);
            return output;
        } catch (DateTimeParseException e) {
            throw new WrongDateTimeFormatException();
        }
    }
}
