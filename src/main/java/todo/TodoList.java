package todo;

import java.util.ArrayList;
import java.util.Arrays;

public class TodoList {
    private ArrayList<Task> todoList = new ArrayList<>();

    public TodoList() {}
    public TodoList(Task ...tasks) {
        this.todoList.addAll(Arrays.asList(tasks));
    }

    public void addTask(Task task) {
        this.todoList.add(task);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        int len = this.todoList.size();
        for (int n = 0; n < len; n++) {
            Task task = this.todoList.get(n);
            str.append(String.format("%d. %s\n", n, task));
        }
        return str.toString();
    }
}
