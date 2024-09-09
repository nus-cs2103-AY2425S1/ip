package ned.commands;

import ned.Storage;
import ned.TaskList;
import ned.exceptions.MissingTaskDescriptionException;
import ned.exceptions.NedException;
import ned.Ui;
import ned.tasks.Task;
import ned.tasks.ToDo;

/**
 * Represents the todo command, which when executed, adds a new todo task to the list of tasks.
 */
public class AddToDoCommand implements Command {
    private static final String TODO_MISSING_TASK_DESCRIPTION_ERROR_MESSAGE = "M'lord, you cannot create a todo task with no description";
    private final String REGEX = "^todo.*";

    /**
     * This method creates a new ToDo object and then adds it to the taskList instance belonging to the Ned instance
     * . It Will throw NedException if the command is incomplete, be it missing a description, a /from timing or a
     * /to timing.
     *
     * @param taskList        The TaskList instance associated with the Ned instance, used to store the list of tasks
     *                        and to modify them
     * @param uiInstance      The Ui instance associated with the Ned instance, used to display user output, and take in
     *                        user input
     * @param storageInstance The storage instance associated with the Ned instance, used to load in saved tasks
     *                        from the cache file as well as to modify the cache file when the list of tasks are
     *                        changed
     * @param userInput       The string of user input, is parsed for relevant information needed to execute commands
     * @throws NedException Thrown if a task description is not provided
     */
    @Override
    public void execute(TaskList taskList, Ui uiInstance, Storage storageInstance, String userInput)
            throws NedException {
        String[] parsedInputs = userInput.split("todo", 2);
        if (parsedInputs[1].strip().isBlank()) {
            throw new MissingTaskDescriptionException(TODO_MISSING_TASK_DESCRIPTION_ERROR_MESSAGE
                    + uiInstance.getCommandMessage());
        }
        Task newTask = ToDo.createToDo(parsedInputs[1].strip(), false);
        taskList.addTask(newTask, uiInstance);
        storageInstance.save(taskList);
    }

    /**
     * Returns the regex expression used to identify the event command
     * Used in Parser class to find the associated command
     * @return The regex pattern associated with this command
     */
    @Override
    public String getRegex() {
        return this.REGEX;
    }
}
