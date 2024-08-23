package commands;

import tasks.TaskManager;
import exceptions.ArgumentNotFoundException;
import exceptions.BottyException;
import exceptions.EmptyArgumentException;
import tasks.Todo;

public class TodoCommand extends AddTaskCommand {
    public TodoCommand(TaskManager taskManager) {
        super(taskManager);
    }

    @Override
    public String execute(ParsedInput parsedInput) throws BottyException {
        try {
            Todo todo = new Todo(parsedInput.getArgument("main"));
            return addToTaskList(todo);
        } catch (ArgumentNotFoundException | EmptyArgumentException ex) {
            throw new BottyException("I am unable to add that todo! Please ensure that the description is not blank");
        }
    }
}
