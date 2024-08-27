import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public void loadTasks(Storage storage) {
        this.tasks = storage.retrieveTasks();
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    public int getSize() {
        return this.tasks.size();
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public void completeTaskInList(int index) {
        this.tasks.get(index - 1).completeTask();
    }

    public void unmarkTaskInList(int index) {
        this.tasks.get(index - 1).uncompleteTask();
    }

    public Task deleteTaskInList(int index) {
        Task t = this.tasks.get(index - 1);
        this.tasks.remove(index - 1);
        return t;
    }

    public Todo addTodo(String taskDescription) {
        Todo t = new Todo(taskDescription);
        this.tasks.add(t);
        return t;
    }

    public Deadline addDeadline(String taskDescription, LocalDateTime time) {
        Deadline d = new Deadline(taskDescription, time);
        this.tasks.add(d);
        return d;
    }

    public Event addEvent(String taskDescription, LocalDateTime startTime, LocalDateTime endTime) {
        Event e = new Event(taskDescription, startTime, endTime);
        this.tasks.add(e);
        return e;
    }
}
