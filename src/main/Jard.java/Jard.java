import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Jard {

    public static class JardException extends RuntimeException {
        public JardException(String message) {
            super(message);
        }
    }

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

    public static class Todo extends Task {
        public Todo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    public static class Deadline extends Task {
        protected String by;

        public Deadline(String description, String by) {
            super(description);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }

    public static class Event extends Task {
        protected String from;
        protected String to;

        public Event(String description, String from, String to) {
            super(description);
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
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
        System.out.println(" Hello! I'm Jard.");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        String input;

        while (true) {
            input = scanner.nextLine().trim();
            String[] inputParts = input.split(" ", 2);
            String command = inputParts[0];
            try{
                if (command.equals("bye")) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    break;
                } else if (command.equals("list")) {
                    if (tasks.isEmpty()) {
                        throw new JardException("The task list is empty!");
                    }
                    System.out.println("____________________________________________________________");
                    System.out.println(" Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(" " + (i + 1) + ". " + tasks.get(i));
                    }
                    System.out.println("____________________________________________________________");
                } else if (command.equals("mark") || command.equals("unmark")) {
                    if (inputParts.length < 2) {
                        throw new JardException("Invalid command! Please specify the task number.");
                    }
                    int taskIndex;
                    try {
                        taskIndex = Integer.parseInt(inputParts[1]) - 1;
                    } catch (NumberFormatException e) {
                        throw new JardException("Invalid task number format!");
                    }
                    if (taskIndex < 0 || taskIndex >= tasks.size()) {
                        throw new JardException("Task number does not exist!");
                    }
                    Task task = tasks.get(taskIndex);
                    if (command.equals("mark")) {
                        task.markAsDone();
                        System.out.println("____________________________________________________________");
                        System.out.println("Nice! I've marked this task as done:");
                    } else {
                        task.markAsNotDone();
                        System.out.println("____________________________________________________________");
                        System.out.println("OK, I've marked this task as not done yet:");
                    }
                    System.out.println("   " + task);
                    System.out.println("____________________________________________________________");
                } else if (command.equals("delete")) {
                    if (inputParts.length < 2) {
                        throw new JardException("Invalid command! Please specify the task number.");
                    } else {
                        try {
                            int taskIndex = Integer.parseInt(inputParts[1]) - 1;
                            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                                Task removedTask = tasks.remove(taskIndex);
                                System.out.println("____________________________________________________________");
                                System.out.println(" Noted. I've removed this task:");
                                System.out.println("   " + removedTask);
                                System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                                System.out.println("____________________________________________________________");
                            } else {
                                throw new JardException("Task number does not exist!");
                            }
                        } catch (NumberFormatException e) {
                            throw new JardException("Invalid task number format!");
                        }
                    }
                } else if (command.equals("todo") && inputParts.length > 1) {
                    tasks.add(new Todo(inputParts[1]));
                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + tasks.get(tasks.size() - 1));
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else if (command.equals("deadline") && inputParts.length > 1) {
                    String[] details = inputParts[1].split("/by", 2);
                    if (details.length < 2) {
                        throw new JardException("Invalid format! Please specify the deadline with /by.");
                    }
                    tasks.add(new Deadline(details[0].trim(), details[1].trim()));
                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + tasks.get(tasks.size() - 1));
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else if (command.equals("event") && inputParts.length > 1) {
                    String[] details = inputParts[1].split("/from|/to");
                    if (details.length != 3) {
                        throw new JardException("Invalid format! Please specify the event with /from and /to.");
                    }

                    tasks.add(new Event(details[0].trim(), details[1].trim(), details[2].trim()));
                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + tasks.get(tasks.size() - 1));
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else {
                    throw new JardException("Invalid command.");
                }
            } catch (JardException e) {
                System.out.println("____________________________________________________________");
                System.out.println(" Error: Jard! " + e.getMessage());
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close();
    }
}
