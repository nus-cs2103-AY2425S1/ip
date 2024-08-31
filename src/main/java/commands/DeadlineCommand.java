package commands;

import exceptions.ArgumentNotFoundException;
import exceptions.BottyException;
import exceptions.EmptyArgumentException;
import tasks.Deadline;
import tasks.TaskManager;

public class DeadlineCommand extends AddTaskCommand {
    @Override
    public String execute(TaskManager taskManager, ParsedInput parsedInput) throws BottyException {
        try {
            Deadline deadline = new Deadline(parsedInput.getArgument("main"), parsedInput.getArgument("by"));
            return addToTaskList(taskManager, deadline);
        } catch (ArgumentNotFoundException | EmptyArgumentException ex) {
            throw new BottyException("I am unable to add that deadline! Please provide details " +
                    "in the following format: [description] /by [deadline]");
        }
    }
}
