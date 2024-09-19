
package parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import exceptions.ErrorMessages;
import tasklist.TaskList;
import tasks.Task;

/**
 * OnCommand class is a command class that lists all the tasks on a specific date.
 */
public class OnCommand implements Command {
    @Override
    public String execute(String input, List<Task> items) {
        try {
            LocalDate localDate = LocalDate.parse(input.substring(2).trim());
            return TaskList.printTasksOnRelevantDate(localDate, items);
        } catch (DateTimeParseException e) {
            System.out.println(ErrorMessages.INCORRECT_FORMAT_FOR_ON_COMMAND);
            return ErrorMessages.INCORRECT_FORMAT_FOR_ON_COMMAND;
        }
    }
}
