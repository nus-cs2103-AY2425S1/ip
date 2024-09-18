package bestie.parser;

import bestie.command.Command;
import bestie.command.DeleteCommand;
import bestie.command.ErrorCommand;

import java.lang.reflect.InvocationTargetException;

public class DeleteParser {

    public Command executeDeleteCommand(String userInput) {
        try {
            String[] parts = userInput.split(" ");
            String commandWord = parts[0];
            int index = Integer.parseInt(parts[1]) - 1;
            return new DeleteCommand(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            return new ErrorCommand("You need to key in the index of the task you are deleting!!");
        }

    }
}
