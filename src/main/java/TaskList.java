import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private ArrayList<Task> taskLists;

    public TaskList() {
        this.taskLists = new ArrayList<>();
    }
    public TaskList(Scanner sc) {
        this.taskLists = new ArrayList<>();
        while (sc.hasNextLine()) {
            String nextLine = sc.nextLine();
            String[] split = nextLine.split(" \\| ");
            String taskType = split[0].trim();
            Boolean isDone = split[1].equals("0") ? false : true;
            if (taskType.equals("T")) {
                Task task = new ToDo(split[2]);
                taskLists.add(task);
                if (isDone) {
                    task.editStatus();
                }
            } else if (taskType.equals("D")) {
                Task task = new Deadline(split[2], split[3]);
                taskLists.add(task);
                if (isDone) {
                    task.editStatus();
                }
            } else if (taskType.equals("E")) {
                Task task = new Event(split[2], split[3], split[4]);
                taskLists.add(task);
                if (isDone) {
                    task.editStatus();
                }
            }

        }
        sc.close();
    }
    public void addTask(Task task) {
        this.taskLists.add(task);
    }

    public Task removeTask(int index) {
        return this.taskLists.remove(index);
    }
    public Task getTask(int index) {
        return this.taskLists.get(index);
    }

    public int size() {
        return this.taskLists.size();
    }

    @Override
    public String toString() {
        return this.taskLists.toString();
    }
}

