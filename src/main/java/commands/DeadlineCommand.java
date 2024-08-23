package commands;

import botty.ParsedInput;
import botty.TaskManager;
import exceptions.ArgumentNotFoundException;
import exceptions.BottyException;
import exceptions.EmptyArgumentException;
import tasks.Deadline;

public class DeadlineCommand extends AddTaskCommand {
    public DeadlineCommand(TaskManager taskManager) {
        super(taskManager);
    }
    @Override
    public String execute(ParsedInput parsedInput) throws BottyException {
        try {
            Deadline deadline = new Deadline(parsedInput.getArgument("main"), parsedInput.getArgument("by"));
            return addToTaskList(deadline);
        } catch (ArgumentNotFoundException | EmptyArgumentException ex) {
            throw new BottyException("I am unable to add that deadline! Please provide details " +
                    "in the following format: [description] /by [deadline]");
        }
    }
}
