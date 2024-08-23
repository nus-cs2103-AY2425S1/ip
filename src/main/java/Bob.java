
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
            } else {
                dialogue("Added: " + userInput);
                messages.add(new Task(userInput));
            }
        }

        scanner.close();
    }
}
