package friendlybot.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * TaskList contains the task list, and handles operations that involve the task list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * A constructor for the TaskList.
     *
     * @param tasks An ArrayList of tasks to be contained within an instance of TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task Task to be added to the TaskList.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a Task in the TaskList that corresponds to the taskNumber.
     *
     * @param taskNumber taskNumber of Task to be deleted.
     * @return Task that was deleted.
     */
    public Task deleteTask(int taskNumber) {
        return this.tasks.remove(taskNumber - 1);
    }

    /**
     * Returns the number of tasks that is contained within the TaskList.
     *
     * @return Size of tasks in TaskList.
     */
    public int getNumTasks() {
        return this.tasks.size();
    }

    /**
     * Returns a Task that corresponds to the taskNumber.
     *
     * @param taskNumber Task Number of task to be deleted, which is one more than the index of the Task.
     * @return Task that corresponds to the taskMumber.
     */
    public Task getTask(int taskNumber) {
        return this.tasks.get(taskNumber - 1);
    }

    /**
     * Returns a list of Tasks that correspond to the given date.
     *
     * @param date Date to check whether a Task happens on that date.
     * @return An ArrayList of Tasks that happens on a given date.
     */
    public List<Task> getTasksOnDate(LocalDate date) {
        return this.tasks.stream()
                .filter(task -> (task instanceof Deadline d && dateIsOnDeadline(date, d))
                        || (task instanceof Event e && dateIsDuringEvent(date, e)))
                .toList();
    }

    private boolean dateIsOnDeadline(LocalDate date, Deadline deadline) {
        return deadline.by.equals(date);
    }

    private boolean dateIsDuringEvent(LocalDate date, Event event) {
        boolean dateIsStartOfEvent = event.from.equals(date);
        boolean dateIsEndOfEvent = event.to.equals(date);
        boolean dateIsMiddleOfEvent = event.from.isBefore(date) && event.to.isAfter(date);
        return dateIsStartOfEvent || dateIsEndOfEvent || dateIsMiddleOfEvent;
    }

    /**
     * Returns a String representation of the list of tasks in TaskList to be saved in a file for local storage.
     *
     * @return A String representation of the list of tasks in TaskList.
     */
    public String formatTasksToSave() {
        StringBuilder sb = new StringBuilder();
        for (Task task : this.tasks) {
            if (task instanceof ToDo) {
                sb.append("T | ");
            } else if (task instanceof Deadline) {
                sb.append("D | ");
            } else {
                sb.append("E | ");
            }
            if (task.isDone) {
                sb.append("1 | ");
            } else {
                sb.append("0 | ");
            }
            sb.append(task.description);
            if (task instanceof Deadline) {
                sb.append(" | ").append(((Deadline) task).by);
            } else if (task instanceof Event e) {
                sb.append(" | ").append(e.from).append(" | ").append(e.to);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns an ArrayList of Tasks whose description contains the keyword.
     * @param keyword A substring of the Task description to look for.
     * @return An ArrayList of Tasks that match the keyword.
     */
    public List<Task> findTasks(String keyword) {
        return this.tasks.stream()
                .filter(task -> task.description.contains(keyword))
                .toList();
    }
}
