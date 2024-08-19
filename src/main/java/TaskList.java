import java.util.ArrayList;

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

    public void list() {
        if (taskListLength == 0) {
            return;
        }

        line.drawLine();
        for (int i = 0; i < taskListLength; i++) {
            int counter = i + 1;
            System.out.println("    "+ counter + ". " + this.taskList.get(i).readTask());
        }
        line.drawLine();
    }
}
