package jeff.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import jeff.exception.JeffException;
import jeff.parser.Parser;
import jeff.storage.Storage;
import jeff.task.Task;
import jeff.task.TaskList;

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
     * Returns the string representation of the response by the chatbot Jeff when the user searches for tasks on a
     * specific date by filtering the task list based on the date input.
     *
     * @param tasks Task list.
     * @param storage Place to get and write the task list to the tasks text file.
     * @return String representation of the response.
     * @throws JeffException if the user's input is in the wrong format.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws JeffException {
        assert tasks != null : "Task list should not be null";
        
        if (this.getInput().matches("task .+")) {
            // Split the user input to get the date specified by the user
            String[] taskParts = this.getInput().split(" ", 2);
            assert taskParts.length == 2 : "Input should be split into two parts";

            String taskPeriod = taskParts.length > 1 ? taskParts[1] : "";
            assert !taskPeriod.isEmpty() : "Task period should not be empty";

            try {
                LocalDate taskDate = LocalDate.parse(taskPeriod);
                assert taskDate != null : "Task date cannot be null";

                // Filter the task list by the given date
                List<Task> filteredTasks = tasks.filterByDate(taskDate);
                assert filteredTasks != null : "Filtered tasks list cannot be null";

                // Check if the filtered task list is empty
                if (filteredTasks.isEmpty()) {
                    throw new JeffException("No deadlines/events on " + taskPeriod + "!");
                }

                // Convert the list of tasks to a string
                String taskListString = Parser.listToString(filteredTasks);

                // Return the response
                return Parser.prettyText("Here are the tasks for " + taskPeriod + ":\n" + taskListString);
            } catch (DateTimeParseException e) {
                throw new JeffException("The format is wrong! It should be \"task yyyy-mm-dd\"!");
            }
        } else {
            throw new JeffException("The format is wrong! It should be \"task yyyy-mm-dd\"!");
        }
    }
}
