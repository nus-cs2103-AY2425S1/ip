import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Jard {

    public static class Task {
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

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Jard");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        String input;

        while (true) {
            input = scanner.nextLine().trim();
            String[] inputParts = input.split(" ", 2);
            String command = inputParts[0];

            if (command.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (command.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(" " + (i + 1) + ". " + tasks.get(i));
                }
                System.out.println("____________________________________________________________");
            } else if (command.equals("mark") || command.equals("unmark")) {
                if (inputParts.length < 2) {
                    System.out.println(" Invalid command. Please specify the task number.");
                } else {
                    try {
                        int taskIndex = Integer.parseInt(inputParts[1]) - 1;
                        if (taskIndex >= 0 && taskIndex < tasks.size()) {
                            Task task = tasks.get(taskIndex);
                            if (command.equals("mark")) {
                                task.markAsDone();
                                System.out.println("____________________________________________________________");
                                System.out.println(" Nice! I've marked this task as done:");
                            } else {
                                task.markAsNotDone();
                                System.out.println("____________________________________________________________");
                                System.out.println(" OK, I've marked this task as not done yet:");
                            }
                            System.out.println("   " + task);
                            System.out.println("____________________________________________________________");
                        } else {
                            System.out.println(" Task number does not exist.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(" Invalid task number format.");
                    }
                }
            } else {
                tasks.add(new Task(input));
                System.out.println("____________________________________________________________");
                System.out.println(" added: " + input);
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close();
    }
}
