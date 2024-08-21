import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a todo to the list.
     *
     * @param description Description of the todo.
     * @return Todo that was added.
     */
    public Task addTodo(String description) {
        Task newTodo = new Todo(description);

        this.tasks.add(newTodo);
        return newTodo;
    }

    /**
     * Adds an event to the list.
     *
     * @param description Description of the event.
     * @param startTime Starting time for the event timeframe.
     * @param endTime Ending time for the event timeframe.
     * @return Event that was added.
     */
    public Task addEvent(String description, String startTime, String endTime) {
        Task newEvent = new Event(description, startTime, endTime);

        this.tasks.add(newEvent);
        return newEvent;
    }

    /**
     * Adds a deadline to the list.
     *
     * @param description Description of the deadline.
     * @param endTime Time to do the deadline by.
     * @return Deadline that was added.
     */
    public Task addDeadline(String description, String endTime) {
        Task newDeadline = new Deadline(description, endTime);

        this.tasks.add(newDeadline);
        return newDeadline;
    }

    /**
     * Marks the task on the list as done.
     *
     * @param taskNumber Number of the task.
     * @return Task that was done.
     * @throws SentinelException if the task does not exist.
     */
    public Task markAsDone(int taskNumber) throws SentinelException {
        if (taskNumber > this.tasks.size() || taskNumber <= 0) {
            throw new SentinelException("That task number does not exist!");
        }

        this.tasks.get(taskNumber - 1).markAsDone();

        return this.tasks.get(taskNumber - 1);
    }

    /**
     * Marks the task on the list as undone.
     *
     * @param taskNumber Number of the task.
     * @return Task that was undone.
     * @throws SentinelException if the task does not exist.
     */
    public Task markAsUndone(int taskNumber) throws SentinelException {
        if (taskNumber > this.tasks.size() || taskNumber <= 0) {
            throw new SentinelException("That task number does not exist!");
        }

        this.tasks.get(taskNumber - 1).markAsUndone();

        return this.tasks.get(taskNumber - 1);
    }

    /**
     * Deletes the task on the list.
     *
     * @param taskNumber Number of the task.
     * @return Task that was deleted.
     * @throws SentinelException if the task does not exist.
     */
    public Task deleteTask(int taskNumber) throws SentinelException {
        if (taskNumber > this.tasks.size() || taskNumber <= 0) {
            throw new SentinelException("That task number does not exist!");
        }

        return this.tasks.remove(taskNumber - 1);
    }

    /**
     * Returns the string representation of the task list.
     *
     * @return List of tasks.
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < tasks.size(); i++) {
            output.append(String.format("%d. %s \n", i + 1, this.tasks.get(i).toString()));
        }

        return output.toString();
    }

}
