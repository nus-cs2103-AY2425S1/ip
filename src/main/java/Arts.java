import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Arts {
    private static final String FILE_PATH = "./data/tasks.txt";
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static final DateTimeFormatter[] INPUT_FORMATTERS = new DateTimeFormatter[]{
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm")
    };
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    public static void main(String[] args) {
        loadTasksFromFile();

        String logo = "     _    _____  _______  _____  \n"
                + "    / \\  |  __ \\|__   __|/ ____| \n"
                + "   / _ \\ | |__) |  | |  | (___   \n"
                + "  / ___ \\|  _  /   | |   \\___ \\  \n"
                + " /_/   \\_\\_| \\_\\   |_|   |_____/ \n";
        System.out.println("Hello from\n" + logo);

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Arts, your go-to Chatbot.");
        System.out.println(" What can I do for you today?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                String input = scanner.nextLine().trim();
                String[] parts = input.split(" ", 2);
                Command command = Command.fromString(parts[0]);

                switch (command) {
                    case BYE:
                        System.out.println("____________________________________________________________");
                        System.out.println("Bye! Hope to see you again soon!");
                        System.out.println("____________________________________________________________");
                        return;

                    case LIST:
                        System.out.println("____________________________________________________________");
                        if (tasks.isEmpty()) {
                            System.out.println("No tasks yet! Why not add some?");
                        } else {
                            System.out.println("Here are the tasks in your list:");
                            for (int i = 0; i < tasks.size(); i++) {
                                System.out.println((i + 1) + ". " + tasks.get(i));
                            }
                        }
                        System.out.println("____________________________________________________________");
                        break;

                    case MARK:
                        int markIndex = Integer.parseInt(parts[1]) - 1;
                        if (markIndex >= 0 && markIndex < tasks.size()) {
                            tasks.get(markIndex).markAsDone();
                            saveTasksToFile();
                            System.out.println("____________________________________________________________");
                            System.out.println("Nice! I've marked this task as done.");
                            System.out.println(" " + tasks.get(markIndex));
                            System.out.println("____________________________________________________________");
                        }
                        break;

                    case UNMARK:
                        int unmarkIndex = Integer.parseInt(parts[1]) - 1;
                        if (unmarkIndex >= 0 && unmarkIndex < tasks.size()) {
                            tasks.get(unmarkIndex).markAsNotDone();
                            saveTasksToFile();
                            System.out.println("____________________________________________________________");
                            System.out.println("OK, I've marked this task as not done.");
                            System.out.println(" " + tasks.get(unmarkIndex));
                            System.out.println("____________________________________________________________");
                        }
                        break;

                    case DELETE:
                        int deleteIndex = Integer.parseInt(parts[1]) - 1;
                        if (deleteIndex >= 0 && deleteIndex < tasks.size()) {
                            Task removedTask = tasks.remove(deleteIndex);
                            saveTasksToFile();
                            System.out.println("____________________________________________________________");
                            System.out.println("Noted. I've removed this task:");
                            System.out.println(" " + removedTask);
                            System.out.println("Now you have " + tasks.size() + " " + (tasks.size() == 1 ? "task" : "tasks") + " in the list.");
                            System.out.println("____________________________________________________________");
                        }
                        break;

                    case TODO:
                        if (parts.length < 2 || parts[1].trim().isEmpty()) {
                            throw new ArtsException("The description of a todo cannot be empty.");
                        }
                        tasks.add(new Todo(parts[1]));
                        saveTasksToFile();
                        System.out.println("____________________________________________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println(" " + tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + " " + (tasks.size() == 1 ? "task" : "tasks") + " in the list.");
                        System.out.println("____________________________________________________________");
                        break;

                    case DEADLINE:
                        if (parts.length < 2 || parts[1].trim().isEmpty()) {
                            throw new ArtsException("The description of a deadline cannot be empty.");
                        }
                        String[] deadlineParts = parts[1].split(" /by ");
                        if (deadlineParts.length < 2) {
                            throw new ArtsException("The deadline must have a /by date.");
                        }
                        LocalDateTime deadlineDate = null;
                        for (DateTimeFormatter formatter : INPUT_FORMATTERS) {
                            try {
                                deadlineDate = LocalDateTime.parse(deadlineParts[1], formatter);
                                break;
                            } catch (DateTimeParseException e) {
                                // Continue to the next formatter
                            }
                        }
                        if (deadlineDate == null) {
                            throw new ArtsException("Invalid date format for deadline. Please use yyyy-MM-dd HHmm or d/M/yyyy HHmm.");
                        }
                        tasks.add(new Deadline(deadlineParts[0], deadlineDate));
                        saveTasksToFile();
                        System.out.println("____________________________________________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println(" " + tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + " " + (tasks.size() == 1 ? "task" : "tasks") + " in the list.");
                        System.out.println("____________________________________________________________");
                        break;

                    case EVENT:
                        if (parts.length < 2 || parts[1].trim().isEmpty()) {
                            throw new ArtsException("The description of an event cannot be empty.");
                        }
                        String[] eventParts = parts[1].split(" /from | /to ");
                        if (eventParts.length < 3) {
                            throw new ArtsException("The event must have /from and /to times.");
                        }
                        LocalDateTime eventFromDate = null;
                        LocalDateTime eventToDate = null;
                        for (DateTimeFormatter formatter : INPUT_FORMATTERS) {
                            try {
                                eventFromDate = LocalDateTime.parse(eventParts[1], formatter);
                                eventToDate = LocalDateTime.parse(eventParts[2], formatter);
                                break;
                            } catch (DateTimeParseException e) {
                                // Continue to the next formatter
                            }
                        }
                        if (eventFromDate == null || eventToDate == null) {
                            throw new ArtsException("Invalid date format for event. Please use yyyy-MM-dd HHmm or d/M/yyyy HHmm for both /from and /to.");
                        }
                        tasks.add(new Event(eventParts[0], eventFromDate, eventToDate));
                        saveTasksToFile();
                        System.out.println("____________________________________________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println(" " + tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + " " + (tasks.size() == 1 ? "task" : "tasks") + " in the list.");
                        System.out.println("____________________________________________________________");
                        break;

                    default:
                        throw new ArtsException("I'm sorry, but I don't know what that means.");
                }
            } catch (ArtsException e) {
                System.out.println("____________________________________________________________");
                System.out.println(" OOPS!!! " + e.getMessage());
                System.out.println("____________________________________________________________");
            } catch (Exception e) {
                System.out.println("____________________________________________________________");
                System.out.println(" An unexpected error occurred: " + e.getMessage());
                System.out.println("____________________________________________________________");
            }
        }
    }

    private static void saveTasksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    private static void loadTasksFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("No existing task file found. Starting fresh.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Task.fromFileFormat(line);
                tasks.add(task);
            }
        } catch (IOException | ArtsException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }
}
