package bestie.parser;

import bestie.command.Command;
import bestie.command.ErrorCommand;
import bestie.command.PriorityCommand;
import bestie.task.Priority;

/**
 * Parse the priority command.
 */
public class PriorityParser {

    /**
     * Parses the priority command for execution.
     *
     * @param userInput User priority command.
     * @return Priority command if parse successful, Error command if errors occurs.
     */
    public Command executePriorityCommand(String userInput) {
        try {
            String priorityToFindString = userInput.split(" ")[1];
            Priority priorityToFind = Priority.valueOf(priorityToFindString.toUpperCase());
            return new PriorityCommand(priorityToFind);
        } catch (IllegalArgumentException e) {
            return new ErrorCommand("Please key in an appropriate priority, low, medium or high!"
                    + " Make sure you spelled it correctly.");
        } catch (ArrayIndexOutOfBoundsException e) {
            return new ErrorCommand("Please key in the priority of tasks you want to find!");
        }

    }
}
