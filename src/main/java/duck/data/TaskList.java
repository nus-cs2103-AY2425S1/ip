package duck.data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

import duck.common.Message;
import duck.data.exception.DuckException;
import duck.data.task.Deadline;
import duck.data.task.Event;
import duck.data.task.Task;
import duck.data.task.ToDo;
import duck.storage.Storage;



/**
 * Represents a list of tasks in the Duck application.
 * This class extends {@link ArrayList} and provides methods for adding and deleting tasks,
 * as well as interacting with the storage system to persist changes.
 */
public class TaskList extends ArrayList<Task> {

    private static final String MESSAGE_ADD_TASK = "Got it. I've added this task:\n";
    private static final String MESSAGE_TASK_LIST_SIZE = " tasks in the list now.\n";
    private static final String MESSAGE_REMOVE_TASK = "Noted. I've removed this task:\n";

    /**
     * Adds a task to the task list and updates the storage system.
     *
     * @param task The task to be added to the list.
     * @param storage The storage system used to save the task.
     * @throws DuckException If an error occurs while interacting with the storage system.
     */
    public void addTask(Task task, Storage storage) throws DuckException {
        assert task != null : "Task to be added is null";
        assert storage != null : "Storage not yet initialized";

        this.add(task);
        storage.appendTask(task);
        System.out.println(MESSAGE_ADD_TASK + task);
        System.out.println(this.size() + MESSAGE_TASK_LIST_SIZE);
    }

    /**
     * Marks a task as done or incomplete and updates the storage system.
     *
     * @param taskIndex The index of the task to be marked as done.
     * @param isDone A boolean indicating whether the task should be marked as done or incomplete.
     * @throws DuckException If the task index is out of bounds.
     */
    public void updateTaskStatus(int taskIndex, boolean isDone) throws DuckException {

        try {
            if (isDone) {
                this.get(taskIndex).markAsDone();
            } else {
                this.get(taskIndex).markAsIncomplete();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DuckException(Message.INDEX_OUT_OF_BOUNDS);
        }


    }
    /**
     * Deletes a task from the task list by its index and updates the storage system.
     *
     * @param index The index of the task to be deleted.
     * @param storage The storage system used to update the task list.
     * @throws DuckException If the task index is out of bounds or
     *     an error occurs while interacting with the storage system.
     */
    public void deleteTask(int index, Storage storage) throws DuckException {
        try {
            System.out.println(MESSAGE_REMOVE_TASK + this.get(index));
            this.remove(index);
            storage.writeTasks(this);
            System.out.println(this.size() + MESSAGE_TASK_LIST_SIZE);
        } catch (IndexOutOfBoundsException e) {
            throw new DuckException(Message.INDEX_OUT_OF_BOUNDS);
        }
    }

    /**
     * Finds all the tasks in the task list that contain the specified keyword in description.
     *
     * @param keyword The index of the task to be marked as done.
     */
    public TaskList findTasks(String keyword) {
        TaskList matchingTasks = new TaskList();
        this.forEach(task -> {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        });

        return matchingTasks;
    }

    /** Prints all the tasks in the task list, with index. */
    public void printTasks() {
        AtomicInteger idx = new AtomicInteger(Message.TASK_LIST_FIRST_INDEX);
        this.forEach(task -> System.out.println(idx.getAndIncrement() + "." + task.toString()));
    }

    /**
     * Performs a stable sort using a comparator and maintains the relative order of other tasks.
     *
     * @param comparator Comparator to sort the specific tasks.
     * @param target The type of tasks to prioritize (todo, deadline, event).
     */
    public void sortTasks(Comparator<Task> comparator, String target) throws DuckException {
        TaskList sortedTasks = new TaskList();
        TaskList remainingTasks = new TaskList();

        for (Task task : this) {
            if (isOfTargetType(task, target)) {
                sortedTasks.add(task);
            } else {
                remainingTasks.add(task);
            }
        }

        if (comparator != null) {
            sortedTasks.sort(comparator);

            // Clear the list and re-add sorted + remaining tasks
            this.clear();
            this.addAll(sortedTasks);
            this.addAll(remainingTasks);
        }
    }

    /**
     * Checks if the task is of the type specified by the target.
     *
     * @param task The task to check.
     * @param target The type of tasks (todo, deadline, event).
     * @return True if the task matches the target type, false otherwise.
     */

    // CHECKSTYLE.OFF: Indentation
    private boolean isOfTargetType(Task task, String target) {
        return switch (target) {
            case "todo" -> task instanceof ToDo;
            case "deadline" -> task instanceof Deadline;
            case "event" -> task instanceof Event;
            default -> true; // for "all", all tasks are included
        };
    }
    // CHECKSTYLE.ON: Indentation

}
