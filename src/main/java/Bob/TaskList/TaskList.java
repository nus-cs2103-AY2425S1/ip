package Bob.TaskList;

import java.util.ArrayList;
import Bob.Tasks.Task;
import Bob.Exception.BobException;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }


    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
