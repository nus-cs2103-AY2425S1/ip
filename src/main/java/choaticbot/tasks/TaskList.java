package choaticbot.tasks;

import java.util.ArrayList;

import choaticbot.actions.ActionResult;
import choaticbot.exceptions.ChoaticBotException;
import choaticbot.exceptions.WrongInputFormatException;

/**
 * Represents a list of tasks. Provides methods to manage and manipulate tasks, including adding, deleting,
 * listing, marking, unmarking, filtering, and updating tasks.
 */
public class TaskList {
    private ArrayList<Task> tasklist;

    /**
     * Constructs a new {@code TaskList} object with an empty list of tasks.
     */
    public TaskList() {
        this.tasklist = new ArrayList<>();
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param task The task to be added.
     * @return an {@link ActionResult} containing the result of adding the task
     */
    public ActionResult addTask(Task task) {
        this.tasklist.add(task);
        String message = "added: " + task + "\n"
                + "You have " + this.tasklist.size() + " tasks in the list";

        return new ActionResult(message);
    }

    /**
     * Deletes a task from the list of tasks based on the specified index.
     *
     * @param index The index of the task to be deleted (1-based index).
     * @return an {@link ActionResult} containing the result of deleting the task
     * @throws ChoaticBotException if the index is out of bounds
     */
    public ActionResult deleteTask(int index) throws ChoaticBotException {
        if (index <= 0 || index > tasklist.size()) {
            throw new ChoaticBotException("Index out of bounds, there is only " + tasklist.size() + " tasks");
        } else {
            String message1 = "deleted: " + this.tasklist.get(index - 1) + "\n";
            this.tasklist.remove(index - 1);
            String message2 = "You have " + this.tasklist.size() + " tasks in the list";

            return new ActionResult(message1 + message2);
        }
    }

    /**
     * Prints the list of tasks with their indices.
     *
     * @return an {@link ActionResult} containing the list of tasks
     */
    public ActionResult listTask() {
        String message = "";
        for (int i = 0; i < tasklist.size(); i++) {
            message = message.concat((i + 1) + ". " + this.tasklist.get(i) + "\n");
        }

        return new ActionResult(message);
    }

    /**
     * Marks a task as completed based on the specified index.
     *
     * @param index The index of the task to be marked as completed (1-based index).
     * @return an {@link ActionResult} containing the result of marking the task as completed
     * @throws ChoaticBotException if the index is out of bounds
     */
    public ActionResult markTask(int index) throws ChoaticBotException {
        if (index <= 0 || index > tasklist.size()) {
            throw new ChoaticBotException("Index out of bounds, there is only " + tasklist.size() + " tasks");
        } else {
            this.tasklist.get(index - 1).complete();
            String message = "Marked as done:\n" + this.tasklist.get(index - 1);

            return new ActionResult(message);
        }
    }

    /**
     * Marks a task as incomplete based on the specified index.
     *
     * @param index The index of the task to be marked as incomplete (1-based index).
     * @return an {@link ActionResult} containing the result of marking the task as incomplete
     * @throws ChoaticBotException if the index is out of bounds
     */
    public ActionResult unmarkTask(int index) throws ChoaticBotException {
        if (index <= 0 || index > tasklist.size()) {
            throw new ChoaticBotException("Index out of bounds, there is only " + tasklist.size() + " tasks");
        } else {
            this.tasklist.get(index - 1).uncomplete();
            String message = "Marked as undone:\n" + this.tasklist.get(index - 1);

            return new ActionResult(message);
        }
    }

    /**
     * Filters and prints tasks that contain the specified word in their name.
     *
     * @param word The word to search for in the task names.
     * @return an {@link ActionResult} containing the filtered tasks
     */
    public ActionResult filterByWord(String word) {
        String message = "";

        for (int i = 0; i < tasklist.size(); i++) {
            if (tasklist.get(i).containWord(word)) {
                message = message.concat((i + 1) + ". " + this.tasklist.get(i) + "\n");
            }
        }

        return new ActionResult(message);
    }

    /**
     * Returns the list of tasks.
     *
     * @return An {@code ArrayList} containing all tasks in the list.
     */
    public ArrayList<Task> getTasks() {
        return this.tasklist;
    }

    /**
     * Updates the task at the specified index with the new details provided.
     * This method delegates the task update logic to the individual task's {@code update} method.
     *
     * @param index The index of the task to be updated (1-based index).
     * @param details The new details to update the task with. The format of the details
     *                depends on the type of task (e.g., deadline tasks need a date).
     * @return an {@link ActionResult} containing the result of updating the task
     * @throws WrongInputFormatException If the details provided do not match the expected format for the task type
     */
    public ActionResult updateTask(int index, String details) throws WrongInputFormatException {
        this.tasklist.get(index - 1).update(details);
        String message = "Updated task " + index + " to: " + this.tasklist.get(index - 1);

        return new ActionResult(message);
    }
}
