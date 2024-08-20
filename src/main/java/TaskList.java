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

    public void add(String s, Task.TaskType taskType) {
        try {
            Task newTask;

            switch (taskType) {
                case T:
                    newTask = ToDo.of(s, taskType);
                    break;
                case D:
                    newTask = Deadline.of(s, taskType);
                    break;
                case E:
                    newTask = Event.of(s, taskType);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid task type");
            }
            this.taskList.add(newTask);
            this.taskListLength += 1;
            line.drawLine();
            System.out.println("    Got it. I've added this task: ");
            System.out.println("      [" + newTask.getTaskTypeAsString() + "][ ] " + newTask.readTask());
            if (this.taskListLength == 1) {
                System.out.println("    Now you have 1 task in the list.");
            } else {
                System.out.println("    Now you have " + this.taskListLength + " tasks in the list.");
            }
            line.drawLine();
        } catch (TaskCreationException e) {
            line.drawLine();
            System.out.println("    " + e.getMessage());
            line.drawLine();
        }
    }

    public void delete(int idx) {
        idx = idx - 1;
        if (idx < 0 || idx >= taskListLength) {
            line.drawLine();
            System.out.println("    Enter a valid index");
            line.drawLine();
        } else {
            Task removedTask = taskList.remove(idx);
            line.drawLine();
            taskListLength--;
            System.out.println("    Noted. I've removed this task: ");
            System.out.println("      [" + removedTask.getTaskTypeAsString() + "]" + "["+ removedTask.getStatus() + "] " + removedTask.readTask());
            if (taskListLength == 1) {
                System.out.println("    Now you have 1 task in the list.");
            } else {
                System.out.println("    Now you have " + this.taskListLength + " tasks in the list.");
            }
            line.drawLine();
        }
    }

    public void markAsDone(String s) {
        int idx = parseInt(s);
        idx -= 1;
        if ((idx < 0) || (idx >= taskListLength)) {
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
        if ((idx < 0) || (idx >= taskListLength)) {
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
            System.out.println("    " + counter + ". [" + currentTask.getTaskTypeAsString() + "] [" + currentTask.getStatus()  + "] " + currentTask.readTask());
        }
        line.drawLine();
    }
}
