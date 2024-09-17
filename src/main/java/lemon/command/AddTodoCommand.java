package lemon.command;

import lemon.Lemon;
import lemon.exception.InvalidFormatException;
import lemon.task.Task;
import lemon.task.Todo;

public class AddTodoCommand extends CommandWithInput {
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
