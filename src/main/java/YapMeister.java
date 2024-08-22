import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class YapMeister {
    final static int MAX_TASKS = 100;
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                               Hello I'm YapMeister
                               YAPYAPYAPYAP
                               """);
        String input = "";
        boolean running = true;
        ArrayList<Task> tasks = new ArrayList<>();
        String[] inputParsed;
        while (running) {
            System.out.print("\n");
            input = scanner.nextLine();
            String[] command = input.split(" ");
            try {
                switch (command[0]) {
                    case "bye":
                        running = false;
                        break;
                    case "list":
                        int i = 0;
                        for (Task task : tasks) {
                            System.out.println(String.format("%d. %s", i + 1, task.toString()));
                            i++;
                        }
                        break;
                    case "mark":
                        int index = parseInt(command[1]) - 1;
                        if (index >= tasks.size() || index < 0) {
                            throw new InvalidMarkException("No task at that index");
                        }
                        tasks.get(index).setCompleted(true);
                        System.out.println("You did this:");
                        System.out.println(tasks.get(index).toString());
                        break;
                    case "unmark":
                        int umindex = parseInt(command[1]) - 1;
                        if (umindex >= tasks.size() || umindex < 0) {
                            throw new InvalidMarkException("No task at that index");
                        }
                        tasks.get(umindex).setCompleted(false);
                        System.out.println("You undid this:");
                        System.out.println(tasks.get(umindex).toString());
                        break;
                    case "todo":
                    case "deadline":
                    case "event":
                        Task task = null;
                        task = createTask(command[0], input);
                        System.out.println("Added:");
                        task.toString();
                        tasks.add(task);
                        System.out.println(String.format("You have %d tasks", tasks.size()));
                        break;
                    default:
                        System.out.println("Invalid input please yap yapology");
                }
            } catch (InvalidDescriptionException | InvalidMarkException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format");
            }
        }
        System.out.println("Fine. Bye. Leave and never return");
    }

    private static Task createTask(String type, String input) throws InvalidDescriptionException {
        Task task = null;
        String[] format;
        switch (type) {
            case "todo":
                format = input.split("todo ");
                if (format.length <= 1) {
                    throw new InvalidDescriptionException("Invalid Task description");
                }
                task = new ToDo(format[1]);
                break;
            case "deadline":
                format = input.split("deadline | /by ");
                if (format.length <= 2) {
                    throw new InvalidDescriptionException("Invalid Task description");
                }
                task = new Deadline(format[1], format[2]);
                break;
            case "event":
                format = input.split("event | /from | /to ");
                if (format.length <= 3) {
                    throw new InvalidDescriptionException("Invalid Task description");
                }
                task = new Event(format[1], format[2], format[3]);
                break;
        }
        return task;
    }
}
