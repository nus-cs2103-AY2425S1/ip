import java.util.Scanner;

public class ArsenBot {

    private static final Task[] mem = new Task[105];
    private static int cnt = 0;

    public static void parseCommand(String input) throws TaskManagerException {
        if (input.startsWith("todo")) {
            if (input.length() <= 5) {
                throw new TaskManagerException("Error: The description of a 'todo' cannot be empty.");
            }
            String description = input.substring(5).trim();
            mem[++ cnt] = new Todo(description);
            System.out.println("Got it. I've added this task:");
            System.out.println("\t" + mem[cnt].toString());
            System.out.println("Now you have " + cnt + " tasks in the list");
        } else if (input.startsWith("deadline")) {
            if (input.length() <= 9) {
                throw new TaskManagerException("Error: The deadline command requires both a task description and a '/by' date.");
            }
            String[] parts = input.substring(9).split(" /by ");
            if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                throw new TaskManagerException("Error: The deadline command requires both a task description and a '/by' date.");
            }
            mem[++ cnt] = new Deadline(parts[0], parts[1]);
            System.out.println("Got it. I've added this task:");
            System.out.println("\t" + mem[cnt].toString());
            System.out.println("Now you have " + cnt + " tasks in the list");
        } else if (input.startsWith("event")) {
            if(input.length() <= 6) {
                throw new TaskManagerException("Error: The event command requires a description, start time, and end time.");
            }
            String[] parts = input.substring(6).split(" /from ");
            if (parts.length < 2 || parts[0].trim().isEmpty() || !parts[1].contains(" /to ")) {
                throw new TaskManagerException("Error: The event command requires a description, start time, and end time.");
            }
            String description = parts[0];
            String[] timeParts = parts[1].split(" /to ");
            if (timeParts[1].isEmpty()) {
                throw new TaskManagerException("Error: The event command requires a description, start time, and end time.");
            }
            mem[++ cnt] = new Event(description, timeParts[0], timeParts[1]);
            System.out.println("Got it. I've added this task:");
            System.out.println("\t" + mem[cnt].toString());
            System.out.println("Now you have " + cnt + " tasks in the list");
        } else {
            throw new TaskManagerException("Error: Unrecognized command. Please enter a valid task command.");
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm ArsenBot");
        System.out.println("What can I do for you?\n");

        Scanner reader = new Scanner(System.in);
        while (true) {
            String input = reader.nextLine();

            if (input.startsWith("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                break;
            }

            else if (input.startsWith("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= cnt; i ++) {
                    System.out.println(i + "." + mem[i]);
                }
                continue;
            }

            else if (input.startsWith("mark")) {
                System.out.println("Nice! I've marked this task as done:");
                int pos = Integer.parseInt(input.substring(5));
                mem[pos].markAsDone();
                System.out.println(mem[pos]);
                continue;
            }

            else if (input.startsWith("unmark")) {
                System.out.println("OK, I've marked this task as not done yet:");
                int pos = Integer.parseInt(input.substring(7));
                mem[pos].markAsUndone();
                System.out.println(mem[pos]);
                continue;
            } else {
                try {
                    parseCommand(input);
                } catch (TaskManagerException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
