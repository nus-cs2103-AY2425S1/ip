import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class TaskList {
    private ArrayList<Task> taskList;
    private final Line line = new Line();
    private int taskListLength;
    private int startPointer;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
        this.startPointer = 0;
        this.taskListLength = 0;
    }

    public void add(String s) {
        this.taskList.add(Task.of(s));
        this.taskListLength += 1;
        line.drawLine();
        System.out.println("    added: " + s);
        line.drawLine();
    }

    public void markAsDone(String s) {
        int idx = parseInt(s);
        idx -= 1;
        if ((idx < 0) || (idx > taskListLength)) {
            return;
        }
        Task currentTask = taskList.get(idx);
        currentTask.markAsDone();
        line.drawLine();
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("      [" + currentTask.getStatus() + "] " + currentTask.readTask());
        line.drawLine();
    }


    public void markAsUndone(String s) {
        int idx = parseInt(s);
        idx -= 1;
        if ((idx < 0) || (idx > taskListLength)) {
            return;
        }
        Task currentTask = taskList.get(idx);
        currentTask.markAsUndone();
        line.drawLine();
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("      [" + currentTask.getStatus() + "] " + currentTask.readTask());
        line.drawLine();
    }

    public void list() {
        if (taskListLength == 0) {
            return;
        }

        line.drawLine();
        for (int i = startPointer; i < taskListLength; i++) {
            int counter = i + 1;
            Task currentTask = this.taskList.get(i);
            System.out.println("    " + counter + ". [" + currentTask.getStatus()  + "] " + currentTask.readTask());
        }
        line.drawLine();
    }
}
