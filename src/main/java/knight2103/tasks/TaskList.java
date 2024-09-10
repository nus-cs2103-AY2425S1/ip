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
     * Returns the Task specified by the index in the bot's taskList.
     *
     * @return the Task specified by index in the bot's taskList.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
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
        return this.tasks.get(index); // must be after markDone to return the newly updated one
    }

    /**
     * Returns the task that is being unmarked as done. The task identified by its index
     * will be unmarked as done in the bot's taskList.
     *
     * @param index The index of the taskList that refers to the task to be unmarked.
     * @return The newly unmarked-as-done Task.
     * @throws IndexOutOfBoundsException If index indicating the Task is out of range
     * of the taskList.
     */
    public Task unmark(int index) throws IndexOutOfBoundsException {
        this.tasks.get(index).unmarkDone();
        return this.tasks.get(index); // must be after unmarkDone to return the newly updated one
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
        Task taskToDelete = this.tasks.get(index);
        this.tasks.remove(index);
        return taskToDelete;
    }

    /**
     * Sorts the list of task where tasks not marked as done are shown first, tasks marked as done are
     * shown at the bottom. Within the marked or unmarked task category, the tasks are shwon in the order of
     * TodoTask, DeadlineTask then EventTask. Within the TodoTask, tasks are sorted alphabetically. Within
     * DeadlineTask, tasks are sorted based on deadlines. Within EventTask, tasks are sorted based on
     * StartTimes. If the start times are the same, tasks are further sorted based on end times. For both
     * deadline and event tasks, if the date and times are the same between the different tasks, these
     * tasks will be sorted based on alphabetical order.
     *
     * @param compareLogic The Comparator class that contains the logic behind the sorting of Task objects.
     * @return The list of tasks after being sorted.
     */
    public void sort(compareLogic) { // hmm void or sort?
        return this.tasks.sort(compareLogic); // mmmm. What other exceptions will there be?
    }

    /**
     * Filters the list of task based on the condition specified by the predicate.
     *
     * @param predicate The logic condition for filtering the list of task. If predicate returns true, the
     * tasks will remain in the list of task.
     * @return A new list of task that corresponds to the predicate.
     */
    public TaskList filter(Predicate<Task> predicate) {
        ArrayList<Task> filteredTasks =
                new ArrayList<Task>(this.tasks.stream().filter(predicate).toList());
        return new TaskList(filteredTasks);
    }

    /**
     * Returns the output in String as a representation of TaskList object which stores list of tasks.
     *
     * @return String representation of TaskList object.
     */
    public String toStringInFile() {
        return formatToList(index -> tasks.get(index).toStringInFile() + "\n");
    }

    @Override
    public String toString() {
        return formatToList(index -> index + ". " + tasks.get(index).toString() + "\n");
    }

    private String formatToList(IntFunction<String> mapper) {
        return IntStream.range(0, this.tasks.size())
                .mapToObj(mapper)
                        .reduce("", (task1, task2) -> task1 + task2);
    }
}
