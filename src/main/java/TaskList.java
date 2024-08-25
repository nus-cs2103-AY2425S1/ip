import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getList() {
        return this.tasks;
    }

    public Task get(int idx) throws InvalidIndexException {
        if (idx >= this.tasks.size() || idx < 0) {
            throw new InvalidIndexException();
        }
        return this.tasks.get(idx);
    }

    public void listTasks() {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i));
        }
    }

    public void mark(int idx, boolean state) throws InvalidIndexException {
        this.get(idx).setIsDone(state);
        if (state) {
            System.out.println("Nice! I've marked this task as done: ");
        } else {
            System.out.println("Ok, I've marked this task as not done yet: ");
        }
        System.out.println(this.get(idx));
    }

    public void remove(int idx) throws InvalidIndexException {
        if (idx >= this.tasks.size() || idx < 0) {
            throw new InvalidIndexException();
        }
        Task deleted = this.tasks.remove(idx);
        System.out.println("Got it. I've removed this task: ");
        System.out.println(deleted);
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list");
    }

    public void add(Task newTask) {
        this.tasks.add(newTask);
        System.out.println("Got it. I've added this task: ");
        System.out.println(newTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
    }
}
