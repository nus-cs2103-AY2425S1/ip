package choaticbot.actions;

import choaticbot.exceptions.ChoaticBotException;
import choaticbot.tasks.TaskList;

/**
 * The {@code Mark} class represents an action that marks a task as completed in the task list.
 * It extends the {@link Action} class and provides an implementation to mark a task based
 * on its index.
 */
public class Mark extends Action {

    /**
     * The index of the task to be marked as completed, provided as a string.
     */
    private String details;

    /**
     * Constructs a {@code Mark} action with the specified task list and task index.
     *
     * @param taskList The task list containing the task to be marked.
     * @param details The string containing the index of the task to mark as completed.
     */
    public Mark(TaskList taskList, String details) {
        super(taskList);
        this.details = details;
    }

    /**
     * Executes the mark action. It parses the task index from the {@code details}
     * field and marks the task at that index as completed in the task list.
     *
     * @return an {@link ActionResult} containing the result of marking the task as completed
     * @throws ChoaticBotException if the task index is invalid or an error occurs during marking
     */
    @Override
    public ActionResult execute() throws ChoaticBotException {
        int index = Integer.parseInt(this.details);
        return this.taskList.markTask(index);
    }
}
