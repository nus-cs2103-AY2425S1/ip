import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> lst;

    TaskList() {
        this.lst = new ArrayList<Task>();
    }

    String add(Task task) {
        this.lst.add(task);
        return "Got it. I've added this task:\n" +
                "   " + task + "\n" +
                "Now you have " + this.lst.size() + " tasks in the list.";
    }

    String view() {
        int i = 1;
        String s = "Here are the tasks in your list:";
        for (Task task : this.lst) {
            s += "\n" + i + ". " + task;
            i++;
        }
        return s;
    }

    String markTask(boolean isDone, int taskId) {
        Task task = this.lst.get(taskId - 1);
        task.changeStatus(isDone);
        return task.toString();
    }

    String delete(int taskId) {
        Task task = this.lst.get(taskId - 1);
        this.lst.remove(taskId - 1);
        return "Got it. I've removed this task:\n" +
                "   " + task + "\n" +
                "Now you have " + this.lst.size() + " tasks in the list.";
    }
}
