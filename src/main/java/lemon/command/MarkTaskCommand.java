package lemon.command;

import lemon.Lemon;
import lemon.exception.InvalidFormatException;
import lemon.task.Task;

/**
 * Represent the {@link CommandWithInput} to mark a task
 * @author He Yiheng
 */
public class MarkTaskCommand extends CommandWithInput {
    /**
     * Constructor for MarkTaskCommand
     * @param ct stores the enum {@link CommandType} for troubleshooting
     * @param input input String that needs to be processed before further execution
     */
    public MarkTaskCommand(CommandType ct, String input) {
        super(ct, input);
    }

    @Override
    public void run(Lemon lemonInstance) {
        try {
            String[] processedInput = processInput();
            int taskIndex = Integer.parseInt(processedInput[1]);
            if (taskIndex > lemonInstance.getTasks().size() || taskIndex <= 0) {
                throw new InvalidFormatException(" OOPS!!! Please select a valid task");
            }

            Task task = lemonInstance.getTasks().get(taskIndex);
            task.markDone();
            lemonInstance.getUi().printMarkMsg(task.toString());
        } catch (NumberFormatException e) {
            lemonInstance.getUi().printException(" Please select the task using its index with no whitespace instead");
        } catch (InvalidFormatException e) {
            lemonInstance.getUi().printException(e);
        }
    }

    @Override
    public String[] processInput() throws InvalidFormatException {
        String[] splitInput = input.split(" ", 2);
        if (splitInput.length < 2) {
            throw new InvalidFormatException(" Missing index of task to be marked!");
        }
        return splitInput;
    }
}
