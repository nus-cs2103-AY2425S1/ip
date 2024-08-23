import java.util.Scanner;
import java.util.ArrayList;

public class Michael {
    private static final String border = "--------------------------------------";
    private static void printer(String text) { // Function to help print each interaction
        System.out.println(border);
        System.out.println(text);
        System.out.println(border);
    }

    private static ArrayList<Task> tasks = new ArrayList<>(); // store user inputs

    public static void main(String[] args) {
        Scanner user = new Scanner(System.in); // scanner for user input

        // Greet user first
        printer("Hello! I'm Michael. \n" + "What can I do for you?");

        // Read user's input
        while (true) {
            String input = user.nextLine();
            if (input.equals("bye")) { // special bye command to exit
                break;
            }
            processor(input);
        }

        // Exit
        printer("Bye. Hope to see you again soon!");
    }

    // Function to match command to required actions
    private static void processor(String input) {
        if (input.length() > 4 && input.substring(0, 4).equals("mark")) { // mark a task as done
            int index = Integer.valueOf(input.substring(5));
            Task target = tasks.get(index - 1);
            target.doTask();
            printer("Nice! I've marked this task as done:\n" + "  " + target);
        } else if (input.length() > 6 && input.substring(0, 6).equals("unmark")) { // unmark a task
            int index = Integer.valueOf(input.substring(7));
            Task target = tasks.get(index - 1);
            target.undoTask();
            printer("OK, I've marked this task as not done yet:\n" + "  " + target);
        } else if (input.equals("list")) { // list user inputs thus far
            String list = "";
            for (int i = 0; i < tasks.size(); i++) {
                String elem = String.valueOf(i + 1) + ". " + tasks.get(i) + "\n";
                list = list.concat(elem);
            }
            printer(list.substring(0, list.length() - 1)); // substring to remove last line break
        } else { // new task to be added
            tasks.add(new Task(input));
            printer("added: " + input);
        }
    }
}
