import java.util.Scanner;
import java.util.ArrayList;

public class Applemazer {
    static Scanner sc = new Scanner(System.in);
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

    private void handleMarking(boolean shouldBeDone) {
        try {
            int taskNumber = Integer.parseInt(sc.nextLine().trim())-1; // Will throw error if non-integer or no input.
            Task task = tasks.get(taskNumber);

            if (shouldBeDone) {
                task.setDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("    " + task.getStatusIcon() + task + "\n");
            } else {
                task.setUndone();
                System.out.println("OK, I've marked this task as not done yet: ");
                System.out.println("    " + task.getStatusIcon() + task + "\n");
            }
        } catch (IndexOutOfBoundsException e) {
            if (tasks.isEmpty()) {
                System.out.println("You have no tasks to mark. ");
            } else {
                int size = tasks.size();
                String message = String.format("""
                                               You currently have %d tasks.
                                               Please enter a number between 1 and %d.
                                               """, size, size);
                System.out.println(message);
            }
        } catch (Exception e) {
            System.out.println("""
                               OOPS!!! You either have a non-integer input or no input at all.
                               Try 'mark <task number>'.
                               """);
        }
    }

    private void process() {
        boolean processing = true;
        Task task;
        String[] split;
        while (processing) {
            if (!sc.hasNext()) { break; } // For automated testing of text UIs.
            String command = sc.next();
            switch (command) {
                case "bye" :
                    try {
                        command = sc.nextLine().trim();
                        if (!command.isEmpty()) {
                            throw new Exception("OOPS!!! The description of bye should be empty. ");
                        }
                        processing = false;
                    } catch (Exception e) {
                            System.out.println(e.getMessage());
                    }
                    break;
                case "list" :
                    try {
                        command = sc.nextLine().trim();
                        if (!command.isEmpty()) {
                            throw new Exception("OOPS!!! The description of list should be empty. ");
                        }
                        System.out.println("Here are the tasks in your list: ");
                        for (int i = 0; i < tasks.size(); ++i) {
                            task = tasks.get(i);
                            System.out.println((i+1) + "." + task.getStatusIcon() + task);
                        }
                        System.out.println(); // Leave empty line.
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "mark" :
                    handleMarking(true);
                    break;
                case "unmark" :
                    handleMarking(false);
                    break;
                case "todo" :
                    try {
                        command = sc.nextLine().trim();
                        if (command.isEmpty()) {
                            throw new Exception("""
                                                OOPS!!! The description of a todo cannot be empty.
                                                Try todo <description>.
                                                """);
                        }

                        task = new Todo(command);
                        tasks.add(task);
                        task.printTaskAddedMessage();

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "deadline" :
                    try {
                        split = sc.nextLine().split("/by");
                        for (int i = 0; i < split.length; ++i) {
                            split[i] = split[i].trim();
                        }
                        task = new Deadline(split[0], split[1]);
                        tasks.add(task);
                        task.printTaskAddedMessage();
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("""
                                           OOPS!!! The description of deadline is wrong.
                                           Try 'deadline <description> /by <date>'.
                                           """);
                    }
                    break;
                case "event" :
                    try {
                        split = sc.nextLine().split("/from | /to ");
                        for (int i = 0; i < split.length; ++i) {
                            split[i] = split[i].trim();
                        }
                        task = new Event(split[0], split[1], split[2]);
                        tasks.add(task);
                        task.printTaskAddedMessage();
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("""
                                           OOPS!!! The description of event is wrong.
                                           Try 'event <description> /from <date1> /to <date2>'.
                                           """);
                    }
                    break;
                default:
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        Applemazer chatBot = new Applemazer();
        chatBot.greeting();
        chatBot.process();
        chatBot.farewell();
    }
}
