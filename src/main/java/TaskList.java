import java.lang.IndexOutOfBoundsException;
import java.io.IOException;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class TaskList {
    private ArrayList<Task> lst;
    private TaskListSaver tls;

    TaskList(TaskListSaver tls) {
        this.lst = new ArrayList<Task>();
        this.tls = tls;
    }

    String add(Task task) {
        this.lst.add(task);
        this.writeToFile();
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
        this.writeToFile();
        return task.toString();
    }

    String delete(int taskId) {
        Task task = this.lst.get(taskId - 1);
        this.lst.remove(taskId - 1);
        this.writeToFile();
        return "Got it. I've removed this task:\n" +
                "   " + task + "\n" +
                "Now you have " + this.lst.size() + " tasks in the list.";
    }

    private void writeToFile() {
        String s = "";
        for (Task task : this.lst) {
            s += task.convertToTxt() + "\n";
        }
        this.tls.writeToFile(s);
    }
}
