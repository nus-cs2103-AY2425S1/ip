package lemon.command;

import lemon.Lemon;
import lemon.exception.InvalidFormatException;
import lemon.task.Task;
import lemon.task.Todo;

/**
 * Represent the {@link CommandWithInput} for adding a to do task
 * @author He Yiheng
 */
public class AddTodoCommand extends CommandWithInput {
    /**
     * Constructor for AddTodoCommand
     * @param ct stores the enum {@link CommandType}
     * @param input input String that needs to be processed before further execution
     */
    public AddTodoCommand(CommandType ct, String input) {
        super(ct, input);
    }

    @Override
    public void run(Lemon lemonInstance) {
        try {
            String[] processedInput = processInput();
            Task todo = new Todo(processedInput[1], false);
            lemonInstance.getTasks().addNewTask(todo);
            lemonInstance.getUi().printAddTaskMsg(todo.toString(), lemonInstance.getTasks().size());
        } catch (InvalidFormatException e) {
            lemonInstance.getUi().printException(e);
        }
    }

    @Override
    public String[] processInput() throws InvalidFormatException {
        String[] splitInput = input.split(" ", 2);
        if (splitInput.length < 2) {
            throw new InvalidFormatException(" Missing description of the todo task to be added!");
        }
        return splitInput;
    }
}
