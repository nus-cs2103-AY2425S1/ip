package choaticbot.actions;

import choaticbot.exceptions.ChoaticBotException;
import choaticbot.tasks.TaskList;

/**
 * Represents an action to update the details of an existing task in the task list.
 * The task is identified by its index in the list, and the details to update are provided.
 */
public class Update extends Action {

    private String details;

    /**
     * Constructs an Update action with the given task list and update details.
     *
     * @param taskList The task list where the task resides.
     * @param details The details containing the task index and the new details to update the task.
     */
    public Update(TaskList taskList, String details) {
        super(taskList);
        this.details = details;
    }

    /**
     * Executes the update action. Splits the details into the task index and the new task details,
     * and updates the corresponding task in the task list.
     *
     * @return an {@link ActionResult} containing the result of updating the task
     * @throws ChoaticBotException if an error occurs while updating the task (e.g., invalid index or malformed input)
     */
    @Override
    public ActionResult execute() throws ChoaticBotException {
        String[] parts = this.details.split(" ", 2);
        int index = Integer.parseInt(parts[0]);
        String updateDetails = parts[1];
        return this.taskList.updateTask(index, updateDetails);
    }

}
