package bestie.parser;

import java.time.format.DateTimeParseException;

import bestie.command.AddCommand;
import bestie.command.Command;
import bestie.command.ErrorCommand;
import bestie.task.Deadline;
import bestie.task.Priority;
/**
 * Creates an instance of the parser to parse a deadline command.
 */
public class DeadlineParser {


    /**
     * Executes the deadline command by parsing it and calling the add command to add a new
     * deadline task.
     *
     * @param userInput User command input.
     * @return Add command to add task, else an Error command if an error occurs.
     */
    public Command executeDeadlineCommand(String userInput) {
        try {
            String[] partsOfDeadline = userInput.split(" /");
            String description = partsOfDeadline[0].substring(9).trim();
            String deadline = partsOfDeadline[1].substring(3).trim();
            String priorityString = partsOfDeadline[2].substring(8).trim().toUpperCase();
            Priority priority = Priority.valueOf(priorityString);
            return new AddCommand(new Deadline(description, deadline, priority));
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
            String errorMessage = "You did not input the deadline in a valid format."
                    + " Please follow the format \"deadline (name of task) /by (deadline) "
                    + "/priority (high/medium/low)\".\n Please stick to the correct format: YYYY-MM-DD HHMM "
                    + "for the deadline";
            return new ErrorCommand(errorMessage);
        } catch (DateTimeParseException e) {
            String errorMessage = "You did not input the date and time in the correct format, or it does not exist."
                    + " Please stick to the correct format: YYYY-MM-DD HHMM";
            return new ErrorCommand(errorMessage);
        } catch (IllegalArgumentException e) {
            return new ErrorCommand("Invalid priority :(. Please specify as 'high', 'medium' or 'low'.");
        }
    }
}
