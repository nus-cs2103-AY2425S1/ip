import java.util.Scanner;

public class MySutong {
    public static void main(String[] args) {
        String horizontalLine = "____________________________________________________________";
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100]; // Array to store tasks
        int taskCount = 0; // Counter to keep track of the number of tasks

        System.out.println(horizontalLine);
        System.out.println("Hello! I'm MySutong");
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);

        String input;
        while (true) {
            input = scanner.nextLine();

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println(horizontalLine);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                System.out.println(horizontalLine);
            } else if (input.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(input.substring(5)) - 1; // Extract the number and convert to integer
                if (taskNumber >= 0 && taskNumber < taskCount) {
                    tasks[taskNumber].markAsDone();
                    System.out.println(horizontalLine);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks[taskNumber]);
                    System.out.println(horizontalLine);
                }
            } else if (input.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(input.substring(7)) - 1; // Extract the number and convert to integer
                if (taskNumber >= 0 && taskNumber < taskCount) {
                    tasks[taskNumber].unmark();
                    System.out.println(horizontalLine);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(tasks[taskNumber]);
                    System.out.println(horizontalLine);
                }
            } else {
                tasks[taskCount] = new Task(input);
                taskCount++;
                System.out.println(horizontalLine);
                System.out.println("added: " + input);
                System.out.println(horizontalLine);
            }
        }

        System.out.println(horizontalLine);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);

        scanner.close();
    }
}

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
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
