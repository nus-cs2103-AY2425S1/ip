package commands;

import tasks.TaskManager;
import exceptions.ArgumentNotFoundException;
import exceptions.BottyException;
import exceptions.EmptyArgumentException;
import tasks.Event;

public class EventCommand extends AddTaskCommand {
    @Override
    public String execute(TaskManager taskManager, ParsedInput parsedInput) throws BottyException {
        try {
            Event event = new Event(parsedInput.getArgument("main"),
                    parsedInput.getArgument("from"),
                    parsedInput.getArgument("to"));
            return addToTaskList(taskManager, event);
        } catch (ArgumentNotFoundException | EmptyArgumentException ex) {
            throw new BottyException("I am unable to add that event! Please provide details in " +
                    "the following format: [description] /from [start] /to [end]");
        }
    }
}
