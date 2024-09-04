/**
 * Manages an ArrayList<Task> and executes operations to be performed on the list.
 *
 */
import java.util.ArrayList;

class TaskList {
    ArrayList<Task> todoList;

    TaskList() {
        this.todoList = new ArrayList<Task>();
    }

    TaskList(ArrayList<Task> todoList) {
        this.todoList = todoList;
    }

    int size() {
        return this.todoList.size();
    }

    boolean isEmpty() {
        return this.todoList.size() == 0;
    }
    
    Task add(String task) {
        Todo newTask = new Todo(task);
        this.todoList.add(newTask);
        return newTask;
    }

    Task add(String task, String deadline) {
        Deadline newTask = new Deadline(task, deadline);
        this.todoList.add(newTask);
        return newTask;
    }

    Task add(String task, String from, String to) {
        Event newTask = new Event(task, from, to);
        this.todoList.add(newTask);
        return newTask;
    }

    Task delete(int i) {
        int index = i - 1;
        Task removedTask = this.todoList.get(index);
        this.todoList.remove(index);
        return removedTask;
    }

    Task mark(int i) {
        int index = i - 1;
        Task taskToMark = this.todoList.get(index);
        taskToMark.mark();
        this.todoList.set(index, taskToMark);
        return taskToMark;
    }

    Task unmark(int i) {
        int index = i - 1;
        Task taskToMark = this.todoList.get(index);
        taskToMark.unmark();
        this.todoList.set(index, taskToMark);
        return taskToMark;
    }

    TaskList find(String keyword) {
        TaskList matchingTasks = new TaskList();
        for (Task t : this.todoList) {
            if (t.getDescription().contains(keyword)) {
                matchTasks.add(t);
            }
        }
        return matchingTasks;
    }

    String list() {
        String list = "";
        int index = 0;
        for (int i = 0; i < this.todoList.size(); i++) {
            index += 1;
            list += index + ". " + this.todoList.get(i) + "\n";
        }
        return list;
    }

    String toSaveAsString() {
        String output = "";
        for (Task t : this.todoList) {
            output = output + t.toSaveAsString() + "\n";
        }
        return output;
    }
}
