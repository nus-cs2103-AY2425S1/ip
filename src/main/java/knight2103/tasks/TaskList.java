package knight2103.tasks;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.IntStream;
import java.util.function.IntFunction;
import java.util.function.Predicate;

/**
 * Models a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an object containing an empty list of tasks stored as an ArrayList.
     * This list is called the taskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Constructs an object containing an existent list of tasks stored as an ArrayList.
     * This list is called the taskList.
     */
    public TaskList(ArrayList<Task> storageData) {
        this.tasks = storageData;
    }

    /**
     * Returns the number of tasks in the bot's taskList.
     *
     * @return Number of tasks in the bot's taskList.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Adds a task into the bot's taskList.
     *
     * @param newTask Task to be added to the taskList.
     */
    public void add(Task newTask) {
        this.tasks.add(newTask);
    }

    /**
     * Returns the task that is being marked as done. The task identified by its index
     * will be marked as done in the bot's taskList.
     *
     * @param index The index of the taskList that refers to the task to be marked.
     * @return The newly marked-as-done Task.
     * @throws IndexOutOfBoundsException If index indicating the Task is out of range
     * of the taskList.
     */
    public Task mark(int index) throws IndexOutOfBoundsException { // modify Command
        this.tasks.get(index).markDone();
        return this.tasks.get(index); // code must be after markDone() to return the newly updated one
    }

    /**
     * Returns the task that is being unmarked as done. The task identified by its index
     * will be unmarked as done in the bot's taskList.
     *
     * @param index The index of the taskList that refers to the task to be unmarked.
     * @return The newly unmarked Task (task that is not done).
     * @throws IndexOutOfBoundsException If index indicating the Task is out of range
     * of the taskList.
     */
    public Task unmark(int index) throws IndexOutOfBoundsException {
        this.tasks.get(index).unmarkDone();
        return this.tasks.get(index); // code must be after unmarkDone() to return the newly updated one
    }

    /**
     * Returns the task that is being deleted. The task identified by its index
     * will be deleted in the bot's taskList.
     *
     * @param index The index of the taskList that refers to the task to be deleted.
     * @return The Task to be deleted.
     * @throws IndexOutOfBoundsException If index indicating the Task is out of range
     * of the taskList.
     */
    public Task delete(int index) throws IndexOutOfBoundsException {
        Task taskToDelete = this.tasks.get(index); // code must be before delete() to show the original task
        this.tasks.remove(index);
        return taskToDelete;
    }

    /**
     * Sorts the list of task in an order specified by compareLogic parameter.
     *
     * @param compareLogic The Comparator object that contains the logic behind the sorting of Task objects.
     */
    public void sort(Comparator<Task> compareLogic) {
        this.tasks.sort(compareLogic);
    }

    /**
     * Filters the list of task based on the condition specified by the predicate.
     *
     * @param predicate The logic condition for filtering the list of task. If predicate returns true, the
     * task will remain in the list of task.
     * @return A new list of tasks that fulfils the condition specified by the predicate.
     */
    public TaskList filter(Predicate<Task> predicate) {
        ArrayList<Task> filteredTasks =
                new ArrayList<Task>(this.tasks.stream().filter(predicate).toList());
        return new TaskList(filteredTasks);
    }

    /**
     * Returns a String representation of the TaskList object when this object is to be shown in a text file.
     *
     * @return String representation of TaskList object when in text file.
     */
    public String toStringInFile() {
        return formatToList(index -> tasks.get(index).toStringInFile() + "\n");
    }

    @Override
    public String toString() {
        return formatToList(index -> index + 1 + ". " + tasks.get(index).toString() + "\n");
    }

    private String formatToList(IntFunction<String> mapper) {
        return IntStream.range(0, this.tasks.size())
                .mapToObj(mapper)
                        .reduce("", (task1, task2) -> task1 + task2);
    }
}
