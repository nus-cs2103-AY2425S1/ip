package Milo.Tasks;

import Milo.Tasks.Task;
import java.util.ArrayList;

/*
* Represents task list object
 */
public class TaskList {

    private final ArrayList<Task> todoList;

    private int numberOfTasks;

    /*
     * Initialise empty task list object
     */
    public TaskList() {
        this.todoList = new ArrayList<Task>();
    }

    /*
    * Initialise task list object
    *
    * @param ArrayList<Task> to initialise TaskList object to
     */
    public TaskList(ArrayList<Task> todoList) {
        this.numberOfTasks = todoList.size();
        this.todoList = todoList;
    }

    /*
    * A getter method returning the task list array
     */
    public ArrayList<Task> getList() {
        return this.todoList;
    }

    /*
    * A getter method returning the number of tasks
     */
    public int getNumberOfTasks() {
        return this.numberOfTasks;
    }

    /*
     * An add Task item method
     *
     * @param Task item
     */
    public void add(Task item) {
        this.todoList.add(item);
        this.numberOfTasks++;
    }

    /*
    * A get element by index method
    *
    * @param Element index
     */
    public Task get(int index) {
        return this.todoList.get(index);
    }

    /*
     * A remove element by index method
     *
     * @param Element index
     */
    public void remove(int index) {
        this.todoList.remove(index);
        this.numberOfTasks--;
    }

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
