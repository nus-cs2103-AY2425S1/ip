package friendlybot.task;

import java.time.LocalDate;
import java.util.ArrayList;

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
        assert taskNumber <= this.tasks.size() : "Input should be less than or equal to the number of tasks.";
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
        assert taskNumber <= this.tasks.size() : "Input should be less than or equal to the number of tasks.";
        return this.tasks.get(taskNumber - 1);
    }

    /**
     * Returns a list of Tasks that correspond to the given date.
     *
     * @param date Date to check whether a Task happens on that date.
     * @return An ArrayList of Tasks that happens on a given date.
     */
    public ArrayList<Task> getTasksOnDate(LocalDate date) {
        ArrayList<Task> res = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task instanceof Deadline d) {
                if (d.by.equals(date)) {
                    res.add(task);
                }
            } else if (task instanceof Event e) {
                if (e.from.equals(date) || e.to.equals(date) || (e.from.isBefore(date) && e.to.isAfter(date))) {
                    res.add(task);
                }
            }
        }
        return res;
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
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> res = new ArrayList<>();
        System.out.println(keyword);
        for (Task task : this.tasks) {
            String taskDescription = task.description;
            System.out.println(taskDescription);
            if (taskDescription.contains(keyword)) {
                System.out.println("yes");
                res.add(task);
            }
        }
        return res;
    }
}
