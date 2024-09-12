package milo.lists;

import milo.tasks.Task;

import java.util.ArrayList;

/**
* Represents task list object
 */
public class TaskList extends List<Task>{

    private final ArrayList<Task> todoList;

    private int numberOfTasks;

    /**
     * Initialise empty task list object
     */
    public TaskList() {
        this.todoList = new ArrayList<Task>();
    }

    /**
    * Initialise task list object
    *
    * @param todoList to initialise TaskList object to
     */
    public TaskList(ArrayList<Task> todoList) {
        this.numberOfTasks = todoList.size();
        this.todoList = todoList;
    }

    /**
    * A getter method returning the number of tasks
     */
    public int getNumberOfTasks() {
        return this.numberOfTasks;
    }

    /**
     * An add Task item method
     *
     * @param item to add to task list
     */
    @Override
    public void add(Task item) {
        this.todoList.add(item);
        this.numberOfTasks++;
    }

    /**
    * A get task by index method
    *
    * @param index of the task
     */
    public Task get(int index) {
        return this.todoList.get(index);
    }

    /**
     * A remove task by index method
     *
     * @param index of the task
     */
    public void remove(int index) {
        this.todoList.remove(index);
        this.numberOfTasks--;
    }

    /**
     * A method to find tasks matching the keyword
     *
     * @param keyword to search for in task list
     * @return matching task list
     */
    public TaskList findMatchingTask(String keyword) {
        TaskList matchingTaskList = new TaskList();
        for (int i = 0; i < this.numberOfTasks; i++) {
            if (this.get(i).isSameTask(keyword)) {
                matchingTaskList.add(this.get(i));
            }
        }
        return matchingTaskList;
    }
}
