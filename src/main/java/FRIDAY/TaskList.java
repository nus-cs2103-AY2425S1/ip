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

    /**
     * removes the task from the task list
     * @param index
     * @throws FRIDAYException
     */
    public void removeTask(int index) throws FRIDAYException {
        if (taskList.isEmpty()) {
            throw new FRIDAYException("Hey your task list is currently empty!");
        }
        if (index >= taskList.size() || index < 0) {
            throw new FRIDAYException("Hey it appears that the task number you entered does not exist");
        }
        taskList.remove(index);
    }

    /**
     * Method returns a String representation of the list of tasks if there are any.
     * If no tasks are available, it returns a String to communicate that
     * @return String representation of the list of tasks
     */
    public String displayTasks() {
        if (taskList.isEmpty()) {
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
    public void markTask(int index) throws FRIDAYException {
        if (index >= taskList.size() || index < 0) {
            throw new FRIDAYException("Hey it appears that the task number you entered does not exist");
        }
        if (taskList.isEmpty()) {
            throw new FRIDAYException("Add some tasks to mark!");
        }
        taskList.get(index).check();
    }

    /**
     * Marks the task at the given index as incomplete
     * @param index index of the task that is to be marked as incomplete
     */
    public void unMarkTask(int index) throws FRIDAYException {
        if (index >= taskList.size() || index < 0) {
            throw new FRIDAYException("Hey it appears that the task number you entered does not exist");
        }
        if (taskList.isEmpty()) {
            throw new FRIDAYException("Add some tasks to unmark!");
        }
        taskList.get(index).uncheck();
    }

    public ArrayList<Task> getList() {
        return this.taskList;
    }

    public int numTasks() {
        return taskList.size();
    }

    public Task getTaskAt(int index) throws FRIDAYException {
        if (index >= taskList.size() || index < 0) {
            throw new FRIDAYException("Hey it appears that the task number you entered does not exist");
        }
        return taskList.get(index);
    }

    /**
     * This method searches the tasklist and returns an ArrayList of Tasks
     * that have descriptions which contain the keyword
     * @param keyword the keyword that the user tries to search for
     * @return ArrayList of tasks that have descriptions containing the keyword
     */
    public ArrayList<Task> search(String keyword) throws FRIDAYException {
        if (taskList.isEmpty()) {
            throw new FRIDAYException("You currently have no tasks in your list!");
        }
        ArrayList<Task> list = new ArrayList<>();
        taskList.forEach((task) -> {
            if (task.containsKeyword(keyword)) {
                list.add(task);
            }
        });
        return list;
    }

    public void deleteAllTasks() {
        this.taskList.clear();
    }
}
