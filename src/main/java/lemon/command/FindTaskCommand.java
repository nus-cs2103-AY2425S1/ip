package lemon.command;

import lemon.Lemon;
import lemon.TaskList;
import lemon.exception.InvalidFormatException;

/**
 * Represent the {@link CommandWithInput} to find a task
 * @author He Yiheng
 */
public class FindTaskCommand extends CommandWithInput {
    /**
     * Constructor for FindTaskCommand
     * @param ct stores the enum {@link CommandType}
     * @param input input String that needs to be processed before further execution
     */
    public FindTaskCommand(CommandType ct, String input) {
        super(ct, input);
    }

    @Override
    public void run(Lemon lemonInstance) {
        try {
            String[] findInput = processInput();
            TaskList matchingTasks = lemonInstance.getTasks().findTasks(findInput[1]);
            if (matchingTasks.size() > 0) {
                lemonInstance.getUi().printMatchingTaskMsg(matchingTasks.toString());
            } else {
                lemonInstance.getUi().printNoMatchingMsg();
            }
        } catch (InvalidFormatException e) {
            lemonInstance.getUi().printException(e);
        }
    }

    @Override
    public String[] processInput() throws InvalidFormatException {
        String[] splitInput = input.split(" ", 2);
        if (splitInput.length < 2) {
            throw new InvalidFormatException(" Missing description/date of the deadline task to be added!");
        } else if (splitInput[1].isEmpty() || splitInput[1].equals(" ")) {
            throw new InvalidFormatException(" Please specify what you need me to find ^-^");
        }

        return splitInput;
    }
}
