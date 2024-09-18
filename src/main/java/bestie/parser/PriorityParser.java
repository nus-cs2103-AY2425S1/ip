package bestie.parser;

import bestie.command.Command;
import bestie.command.ErrorCommand;
import bestie.command.PriorityCommand;
import bestie.task.Priority;

import java.lang.reflect.InvocationTargetException;

public class PriorityParser {

    public Command executePriorityCommand(String userInput) {
        try {
            String priorityToFindString = userInput.split(" ")[1];
            Priority priorityToFind = Priority.valueOf(priorityToFindString.toUpperCase());
            return new PriorityCommand(priorityToFind);
        } catch (IllegalArgumentException e) {
            return new ErrorCommand("Please key in an appropriate priority, low, medium or high!" +
                    " Make sure you spelled it correctly.");
        } catch (ArrayIndexOutOfBoundsException e) {
            return new ErrorCommand("Please key in the priority of tasks you want to find!");
        }

    }
}
