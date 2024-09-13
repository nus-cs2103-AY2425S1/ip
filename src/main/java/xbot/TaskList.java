package xbot;

import java.util.ArrayList;
import java.util.List;

import xbot.exception.XBotException;
import xbot.parser.Parser;
import xbot.task.Deadline;
import xbot.task.Event;
import xbot.task.Task;
import xbot.task.ToDo;
import xbot.ui.Ui;

/**
 * The TaskList class manages the list of tasks in the XBot application.
 * It provides methods to add, delete, and retrieve tasks, as well as
 * to mark tasks as done or undone.
 */
public class TaskList {
    private List<Task> list;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Returns the list of all tasks.
     *
     * @return A List of Task objects.
     */
    public List<Task> getAllTask() {
        return list;
    }


    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return list.size();
    }


    /**
     * Returns the task at the specified index.
     *
     * @param index The index of the task to return.
     * @return The Task object at the specified index.
     */
    public Task get(int index) {
        assert index >= 0 : "Index for task list should not be less than 0";
        return list.get(index);
    }

    /**
     * Adds the task to the list.
     *
     * @param task The task to add.
     */
    public void add(Task task) {
        assert task != null : "Task added should not be null";
        list.add(task);
    }

    /**
     * Removes the task from the list.
     *
     * @param index The index of task to remove.
     */
    public void remove(int index) {
        list.remove(index);
    }

    /**
     * Finds and displays tasks that match the given keyword.
     *
     * @param keyword the keyword to search for in task descriptions
     */
    public String findTask(String keyword) {
        assert !keyword.isEmpty() : "The keyword for find task is empty!";
        TaskList specificTasks = new TaskList();
        list.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .forEach(filtered -> specificTasks.add(filtered));
        return Ui.showMatchingTaskList(specificTasks);
    }
}
