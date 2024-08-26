import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> lst;

    TaskList() {
        this.lst = new ArrayList<Task>();
    }

    String add(Task task) {
        lst.add(task);
        return "Got it. I've added this task:\n" +
                "   " + task + "\n" +
                "Now you have " + lst.size() + " tasks in the list.";
    }

    String view() {
        int i = 1;
        String s = "Here are the tasks in your list:";
        for (Task task : lst) {
            s += "\n" + i + ". " + task;
            i++;
        }
        return s;
    }

    String markTask(boolean isDone, int taskId) {
        Task task = lst.get(taskId - 1);
        task.changeStatus(isDone);
        return task.toString();
    }
}
