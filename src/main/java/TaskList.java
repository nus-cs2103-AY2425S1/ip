import java.util.*;
public class TaskList {
    private List<Task> tasks;

    private class Task {
        protected String name;
        protected boolean isDone;

        public Task(String description) {
            this.name = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }
        public void complete() {
            this.isDone = true;
        }
        public void uncomplete() {
            this.isDone = false;
        }
        @Override
        public String toString() {
            return "[" + this.getStatusIcon() + "] " + this.name;
        }
    }

    public TaskList() {
        tasks = new ArrayList<>();
    }
    public void addTask(String task) {
        if (!task.equals("list")) {
            tasks.add(new Task(task));
            System.out.println("    added: " + task);
        }
        else printTasks();
    }
    public void markTaskAsDone(int i) {
        if (i <= tasks.size()) tasks.get(i-1).complete();
    }
    public void markTaskAsUndone(int i) {
        if (i <= tasks.size()) tasks.get(i-1).uncomplete();
    }
    public String getTask(int i) {
        if (i <= tasks.size()) return tasks.get(i-1).toString();
        else return "";
    }
    public void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("    " + (i + 1) + "." + tasks.get(i).toString());
        }
    }
}
