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
        printer("Hello! I'm Michael.\n" + "What can I do for you?");

        // Read user's input
        while (true) {
            String input = user.nextLine();
            if (input.equals("bye")) { // special bye command to exit
                break;
            }
            try {
                processor(input);
            } catch (MichaelException e) {
                System.out.println(e.getMessage());
            } finally {}
        }

        // Exit
        printer("Bye. Hope to see you again soon!");
    }

    // Function to match command to required actions
    private static void processor(String input) throws MichaelException {
        if (input.length() >= 4 && input.substring(0, 4).equals("mark")) { // mark a task as done
            if (input.length() < 6) { // no number given to mark
                throw new MichaelException("Enter integer position of task on list to mark. " +
                        "Use command list to check the position of the required task.");
            }
            int index = Integer.valueOf(input.substring(5));
            Task target = tasks.get(index - 1);
            target.doTask();
            printer("Nice! I've marked this task as done:\n" + "  " + target);
        } else if (input.length() >= 6 && input.substring(0, 6).equals("unmark")) { // unmark a task
            if (input.length() < 8) { // no number given to unmark
                throw new MichaelException("Enter integer position of task on list to unmark. " +
                        "Use command list to check the position of the required task.");
            }
            int index = Integer.valueOf(input.substring(7));
            Task target = tasks.get(index - 1);
            target.undoTask();
            printer("OK, I've marked this task as not done yet:\n" + "  " + target);
        } else if (input.equals("list")) { // list user inputs thus far
            String list = "Here are the tasks in your list:\n";
            for (int i = 0; i < tasks.size(); i++) {
                String elem = String.valueOf(i + 1) + ". " + tasks.get(i) + "\n";
                list = list.concat(elem);
            }
            printer(list.substring(0, list.length() - 1)); // substring to remove last line break
        } else if (input.length() >= 4 && input.substring(0, 4).equals("todo")) { // task of type todo to be added
            if (input.length() < 6) { // no task given
                throw new MichaelException("Enter a task to be done.");
            }
            ToDo curr = new ToDo(input.substring(5));
            tasks.add(curr);
            String message = "Got it. I've added this task:\n" + "  " + curr.toString() + "\n"
                    + "Now you have " + String.valueOf(tasks.size()) + " tasks in the list.";
            printer(message);
        } else if (input.length() >= 8 && input.substring(0, 8).equals("deadline")) { // task of type deadline to be added
            if (input.length() < 10) { // no deadline task given
                throw new MichaelException("Enter a valid task with a deadline.");
            }
            String task = input.substring(9);
            String[] parts = task.split("/");
            for (int i = 0; i < parts.length - 1; i++) {
                String curr = parts[i];
                parts[i] = curr.substring(0, curr.length() - 1);
            }
            Deadline curr = new Deadline(parts[0], parts[1].substring(3));
            tasks.add(curr);
            String message = "Got it. I've added this task:\n" + "  " + curr.toString() + "\n"
                    + "Now you have " + String.valueOf(tasks.size()) + " tasks in the list.";
            printer(message);
        } else if (input.length() >= 5 && input.substring(0, 5).equals("event")) {
            if (input.length() < 7) { // no event given
                throw new MichaelException("Enter a valid event.");
            }
            String task = input.substring(6);
            String[] parts = task.split("/");
            for (int i = 0; i < parts.length - 1; i++) {
                String curr = parts[i];
                parts[i] = curr.substring(0, curr.length() - 1);
            }
            Event curr = new Event(parts[0], parts[1].substring(5), parts[2].substring(3));
            tasks.add(curr);
            String message = "Got it. I've added this task:\n" + "  " + curr.toString() + "\n"
                    + "Now you have " + String.valueOf(tasks.size()) + " tasks in the list.";
            printer(message);
        } else if (input.length() >= 6 && input.substring(0, 6).equals("delete")) {
            if (input.length() < 8) { // no number given to delete
                throw new MichaelException("Enter integer position of task on list to delete. " +
                        "Use command list to check the position of the required task.");
            }
            int index = Integer.valueOf(input.substring(7));
            Task target = tasks.get(index - 1);
            tasks.remove(index - 1);
            printer("Noted. I've removed this task:\n" + "  " + target + "\n" +
                    "Now you have " + tasks.size() + " tasks in the list.");
        } else { // invalid command
            throw new MichaelException("Invalid command entered. Please enter one of the following valid commands: " +
                    "todo, deadline, event, mark, unmark, list, bye");
        }
    }
}
