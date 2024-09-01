package FRIDAY;

import java.util.ArrayList;

/**
 * Represents a list of tasks, allowing for task management operations such as
 * adding, removing, and marking tasks.
 * <p>
 * This class provides methods to manipulate and retrieve tasks in the list,
 * including displaying all tasks, marking tasks as complete or incomplete,
 * and accessing specific tasks by index.
 * </p>
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructs a new TaskList with the specified list of tasks.
     * <p>
     * The initial task list is populated with the tasks from the provided list.
     * </p>
     *
     * @param list the list of tasks to initialize the TaskList with
     */
    public TaskList(ArrayList<Task> list) {
        this.taskList = new ArrayList<>();
        taskList.addAll(list);
    }

    public void addTask(Task newTask) {
        taskList.add(newTask);
    }

    public void removeTask(int index) {
        taskList.remove(index);
    }

    /**
     * Method returns a String representation of the list of tasks if there are any.
     * If no tasks are available, it returns a String to communicate that
     * @return String representation of the list of tasks
     */
    public String displayTasks() {
        if(taskList.isEmpty()) {
            return "You currently have no tasks in your list";
        }
        StringBuilder sb = new StringBuilder();
        taskList.forEach((task) -> {
            sb.append(taskList.indexOf(task) + 1).append(". ").append(task).append("\n");
        });
        return sb.toString();
    }

    /**
     * Marks the task at the given index as complete
     * @param index index of the task that is to be marked as complete
     */
    public void markTask(int index) {
        taskList.get(index).check();
    }

    /**
     * Marks the task at the given index as incomplete
     * @param index index of the task that is to be marked as incomplete
     */
    public void unMarkTask(int index) {
        taskList.get(index).uncheck();
    }

    public ArrayList<Task> getList() {
        return this.taskList;
    }

    public int numTasks() {
        return taskList.size();
    }

    public Task getTaskAt(int index) {
        return taskList.get(index);
    }
}
