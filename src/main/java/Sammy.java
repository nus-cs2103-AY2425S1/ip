import java.util.ArrayList;
import java.util.Scanner;


class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

public class Sammy {

    public static void main(String[] args) {
        String line = "____________________________________________________________";
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println(line);
        System.out.println(" Hello! I'm Sammy.");
        System.out.println(" What can I do for you?");
        System.out.println(line);

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println(line);
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            } else if (input.equals("list")) {
                System.out.println(line);
                System.out.println(" Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(" " + (i + 1) + "." + tasks.get(i));
                }
                System.out.println(line);
            } else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.substring(5).trim()) - 1;
                if (index >= 0 && index < tasks.size()) {
                    tasks.get(index).markAsDone();
                    System.out.println(line);
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println(" " + tasks.get(index));
                    System.out.println(line);
                } else {
                    System.out.println("Invalid task number.");
                }
            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.substring(7).trim()) - 1;
                if (index >= 0 && index < tasks.size()) {
                    tasks.get(index).markAsNotDone();
                    System.out.println(line);
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println(" " + tasks.get(index));
                    System.out.println(line);
                } else {
                    System.out.println("Invalid task number.");
                }
            } else {
                tasks.add(new Task(input));
                System.out.println(line);
                System.out.println(" added: " + input);
                System.out.println(line);
            }
        }
    }
}