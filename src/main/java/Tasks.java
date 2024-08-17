import java.util.ArrayList;
import java.util.List;

public class Tasks {
    private List<Task> tasks;
    public Tasks() {
        tasks = new ArrayList<>();
    }
    public void addTask(String task) {
        tasks.add(new Task(task));
    }
    public void printTasks() {
        int count = 1;
        for (Task task : tasks) {
            System.out.print(count + ". ");
            task.printTask();
            System.out.println();
            count++;
        }
    }
    public void markTask(int index) {
        tasks.get(index - 1).completeTask();
    }

    public void printTask(int index) {
        tasks.get(index - 1).printTask();
        System.out.println();
    }
    public void unmarkTask(int index) {
        tasks.get(index - 1).uncompleteTask();
    }

    private class Task {
        private Boolean isComplete = false;
        private String description;
        private Task(String description) {
            this.description = description;
        }

        private void printTask() {
            String msg = "[";
            if (isComplete) {
                msg += "X] ";
            } else {
                msg += " ] ";
            }
            msg += this.description;
            System.out.print(msg);
        }

        private void completeTask() {
            this.isComplete = true;
        }
        private void uncompleteTask() {
            this.isComplete = false;
        }
    }
}
