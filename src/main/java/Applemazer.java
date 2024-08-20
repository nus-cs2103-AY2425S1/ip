import java.util.Scanner;
import java.util.ArrayList;

public class Applemazer {
    Scanner sc = new Scanner(System.in);
    static ArrayList<Task> tasks = new ArrayList<>();

    private static class Task {
        private final String description;
        private boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return isDone ? "[X] " : "[ ] ";
        }

        public void setDone() {
            isDone = true;
        }

        public void setUndone() {
            isDone = false;
        }

        @Override
        public String toString() {
            return description;
        }

        public void printTaskAddedMessage() {
            System.out.println("Got it. I've added this task: ");
            System.out.println("    " + this.getStatusIcon() + this);
            System.out.println("Now you have " + tasks.size() + " tasks in the list. \n");
        }
    }

    private static class Todo extends Task {
        public Todo(String description) {
            super(description);
        }

        @Override
        public String getStatusIcon() {
            return "[T]" + super.getStatusIcon();
        }
    }

    private static class Deadline extends Task {
        private final String deadline;

        public Deadline(String description, String deadline) {
            super(description);
            this.deadline = deadline;
        }

        @Override
        public String getStatusIcon() {
            return "[D]" + super.getStatusIcon();
        }

        @Override
        public String toString() {
            return super.description + " (by: " + deadline + ") ";
        }
    }

    private static class Event extends Task {
        private final String from, to;

        public Event(String description, String from, String to) {
            super(description);
            this.from = from;
            this.to = to;
        }

        @Override
        public String getStatusIcon() {
            return "[E]" + super.getStatusIcon();
        }

        @Override
        public String toString() {
            return super.description + " (from: " + from + " to: " + to + ") ";
        }
    }

    private void greeting() {
        String greeting = "Hello! I'm Applemazer.\nWhat can I do for you?\n";
        System.out.println(greeting);
    }

    private void farewell() {
        String farewell = "Bye. Hope to see you again soon!";
        System.out.println(farewell);
    }

    private void process() {
        boolean processing = true;
        Task task;
        int taskNumber;
        String[] split;
        while (processing) {
            String command = sc.next();
            switch (command) {
                case "bye" :
                    processing = false;
                    break;
                case "list" :
                    System.out.println("Here are the tasks in your list: ");
                    for (int i = 0; i < tasks.size(); ++i) {
                        task = tasks.get(i);
                        System.out.println((i+1) + "." + task.getStatusIcon() + task);
                    }
                    System.out.println(); // Leave empty line.
                    break;
                case "mark" :
                    taskNumber = sc.nextInt()-1;
                    assert taskNumber < tasks.size(): "Index exceeds number of tasks. ";
                    task = tasks.get(taskNumber);
                    task.setDone();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println("    " + task.getStatusIcon() + task + "\n");
                    break;
                case "unmark" :
                    taskNumber = sc.nextInt()-1;
                    assert taskNumber < tasks.size(): "Index exceeds number of tasks. ";
                    task = tasks.get(taskNumber);
                    task.setUndone();
                    System.out.println("OK, I've marked this task as not done yet: ");
                    System.out.println("    " + task.getStatusIcon() + task + "\n");
                    break;
                case "todo" :
                    command = sc.nextLine();
                    task = new Todo(command);
                    tasks.add(task);
                    task.printTaskAddedMessage();
                    break;
                case "deadline" :
                    split = sc.nextLine().split("/by");
                    for (int i = 0; i < split.length; ++i) {
                        split[i] = split[i].trim();
                    }
                    task = new Deadline(split[0], split[1]);
                    tasks.add(task);
                    task.printTaskAddedMessage();
                    break;
                case "event" :
                    split = sc.nextLine().split("/from | /to ");
                    for (int i = 0; i < split.length; ++i) {
                        split[i] = split[i].trim();
                    }
                    task = new Event(split[0], split[1], split[2]);
                    tasks.add(task);
                    task.printTaskAddedMessage();
                    break;
                default:
                    command += sc.nextLine(); // Get rest of line if first word not valid command.
                    task = new Task(command);
                    tasks.add(task);
                    task.printTaskAddedMessage();
            }
        }
    }

    public static void main(String[] args) {
        Applemazer chatBot = new Applemazer();
        chatBot.greeting();
        chatBot.process();
        chatBot.farewell();
    }
}
