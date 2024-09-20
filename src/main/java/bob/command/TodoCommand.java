package bob.command;

import bob.exception.InvalidTaskException;
import bob.parser.Parser;
import bob.storage.Storage;
import bob.task.Task;
import bob.task.TaskList;
import bob.task.Todo;
import bob.ui.Ui;

/**
 * ListCommand class executes list command.
 */
public class TodoCommand extends Command {

    /**
     * Constructor to initalise TodoCommand
     *
     * @param input
     */
    public TodoCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        String input = getInput();
        try {
            Task newTodoTask = getTodoTask(input);
            taskList.updateWithNewTask(newTodoTask);
            storage.saveTaskListToStorage(taskList);
            Ui.showAddedTaskConfirmation(taskList);
            String todoTaskAddedConfirmationMessage = taskList.getAddedTaskString();
            return todoTaskAddedConfirmationMessage;
        } catch (InvalidTaskException e) {
            System.err.println(e.getMessage());
            return e.getMessage();
        }
    }

    /**
     * Returns a todo task.
     *
     * @param input Input command from user.
     * @return todo task.
     * @throws InvalidTaskException
     */
    private static Task getTodoTask(String input) throws InvalidTaskException {
        String todoDescription = Parser.parseDescriptionFromInput(input);
        Task newTodoTask = new Todo(todoDescription);
        return newTodoTask;
    }
}
