package cookie.command;

import cookie.exception.CookieException;
import cookie.storage.Storage;
import cookie.task.TaskList;
import cookie.task.ToDo;
import cookie.ui.Ui;


public class ToDoCommand extends Command {
    private final String description;

    /**
     * Constructs a new {@code ToDoCommand} with the specified task description.
     *
     * @param description the description of the to-do task
     */
    public ToDoCommand(String description) {
        this.description = description;
    }
    /**
     * Executes the find command, which searches for tasks in the task list
     * that match specific criteria.
     *
     * @param taskList The list of tasks where the search will be performed.
     * @param ui The user interface component to display results or prompt interactions.
     * @param storage  The storage system to retrieve or save task data.
     * @return A string containing the result of the find command, usually a message
     *         displaying the matching tasks or indicating no matches found.
     * @throws CookieException  If there is an issue executing the command, such as
     *         invalid or expired cookies, or other related problems.
     */
    @Override
    public String executeCommand(TaskList taskList, Ui ui, Storage storage) throws CookieException {
        if (description.isEmpty()) {
            throw new CookieException("Please enter a task for you to do.");
        }

        ToDo newTodoTask = new ToDo(description);
        taskList.addTask(newTodoTask);

        String response = ui.printLatestTask(newTodoTask);
        response = response + ui.printNoTasksInList(taskList.getTaskArrayList());
        return response;
    }
}
