
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Task {

    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
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

class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

public class Bob {

    private static void dialogue(String input) {
        System.out.println("___________________________________\n");
        System.out.println(input);
        System.out.println("___________________________________\n");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Task> messages = new ArrayList<Task>();

        dialogue("Hello! I'm Bob\n"
                + "What can I do for you?");

        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                dialogue("Bye. Hope to see you again soon!");
                break;
            
            } else if (userInput.equalsIgnoreCase("list")) {
                String out = "Here are the tasks in your list: \n";
                for (int i = 0; i < messages.size(); i++) {
                    out += (i + 1) + ". " + messages.get(i) + "\n";
                }
                dialogue(out);
            
            } else if (userInput.startsWith("mark")) {
                String[] words = userInput.split(" ");
                int index = Integer.parseInt(words[1]) - 1;
                messages.get(index).markAsDone();
                dialogue("Nice! I've marked this task as done: \n" + messages.get(index));
           
            } else if (userInput.startsWith("unmark")) {
                String[] words = userInput.split(" ");
                int index = Integer.parseInt(words[1]) - 1;
                messages.get(index).unmarkAsDone();
                dialogue("OK, I've marked this task as not done yet: \n" + messages.get(index));

            } else if (userInput.startsWith("todo")) {
                // Example: todo read book
                String description = userInput.substring(5);
                Todo todo = new Todo(description);
                messages.add(todo);
                dialogue("Got it. I've added this task: \n" + todo + "\nNow you have " + messages.size() + " tasks in the list.");

            } else if (userInput.startsWith("deadline")) {
                // Example: deadline return book /by Sunday
                String[] words = userInput.split(" /by ");
                String description = words[0].substring(9);
                String by = words[1];
                Deadline deadline = new Deadline(description, by);
                messages.add(deadline);
                dialogue("Got it. I've added this task: \n" + deadline + "\nNow you have " + messages.size() + " tasks in the list.");

            } else if (userInput.startsWith("event")) {
                // Example: event project meeting /from Mon 2pm /to 4pm
                String[] words = userInput.split(" /from ");
                String[] words2 = words[1].split(" /to ");
                String description = words[0].substring(6);
                String from = words2[0];
                String to = words2[1];
                Event event = new Event(description, from, to);
                messages.add(event);
                dialogue("Got it. I've added this task: \n" + event + "\nNow you have " + messages.size() + " tasks in the list.");

            } else {
                dialogue("What?");
            }
        }

        scanner.close();
    }
}
