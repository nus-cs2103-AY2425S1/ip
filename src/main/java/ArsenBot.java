import java.util.Scanner;

public class ArsenBot {

    private static final Task[] mem = new Task[105];
    private static int cnt = 0;

    public static void parseCommand(String input) {
        System.out.println("Got it. I've added this task:");
        if (input.startsWith("todo")) {
            String description = input.substring(5);
            mem[++ cnt] = new Todo(description);
        } else if (input.startsWith("deadline")) {
            String[] parts = input.substring(9).split(" /by ");
            mem[++ cnt] = new Deadline(parts[0], parts[1]);
        } else if (input.startsWith("event")) {
            String[] parts = input.substring(6).split(" /from ");
            String description = parts[0];
            String[] timeParts = parts[1].split(" /to ");
            mem[++ cnt] = new Event(description, timeParts[0], timeParts[1]);
        }
        System.out.println("\t" + mem[cnt].toString());
        System.out.println("Now you have " + cnt + " tasks in the list");
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
                parseCommand(input);
                continue;
            }
        }
    }
}
