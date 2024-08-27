package Tasks;

import java.util.ArrayList;



// contains the task list e.g., it has operations to add/delete tasks in the list

public class TaskList {
    
    private ArrayList<Task> tasksArray;

    public TaskList(TaskList taskList) {
        this.tasksArray = new ArrayList<Task>(taskList.tasksArray);
    }

    public TaskList() {
        this.tasksArray = new ArrayList<Task>();
    }

    // add
    public void add(Task task) {
        tasksArray.add(task);
    }

    // get
    public Task get(int index) {
        return tasksArray.get(index);
    }

    // size
    public int size() {
        return tasksArray.size();
    }

    // remove
    public void remove(int index) {
        tasksArray.remove(index);
    }
}
