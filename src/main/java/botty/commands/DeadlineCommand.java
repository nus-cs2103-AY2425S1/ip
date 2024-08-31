package botty.commands;

import botty.exceptions.ArgumentNotFoundException;
import botty.exceptions.BottyException;
import botty.exceptions.EmptyArgumentException;
import botty.tasks.Deadline;
import botty.tasks.TaskManager;

/**
 * Defines the behaviour of the create deadline command
 */
public class DeadlineCommand extends AddTaskCommand {
    /**
     * Executes the deadline command, adding a deadline to the given task manager with
     * the given arguments
     * @param taskManager
     * @param parsedInput
     * @return success message
     * @throws BottyException if given input is invalid
     */
    @Override
    public String execute(TaskManager taskManager, ParsedInput parsedInput) throws BottyException {
        try {
            Deadline deadline = new Deadline(parsedInput.getArgument("main"), parsedInput.getArgument("by"));
            return addToTaskList(taskManager, deadline);
        } catch (ArgumentNotFoundException | EmptyArgumentException ex) {
            throw new BottyException("I am unable to add that deadline! Please provide details "
                    + "in the following format: [description] /by [deadline]");
        }
    }
    @Override
    public boolean changesTaskList() {
        return true;
    }
}
