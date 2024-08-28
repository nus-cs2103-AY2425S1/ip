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
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

public class Hien {
    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Hien");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String input = scanner.nextLine().trim();

            System.out.println("____________________________________________________________");

            if (input.equalsIgnoreCase("bye")) {
                System.out.println(" Bye. Hope to see you again soon!");
                break;
            } else if (input.equalsIgnoreCase("list")) {
                listTasks();
            } else if (input.startsWith("mark ")) {
                markTask(input, true);
            } else if (input.startsWith("unmark ")) {
                markTask(input, false);
            } else {
                addTask(input);
            }

            System.out.println("____________________________________________________________");
        }

        scanner.close();
    }

    private static void addTask(String description) {
        Task newTask = new Task(description);
        tasks.add(newTask);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + newTask);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println(" There are no tasks in your list.");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + tasks.get(i));
            }
        }
    }

    private static void markTask(String input, boolean isDone) {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            if (index >= 0 && index < tasks.size()) {
                Task task = tasks.get(index);
                if (isDone) {
                    task.markAsDone();
                    System.out.println(" Nice! I've marked this task as done:");
                } else {
                    task.markAsUndone();
                    System.out.println(" OK, I've marked this task as not done yet:");
                }
                System.out.println("   " + task);
            } else {
                System.out.println(" Invalid task number. Please try again.");
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println(" Invalid command. Please use 'mark <task number>' or 'unmark <task number>'.");
        }
    }
}