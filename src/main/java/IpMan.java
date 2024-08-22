import java.util.Arrays;
import java.util.Scanner;

public class IpMan {
    public static final String SEPARATOR = "____________________________________________________________";
    public static void main(String[] args) {
        Task[] list = new Task[100];
        int nextIdx = 0;
        System.out.println(SEPARATOR);
        System.out.println("Hello from Ip Man!\nWhat can I do for you?");
        System.out.println(SEPARATOR);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(SEPARATOR);
            if (line.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(SEPARATOR);
                break;
            }
            try {
                switch (line.split(" ")[0]) {
                    case "list": {
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < nextIdx; i++) {
                            System.out.println((i + 1) + ". " + list[i]);
                        }
                        break;
                    }
                    case "unmark": {
                        int idx = Integer.parseInt(line.split(" ")[1]) - 1;
                        if (idx < 0 || idx > nextIdx) {
                            continue;
                        }
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(list[idx].unmarkStatus());
                        break;
                    }
                    case "mark": {
                        int idx = Integer.parseInt(line.split(" ")[1]) - 1;
                        if (idx < 0 || idx > nextIdx) {
                            continue;
                        }
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(list[idx].markStatus());
                        break;
                    }
                    case "todo": {
                        if (!line.contains(" ") || line.split(" ", 2)[1].isEmpty()) {
                            throw new CommandException("The description of the todo must not be empty.");
                        }
                        String desc = line.split(" ", 2)[1];
                        list[nextIdx++] = new Todo(desc);
                        break;
                    }
                    case "deadline": {
                        if (!line.contains(" ") || line.split(" ", 2)[1].isEmpty()) {
                            throw new CommandException("The description of the deadline must not be empty.");
                        }
                        String[] tail = line.split(" ", 2)[1].split(" /by ");
                        list[nextIdx++] = new Deadline(tail[0], tail[1]);
                        break;
                    }
                    case "event": {
                        if (!line.contains(" ") || line.split(" ", 2)[1].isEmpty()) {
                            throw new CommandException("The description of the event must not be empty.");
                        }
                        String desc = line.split(" ", 2)[1].split(" /from ")[0];
                        String from = line.split(" ", 2)[1].split(" /from ")[1].split(" /to ")[0];
                        String to = line.split(" ", 2)[1].split(" /from ")[1].split(" /to ")[1];
                        list[nextIdx++] = new Event(desc, from, to);
                        break;
                    }
                    default: {
                        throw new CommandException("Invalid command");
                    }
                }
                if (Arrays.asList("todo", "deadline", "event").contains(line.split(" ")[0])) {
                    System.out.println("Got it. I've added this task:");
                    System.out.println(list[nextIdx - 1]);
                    System.out.println("Now you have " + nextIdx + " tasks in the list.");
                }
            } catch (CommandException ce) {
                System.out.println(ce.getMessage());
            } catch (Exception e) {
                System.out.println("unspecified syntax error");
            }
            System.out.println(SEPARATOR);
        }
    }
}
