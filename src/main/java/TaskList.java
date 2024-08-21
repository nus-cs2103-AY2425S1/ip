import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> list;
    private static final String line =
            "____________________________________________________________";

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void addToList(Task task) {
        this.list.add(task);
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        System.out.println(line);
    }

    public void markTask(int num) {
        if (num <= list.size() && num > 0) {
            Task currTask = list.get(num - 1);
            currTask.markDone();
            System.out.println(line);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(currTask.toString());
            System.out.println(line);
        } else {
            System.out.println(line);
            System.out.println("Invalid number");
            System.out.println(line);
        }
    }

    public void unmarkTask(int num) {
        if (num <= list.size() && num > 0) {
            Task currTask = list.get(num - 1);
            currTask.markUndone();
            System.out.println(line);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(currTask.toString());
            System.out.println(line);
        } else {
            System.out.println(line);
            System.out.println("Invalid number");
            System.out.println(line);
        }
    }

    public void delete(int num) {
        if (num <= list.size() && num > 0) {
            Task currTask = list.get(num - 1);
            this.list.remove(currTask);
            System.out.println(line);
            System.out.println("Noted. I've removed this task:");
            System.out.println(currTask.toString());
            System.out.println(" Now you have " + list.size() + " tasks in the list.");
            System.out.println(line);
        } else {
            System.out.println(line);
            System.out.println("Invalid number");
            System.out.println(line);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            Task currTask = list.get(i);
            int currNum = i + 1;
            sb.append(currNum).append(".").append(currTask.toString()).append("\n");
        }
        return sb.toString();
    }
}
