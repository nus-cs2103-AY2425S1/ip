package bottle.task;

import java.util.ArrayList;
import java.util.Comparator;


/**
 * The type Task list.
 */
public class TaskList {
    /**
     * The Task list.
     */
    protected ArrayList<Task> taskList;


    /**
     * Instantiates a new Task list.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Instantiates a new Task list.
     *
     * @param taskList the task list
     */
    public TaskList(ArrayList<Task> taskList) {
        assert taskList != null : "taskList shouldn't be null!";
        this.taskList = taskList;
    }

    /**
     * Gets task list.
     *
     * @return the task list
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Gets task.
     *
     * @param index the index
     * @return the task
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Add task.
     *
     * @param task the task
     */
    public void addTask(Task task) {
        assert task != null : "task cannot be null!";
        taskList.add(task);
    }

    /**
     * Remove task task.
     *
     * @param index the index
     * @return the task
     */
    public Task removeTask(int index) {
        if (index >= 0 && index < taskList.size()) {
            return taskList.remove(index);
        } else {
            throw new IndexOutOfBoundsException("OOPS!!! Task number is out of bounds.");
        }
    }

    @Override
    public String toString() {
        assert taskList != null : "Task List shouldn't be null";
        StringBuilder tasks = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            int idx = i + 1;
            tasks.append(idx).append(". ").append(taskList.get(i)).append("\n");
        }
        return tasks.toString();
    }

    /**
     * Detect anomalies array list.
     *
     * @return the array list
     */
    public TaskList detectAnomalies() {
        ArrayList<Event> eventList = new ArrayList<>();
        for (Task t : taskList) {
            if (t instanceof Event) {
                eventList.add((Event) t);
            }
        }
        eventList.sort(Comparator.comparing(e -> e.from));
        ArrayList<Event> clashList = new ArrayList<>();
        for (int i = 0; i < eventList.size() - 1; i++) {
            Event prev = eventList.get(i);
            Event curr = eventList.get(i + 1);
            if (!prev.to.isBefore(curr.from)) {
                clashList.add(prev);
                clashList.add(curr);
            }
        }
        return new TaskList(new ArrayList<>(clashList.stream().distinct().toList()));
    }
}
