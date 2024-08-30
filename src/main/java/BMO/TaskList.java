package BMO;
import java.io.IOException;
import java.util.ArrayList;

import BMO.task.Deadline;
import BMO.task.Event;
import BMO.task.Task;
import BMO.task.ToDo;

public class TaskList {
    private ArrayList<Task> TasksList;

    public TaskList() {
        this.TasksList = new ArrayList<>(100);
    }

    //Adds the input task to the storage array
    public void addTask(Task task) throws IOException{
        this.TasksList.add(task);
    }

    //Returns the ArrayList containing all the tasks 
    public ArrayList<Task> getTasks() {
        return this.TasksList;
    }

    //Return a specific task from the storage array based on the index
    public Task getTask(int index) {
        return this.TasksList.get(index);
    }

    //Returns the size of the storage array
    public int getSize() {
        return this.TasksList.size();
    }

    //Deletes a task from the storage array based on the index
    public void deleteTask(int index) throws IOException{
        this.TasksList.remove(index);
    }

    public void markTask(int index) throws IOException {
        this.getTask(index).mark();
    }

    public void unmarkTask(int index) throws IOException {
        this.getTask(index).unmark();
    }

    public void addTodo(String description) throws IOException {
        Task todo = new ToDo(description);
        this.addTask(todo);
    }

    public void addDeadline(String description, String by) throws IOException {
        Task deadline = new Deadline(description, by);
        this.addTask(deadline);
    }

    public void addEvent(String description, String from, String to) throws IOException {
        Task event = new Event(description, from, to);
        this.addTask(event);
    }
}