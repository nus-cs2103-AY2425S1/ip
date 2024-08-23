import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>(100);
    }

    public void addTask(Task task) {
        tasks.add(task);
        printLines("Got it, I've added this task to your list!\n" +
                "      " + task.toString() + "\n" + "    Wah bro... " + getTaskSize() + (getTaskSize() > 1 ? " tasks already!" : " task already!"));
    }

    public void deleteTask(int taskId) {
        if (taskId <= tasks.size() + 1) {
            Task task = tasks.get(taskId - 1);
            tasks.remove(task);
            printLines("Awesome bro! One task gone :D\n" +
                    "      " + task.toString() + "\n" + "    Wah bro... " + getTaskSize() + (getTaskSize() > 1 ? " tasks already!" : " task already!"));
        }
    }

    public int getTaskSize() {
        return tasks.size();
    }

    public void listTasks() {
        String taskString = "Here are the tasks in your list:\n" + "    ";

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
        if (taskId <= tasks.size() + 1) {
            tasks.get(taskId - 1).setComplete();
            printLines("Nice, I've marked this task as complete:\n" +
                    "       " + tasks.get(taskId - 1).toString());
        }
    }

    public void incompleteTask(int taskId) {
        if (taskId <= tasks.size() + 1) {
            tasks.get(taskId - 1).setIncomplete();
            printLines("Ok, I've marked this task as incomplete:\n" +
                    "       " + tasks.get(taskId - 1).toString());
        }
    }

    public void printLines(String input) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    " + input);
        System.out.println("    ____________________________________________________________\n");
    }
}
