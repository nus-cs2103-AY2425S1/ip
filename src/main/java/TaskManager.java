import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>(100);
    }

    public void addTask(Task task) {
        tasks.add(task);
        printLines("added: " + task.getTaskItem());
    }

    public int getTaskSize() {
        return tasks.size();
    }

    public void listTasks() {
        String taskString = "";

        for (int i=0; i<tasks.size(); i++) {
            if (i != tasks.size() - 1) {
                taskString += i + 1 + "." + tasks.get(i).toString() + "\n    ";
            }
            else {
                taskString += i + 1 + "." + tasks.get(i).toString();
            }
        }

        printLines(taskString);
    }

    public void completeTask(int taskId) {
        if (taskId < tasks.size() + 1) {
            tasks.get(taskId - 1).setComplete();
            printLines("Nice, I've marked this task as complete:\n   " +
                    tasks.get(taskId - 1).toString());
        }
    }

    public void incompleteTask(int taskId) {
        if (taskId < tasks.size() + 1) {
            tasks.get(taskId - 1).setIncomplete();
            printLines("Ok, I've marked this task as incomplete:\n   " +
                    tasks.get(taskId - 1).toString());
        }
    }

    public void printLines(String input) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    " + input);
        System.out.println("    ____________________________________________________________\n");
    }
}
