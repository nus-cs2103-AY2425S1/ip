import java.util.ArrayList;

/**
 * List of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param description Description of the task.
     */
    public Task addTodo(String description) {
        Task newTodo = new Todo(description);

        this.tasks.add(newTodo);
        return newTodo;
    }

    public Task addEvent(String description, String startTime, String endTime) {
        Task newEvent = new Event(description, startTime, endTime);

        this.tasks.add(newEvent);
        return newEvent;
    }

    public Task addDeadline(String description, String endTime) {
        Task newDeadline = new Deadline(description, endTime);

        this.tasks.add(newDeadline);
        return newDeadline;
    }

    /**
     * Marks the task on the list as done.
     *
     * @param taskNumber Number of the task.
     */
    public Task markAsDone(int taskNumber) {
        this.tasks.get(taskNumber - 1).markAsDone();

        return this.tasks.get(taskNumber - 1);
    }

    /**
     * Marks the task on the list as undone.
     *
     * @param taskNumber Number of the task.
     */
    public Task markAsUndone(int taskNumber) {
        this.tasks.get(taskNumber - 1).markAsUndone();

        return this.tasks.get(taskNumber - 1);
    }

    /**
     * Deletes the task on the list.
     *
     * @param taskNumber Number of the task.
     */
    public Task deleteTask(int taskNumber) {
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
