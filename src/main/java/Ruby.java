import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Ruby {

    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static final String FILE_PATH = "./data/ruby.txt";
    public static void main(String[] args) {

        loadTasks();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Ruby\nWhat can I do for you?");

        while (true) {
            String command = scanner.nextLine().trim();
            try {
                if (command.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    saveTasks();
                    break;
                } else if (command.equals("list")) {
                    listTasks();
                } else if (command.startsWith("mark ")) {
                    markTask(command);
                    saveTasks();
                } else if (command.startsWith("unmark ")) {
                    unmarkTask(command);
                    saveTasks();
                } else if (command.startsWith("todo")) {
                    addToDoTask(command);
                    saveTasks();
                } else if (command.startsWith("deadline")) {
                    addDeadlineTask(command);
                    saveTasks();
                } else if (command.startsWith("event")) {
                    addEventTask(command);
                    saveTasks();
                } else if (command.startsWith("delete")) {
                    deleteTask(command);
                    saveTasks();
                } else {
                    throw new RubyException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (RubyException e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }

    private static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + "." + tasks.get(i));
        }
    }

    private static void markTask(String command) throws RubyException {
        int taskNumber = Integer.parseInt(command.split(" ")[1]) - 1;
        if (taskNumber >= 0 && taskNumber < tasks.size()) {
            tasks.get(taskNumber).markAsDone();
            System.out.println("     Nice! I've marked this task as done:");
            System.out.println("       " + tasks.get(taskNumber));
        } else {
            throw new RubyException("     Invalid task number.");
        }
    }

    private static void unmarkTask(String command) throws RubyException {
        int taskNumber = Integer.parseInt(command.split(" ")[1]) - 1;
        if (taskNumber >= 0 && taskNumber < tasks.size()) {
            tasks.get(taskNumber).markAsNotDone();
            System.out.println("     OK, I've marked this task as not done yet:");
            System.out.println("       " + tasks.get(taskNumber));
        } else {
            throw new RubyException("     Invalid task number.");
        }
    }

    private static void addToDoTask(String command) throws RubyException {
        String[] parts = command.split(" ", 2);
        if (parts.length < 2 || parts[1].isEmpty()) {
            throw new RubyException("OOPS!!! The description of a todo cannot be empty.");
        }
        tasks.add(new Todo(parts[1]));
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + tasks.get(tasks.size() - 1));
        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void addDeadlineTask(String command) throws RubyException {
        String[] parts = command.split(" /by ", 2);
        if (parts.length < 2 || parts[1].isEmpty()) {
            throw new RubyException("OOPS!!! The description of a deadline must include a date/time.");
        }
        tasks.add(new Deadline(parts[0].substring(9), parts[1]));
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + tasks.get(tasks.size() - 1));
        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void addEventTask(String command) throws RubyException {
        String[] parts = command.split(" /from ", 2);
        if (parts.length < 2 || !parts[1].contains(" /to ")) {
            throw new RubyException("OOPS!!! The description of an event must include start and end times.");
        }
        String[] eventParts = parts[1].split(" /to ", 2);
        tasks.add(new Event(parts[0].substring(6), eventParts[0], eventParts[1]));
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + tasks.get(tasks.size() - 1));
        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void deleteTask(String command) throws RubyException {
        int taskNumber = Integer.parseInt(command.split(" ")[1]) - 1;
        if (taskNumber >= 0 && taskNumber < tasks.size()) {
            Task removedTask = tasks.remove(taskNumber);
            System.out.println("     Noted. I've removed this task:");
            System.out.println("       " + removedTask);
            System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
        } else {
            throw new RubyException("     Invalid task number.");
        }
    }

    private static void saveTasks() {
        try {
            Path path = Paths.get("./data");
            if (!Files.exists(path)) {
                Files.createDirectory(path);
            }

            FileWriter writer = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                writer.write(task.toDataString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks to the file.");
        }
    }

    private static void loadTasks() {
        try {
            Path path = Paths.get(FILE_PATH);
            if (!Files.exists(path)) {
                return;
            }

            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                String[] parts = line.split(" \\| ");
                Task task;
                switch (parts[0]) {
                    case "T":
                        task = new Todo(parts[2]);
                        break;
                    case "D":
                        task = new Deadline(parts[2], parts[3]);
                        break;
                    case "E":
                        task = new Event(parts[2], parts[3], parts[4]);
                        break;
                    default:
                        continue;
                }
                if (parts[1].equals("1")) {
                    task.markAsDone();
                }
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks from the file.");
        }
    }
}