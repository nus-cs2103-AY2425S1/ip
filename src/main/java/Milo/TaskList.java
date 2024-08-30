package Milo;

import Milo.TaskObj.Task;
import java.util.ArrayList;

/*
* Represents task list object
 */
public class TaskList {

    private final ArrayList<Task> todoList;

    /*
    * Initialise task list object
    *
    * @param ArrayList<Task> to initialise TaskList object to
     */
    public TaskList(ArrayList<Task> todoList) {
        this.todoList = todoList;
    }

    /*
    * A getter method returning the task list array
     */
    public ArrayList<Task> getList() {
        return this.todoList;
    }
}
