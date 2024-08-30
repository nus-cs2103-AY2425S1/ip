import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

/**
 * Duck class is a chatbot that helps with tasks such as todo, event and deadline.
 */
public class Duck {
    private static final String FILE_PATH = "./data/duck.txt";
    public static void main(String[] args) throws EmptyToDoException, InvalidCommandException {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = Duck.loadData();

        String line = "____________________________________________________________";
        String welcomeMessage = "Hello! I'm Duck\nWhat can I do for you?";
        System.out.println(line + "\n" + welcomeMessage + "\n" + line);

        while (true) {
            String userCommand = scanner.nextLine();
            if (userCommand.equals("bye")) {
                break;
            }
            if (userCommand.equals("list")) {
                System.out.println(line + "\nHere are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    int num = i + 1;
                    System.out.println(num + ". " + list.get(i));
                }
                System.out.println(line);
            } else if (userCommand.startsWith("mark")) {
                int taskIndex = Integer.parseInt(userCommand.split(" ")[1]) - 1;
                Task task = list.get(taskIndex);
                task.mark();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(task);
                Duck.saveTasks(list);
            } else if (userCommand.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(userCommand.split(" ")[1]) - 1;
                Task task = list.get(taskIndex);
                task.unmark();
                System.out.println("OK, I've marked this task as not done yet: ");
                System.out.println(task);
                Duck.saveTasks(list);
            } else if (userCommand.startsWith("todo")) {
                if (userCommand.equals("todo")) {
                    throw new EmptyToDoException("Cannot have empty todo");
                }
                Task task = new ToDo(userCommand.split(" ", 2)[1]);
                list.add(task);
                System.out.println("Got it. I've added this task:");
                System.out.println(task);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
                Duck.saveTasks(list);
            } else if (userCommand.startsWith("event")) {
                String[] commandParts = userCommand.split("/from|/to");
                Task task = new Event(commandParts[0].split(" ", 2)[1], commandParts[1], commandParts[1]);
                list.add(task);
                System.out.println("Got it. I've added this task:");
                System.out.println(task);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
                Duck.saveTasks(list);
            } else if (userCommand.startsWith("deadline")) {
                String[] commandParts = userCommand.split("/by");
                LocalDate date = LocalDate.parse(commandParts[1]);
                Task task = new Deadline(commandParts[0].split(" ", 2)[1], date);
                list.add(task);
                System.out.println("Got it. I've added this task:");
                System.out.println(task);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
                Duck.saveTasks(list);
            } else if (userCommand.startsWith("delete")) {
                int taskIndex = Integer.parseInt(userCommand.split(" ")[1]) - 1;
                list.remove(taskIndex);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
                Duck.saveTasks(list);
            } else {
                throw new InvalidCommandException("Invalid command");
            }
        }

        String goodbyeMessage = "Bye. Hope to see you again soon!";
        System.out.println(line + "\n" + goodbyeMessage + "\n" + line);
        scanner.close();
    }

    private static void saveTasks(ArrayList<Task> list) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            for (Task task : list) {
                writer.write(task.toFileString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    private static ArrayList<Task> loadData() {
        File file = new File(FILE_PATH);
        ArrayList<Task> taskList = new ArrayList<>();

        if (!file.exists()) {
            return taskList;
        }

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" \\| ");
                Task task = switch (parts[0]) {
                    case "T" -> new ToDo(parts[2]);
                    case "D" -> new Deadline(parts[2], LocalDate.parse(parts[3]));
                    case "E" -> new Event(parts[2], parts[3], parts[4]);
                    default -> throw new InvalidCommandException("Invalid task type in file");
                };
                if (parts[1].equals("1")) {
                    task.mark();
                }
                taskList.add(task);
            }
        } catch (IOException | InvalidCommandException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return taskList;
    }
}
