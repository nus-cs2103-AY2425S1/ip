package jeff.command;

import jeff.exception.JeffException;
import jeff.storage.Storage;
import jeff.task.Task;
import jeff.task.TaskList;
import jeff.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class DateCommand extends Command {
    public DateCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JeffException {
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

            // Initialise a StringBuilder
            StringBuilder listString = new StringBuilder();

            // Loop through the inputList and add it to the StringBuilder
            for (int i = 0; i < filteredTasks.size(); i++) {
                listString.append(Integer.toString(i + 1)).append(".").append(filteredTasks.get(i).toString());

                // Only add a new line when it is not the last task in the list
                if (i != filteredTasks.size() - 1) {
                    listString.append("\n ");
                }

            }

            // Print out the statement
            ui.printText(listString.toString());

        } catch (DateTimeParseException e) {
            throw new JeffException("The format is wrong! " + "It should be \"task yyyy-mm-dd\"!");
        }
    }
}
