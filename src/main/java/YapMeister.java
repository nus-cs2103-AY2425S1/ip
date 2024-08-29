import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class YapMeister {
    final static int MAX_TASKS = 100;

    public static void main(String[] args) {
        boolean isRunning = true;
        File taskFile;
        try {
            taskFile = loadTaskFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            isRunning = false;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                Hello I'm YapMeister
                YAPYAPYAPYAP
                """);
        String input = "";
        ArrayList<Task> tasks = new ArrayList<>();
        while (isRunning) {
            System.out.print("\n");
            input = scanner.nextLine();
            String[] command = input.split(" ");
            try {
                switch (command[0]) {
                case "bye":
                    isRunning = false;
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
                    //Fallthrough
                case "deadline":
                    //Fallthrough
                case "event":
                    Task task = null;
                    task = createTask(command[0], input);
                    System.out.println("Added:");
                    tasks.add(task);
                    System.out.println(String.format("You have %d tasks", tasks.size()));
                    break;
                case "delete":
                    int dindex = parseInt(command[1]) - 1;
                    if (dindex >= tasks.size() || dindex < 0) {
                        throw new InvalidMarkException("No task at that index");
                    }
                    Task removedTask = tasks.remove(dindex);
                    System.out.println("Removed this task");
                    System.out.println(removedTask.toString());
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

    private static File loadTaskFile() throws IOException {
        //create filepath if it does not exist
        if (!Files.exists(Paths.get("./data/"))) {
            new File("./data/").mkdirs();
        }

        File file = new File("./data/tasks.txt");

        //create file if it does not exist
        file.createNewFile();
        return file;
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
