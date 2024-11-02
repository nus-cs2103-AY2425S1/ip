package botty.commands;

import botty.exceptions.BottyException;
import botty.exceptions.EmptyArgumentException;
import botty.tasks.TaskManager;
import botty.tasks.Todo;
import botty.tasks.TodoData;

/**
 * Defines the behaviour of the Todo command
 */
public class TodoCommand extends AddTaskCommand {
    /**
     * Executes the todo command, adding a todo to the given task manager with
     * the given arguments
     * @param taskManager
     * @param parsedInput
     * @return success message
     * @throws BottyException if given input is invalid
     */
    @Override
    public String execute(TaskManager taskManager, ParsedInput parsedInput) throws BottyException {
        try {
            parsedInput.changeMainFlag("desc");
            Todo todo = new Todo(new TodoData(parsedInput));
            return addToTaskList(taskManager, todo);
        } catch (EmptyArgumentException ex) {
            throw new BottyException("I am unable to add that todo! Please ensure that the description is not blank");
        }
    }
    /**
     * Returns true as the command will edit the task list
     */
    @Override
    public boolean changesTaskList() {
        return true;
    }
}
