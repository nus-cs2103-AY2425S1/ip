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

public class DateCommand extends Command {
    public DateCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JeffException {
        if (this.getInput().matches("task .+")) {
            String[] taskParts  = this.getInput().split(" ", 2);
            String taskPeriod = taskParts.length > 1 ? taskParts[1] : "";
            try {
                LocalDate taskDate = LocalDate.parse(taskPeriod);
                List<Task> filteredTasks = tasks.filterByDate(taskDate);

                // Check if the list is empty
                if (filteredTasks.isEmpty()) {
                    throw new JeffException("No deadlines/events on " + taskPeriod + "!");
                }

                // Convert the list of tasks to a string
                String taskListString = Parser.listToString(filteredTasks);

                ui.printText("Here are the tasks for " + taskPeriod + ":\n " + taskListString);
            } catch (DateTimeParseException e) {
                throw new JeffException("The format is wrong! It should be \"task yyyy-mm-dd\"!");
            }
        } else {
            throw new JeffException("The format is wrong! It should be \"task yyyy-mm-dd\"!");
        }

    }
}
