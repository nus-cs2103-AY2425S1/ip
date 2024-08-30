package jeff.command;

import jeff.exception.JeffException;
import jeff.parser.Parser;
import jeff.storage.Storage;
import jeff.task.Task;
import jeff.task.TaskList;
import jeff.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Represents a "Check what task is on a certain date" command.
 */
public class DateCommand extends Command {
    /**
     * Constructor for DateCommand Class.
     * Stores the user's input.
     *
     * @param input User's input.
     */
    public DateCommand(String input) {
        super(input);
    }

    /**
     * Filters the task list based on a date input, then prints out the tasks on that date.
     *
     * @param tasks Task list.
     * @param ui UI to print statements.
     * @param storage Place to get and write the task list to the tasks text file.
     * @throws JeffException if the user's input is in the wrong format.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JeffException {
        if (this.getInput().matches("task .+")) {
            // Split the user input to get the date specified by the user
            String[] taskParts  = this.getInput().split(" ", 2);
            String taskPeriod = taskParts.length > 1 ? taskParts[1] : "";

            try {
                LocalDate taskDate = LocalDate.parse(taskPeriod);

                // Filter the task list by the given date
                List<Task> filteredTasks = tasks.filterByDate(taskDate);

                // Check if the filtered task list is empty
                if (filteredTasks.isEmpty()) {
                    throw new JeffException("No deadlines/events on " + taskPeriod + "!");
                }

                // Convert the list of tasks to a string
                String taskListString = Parser.listToString(filteredTasks);

                // Print out the filtered task list
                ui.printText("Here are the tasks for " + taskPeriod + ":\n " + taskListString);
            } catch (DateTimeParseException e) {
                throw new JeffException("The format is wrong! It should be \"task yyyy-mm-dd\"!");
            }
        } else {
            throw new JeffException("The format is wrong! It should be \"task yyyy-mm-dd\"!");
        }
    }
}
