package Commands;

import Tasks.Task;
import WindeBot.Reminder;
import WindeBot.Ui;

import Exceptions.EmptyDescriptionException;
import Exceptions.TooManyParametersException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * The TaskDateCommand class represents a command to list tasks on a specific date.
 * This command searches through the list of tasks and displays any tasks that match the specified date.
 */

public class TaskDateCommand extends Command {

    /**
     * Executes the TaskDateCommand, displaying tasks that occur on the specified date.
     *
     * @param input The user input string containing the date to search for tasks.
     * @param reminder The Reminder object that manages the task list.
     * @param ui The Ui object used to interact with the user.
     * @return true if the command was executed successfully.
     * @throws EmptyDescriptionException If no date is provided in the input.
     * @throws TooManyParametersException If too many parameters are provided in the input.
     */

    public boolean execute(String input, Reminder reminder, Ui ui) throws EmptyDescriptionException, TooManyParametersException {
        String[] command = input.split(" ");
        if (command.length == 2) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(command[1], formatter);
            ui.print("These are the tasks you have for " + date.toString());
            ArrayList<Task> taskList = reminder.getTasksOnDate(date);
            if (taskList != null) {
                for (Task task : taskList) {
                    ui.print(task.toString());
                }
            } else {
                ui.print("Hurray! No tasks on: " + date.toString());
            }
        } else if (command.length < 2) {
            throw new EmptyDescriptionException("I NEED TO KNOW THE DATE!");
        } else {
            throw new TooManyParametersException("ONE AT A TIME!");
        }
        return true;
    }
}