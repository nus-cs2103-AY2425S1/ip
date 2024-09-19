package choaticbot.actions;

import choaticbot.exceptions.ChoaticBotException;
import choaticbot.tasks.TaskList;

/**
 * The {@code Unmark} class represents an action that unmarks a task as incomplete in the task list.
 * It extends the {@link Action} class and provides an implementation to unmark a task based
 * on its index.
 */
public class Unmark extends Action {

    /**
     * The index of the task to be unmarked as incomplete, provided as a string.
     */
    private String details;

    /**
     * Constructs an {@code Unmark} action with the specified task list and task index.
     *
     * @param taskList The task list containing the task to be unmarked.
     * @param details  The string containing the index of the task to unmark as incomplete.
     */
    public Unmark(TaskList taskList, String details) {
        super(taskList);
        this.details = details;
    }

    /**
     * Executes the unmark action. It parses the task index from the {@code details}
     * field and unmarks the task at that index as incomplete in the task list.
     *
     * @return an {@link ActionResult} containing the result of marking the task as uncompleted
     * @throws ChoaticBotException if the task index is invalid or an error occurs during unmarking
     */
    @Override
    public ActionResult execute() throws ChoaticBotException {
        int index = Integer.parseInt(this.details);
        return this.taskList.unmarkTask(index);
    }

}
