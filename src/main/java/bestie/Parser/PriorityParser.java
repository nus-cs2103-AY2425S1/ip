package bestie.Parser;

import bestie.command.Command;
import bestie.command.PriorityCommand;
import bestie.task.Priority;

public class PriorityParser {
    public Command executePriorityCommand(String userInput) {
        String priorityToFindString = userInput.split(" ")[1];
        Priority priorityToFind = Priority.valueOf(priorityToFindString.toUpperCase());
        return new PriorityCommand(priorityToFind);
    }
}
