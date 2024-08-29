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

    void loadFromTxt(Path filePath) {
        try {
            List<String> txt = Files.readAllLines(filePath);
            for (String line : txt) {
                String[] arr = line.split(",");
                boolean isDone = arr[1].equals("true");
                if (arr[0].equals("T")) {
                    Todo td = new Todo(isDone, arr[2]);
                    this.lst.add(td);
                } else if (arr[0].equals("D")) {
                    Deadline dl = new Deadline(isDone, arr[2], arr[3]);
                    this.lst.add(dl);
                } else if (arr[0].equals("E")) {
                    Event e = new Event(isDone, arr[2], arr[3], arr[4]);
                    this.lst.add(e);
                } else {
                    throw new LoafyException();
                }
            }
        } catch (IOException e) {
            System.out.println("Error: Could not load previous list");
        } catch (IndexOutOfBoundsException | LoafyException e) {
            System.out.println("Error: Previous list corrupted");
        }
    }
}
