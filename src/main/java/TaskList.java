import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasklist;

    public TaskList() {
        this.tasklist = new ArrayList<>();
    }

    public String add(String task) {
        Task newTask = new Task(task);
        this.tasklist.add(newTask);
        return "Added: " + task;
    }

    public String markTask(int index) {
        Task task = this.tasklist.get(index-1);
        task.mark();
        return " Nice! I've marked this task as done: \n" + task;
    }

    public String unmarkTask(int index) {
        Task task = this.tasklist.get(index-1);
        task.unmark();
        return "OK, I've marked this task as not done yet: \n" + task;
    }

    @Override
    public String toString() {
        String tasks = "Here are the tasks in your list: \n";
        for (int i=0; i < tasklist.size(); i++) {
            tasks += (i+1) + ". " + tasklist.get(i) + "\n";
        }
        return tasks;
    }

}
