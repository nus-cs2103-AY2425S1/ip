package cookie.command;

import cookie.exception.CookieException;
import cookie.storage.Storage;
import cookie.task.Deadline;
import cookie.task.TaskList;
import cookie.ui.Ui;

public class DeadlineCommand extends Command {
    private String description;
    private String by;

    /**
     * Constructs a new {@code DeadlineCommand} with the given task description and deadline.
     *
     * @param description the description of the deadline task
     * @param by the due date or time by which the task needs to be completed
     */
    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }
    /**
     * Executes the {@code DeadlineCommand} by creating a new {@code Deadline} task with the provided description
     * and deadline, adding it to the task list, and displaying appropriate responses to the user.
     *
     * @param taskList the list of tasks to which the new deadline task will be added
     * @param ui the user interface object used to print responses
     * @param storage the storage object for saving or retrieving tasks (not used in this method)
     * @return a string representing the response printed by the UI, including details of the added task
     *         and the current task count
     * @throws CookieException if either the task description or the deadline is empty
     */
    @Override
    public String executeCommand(TaskList taskList, Ui ui, Storage storage) throws CookieException {
        if (description.isEmpty() || by.isEmpty()) {
            throw new CookieException("Deadlines must include a task todo and a due date \n"
                    + "[task] /by [deadline]");
        }
        Deadline newDeadlineTask = new Deadline(this.description, this.by);
        taskList.addTask(newDeadlineTask);

        String response = ui.printLatestTask(newDeadlineTask);
        response = response + ui.printNoTasksInList(taskList.getTaskArrayList());

        return response;
    }
}
