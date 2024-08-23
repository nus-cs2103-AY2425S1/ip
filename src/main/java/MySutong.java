import java.util.ArrayList;
import java.util.Scanner;

public class MySutong {
    public static void main(String[] args) {
        String horizontalLine = "____________________________________________________________";
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println(horizontalLine);
        System.out.println("Hello! I'm MySutong");
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);

        String input;
        while (true) {
            input = scanner.nextLine();

            try {
                if (input.equals("bye")) {
                    break;
                } else if (input.equals("list")) {
                    System.out.println(horizontalLine);
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                    System.out.println(horizontalLine);
                } else if (input.startsWith("mark ")) {
                    int taskNumber = Integer.parseInt(input.substring(5)) - 1;
                    if (taskNumber >= 0 && taskNumber < tasks.size()) {
                        tasks.get(taskNumber).markAsDone();
                        System.out.println(horizontalLine);
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(tasks.get(taskNumber));
                        System.out.println(horizontalLine);
                    } else {
                        throw new InvalidTaskNumberException("Task number " + (taskNumber + 1) + " is out of range.");
                    }
                } else if (input.startsWith("unmark ")) {
                    int taskNumber = Integer.parseInt(input.substring(7)) - 1;
                    if (taskNumber >= 0 && taskNumber < tasks.size()) {
                        tasks.get(taskNumber).unmark();
                        System.out.println(horizontalLine);
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(tasks.get(taskNumber));
                        System.out.println(horizontalLine);
                    } else {
                        throw new InvalidTaskNumberException("Task number " + (taskNumber + 1) + " is out of range.");
                    }
                } else if (input.startsWith("todo ")) {
                    String description = input.substring(5).trim();
                    if (description.isEmpty()) {
                        throw new NoDescriptionException("The description of a todo cannot be empty.");
                    }
                    tasks.add(new Todo(description));
                    System.out.println(horizontalLine);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println(horizontalLine);
                } else if (input.startsWith("deadline ")) {
                    String[] parts = input.substring(9).split("/by");
                    if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                        throw new NoDescriptionException("The description or deadline of a task cannot be empty.");
                    }
                    String description = parts[0].trim();
                    String by = parts[1].trim();
                    tasks.add(new Deadline(description, by));
                    System.out.println(horizontalLine);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println(horizontalLine);
                } else if (input.startsWith("event ")) {
                    String[] parts = input.substring(6).split("/from|/to");
                    if (parts.length < 3 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
                        throw new NoDescriptionException("The description, start time, or end time of an event cannot be empty.");
                    }
                    String description = parts[0].trim();
                    String from = parts[1].trim();
                    String to = parts[2].trim();
                    tasks.add(new Event(description, from, to));
                    System.out.println(horizontalLine);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println(horizontalLine);
                } else if (input.startsWith("delete ")) {
                    int taskNumber = Integer.parseInt(input.substring(7)) - 1;
                    if (taskNumber >= 0 && taskNumber < tasks.size()) {
                        Task removedTask = tasks.remove(taskNumber);
                        System.out.println(horizontalLine);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(removedTask);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println(horizontalLine);
                    } else {
                        throw new InvalidTaskNumberException("Task number " + (taskNumber + 1) + " is out of range.");
                    }
                } else {
                    throw new UnknownCommandException("I'm sorry, but I don't know what that means.");
                }
            } catch (SutongException e) {
                System.out.println(horizontalLine);
                System.out.println("Error: " + e.getMessage());
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
    private String description;
    private boolean isDone;

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

    public void unmark() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

class Event extends Task {
    private String from;
    private String to;

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

class SutongException extends Exception {
    public SutongException(String message) {
        super(message);
    }
}

class NoDescriptionException extends SutongException {
    public NoDescriptionException(String message) {
        super(message);
    }
}

class InvalidTaskNumberException extends SutongException {
    public InvalidTaskNumberException(String message) {
        super(message);
    }
}

class UnknownCommandException extends SutongException {
    public UnknownCommandException(String message) {
        super(message);
    }
}
