import TaskObj.Task;
import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> todoList;

    public TaskList(ArrayList<Task> todoList) {
        this.todoList = todoList;
    }

    public ArrayList<Task> getList() {
        return this.todoList;
    }
}
