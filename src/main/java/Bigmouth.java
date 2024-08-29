import java.io.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
public class Bigmouth {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final URL fileURL = Bigmouth.class.getProtectionDomain().getCodeSource().getLocation();
    private static String path = fileURL.getPath(); //FILE_PATH;
    public static String rootPath = path.substring(0, path.indexOf("ip") + 3) +  "/data/Bigmouth.txt";
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    private static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    public static void main(String[] args) {
        loadFromFile();
        Scanner scanner = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Bigmouth\nWhat can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String userInput = scanner.nextLine();

            try {
                if (userInput.equals("bye")) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    break;
                }

                if (userInput.equals("list")) {
                    if (tasks.isEmpty()) {
                        throw new BigmouthException("Your task list is empty!");
                    }
                    System.out.println("____________________________________________________________");
                    System.out.println(" Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(" " + (i + 1) + "." + tasks.get(i));
                    }
                    System.out.println("____________________________________________________________");
                } else if (userInput.startsWith("mark ")) {
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    if (taskNumber < 0 || taskNumber >= tasks.size()) {
                        throw new BigmouthException("Invalid task number. Please enter a valid task number.");
                    }
                    tasks.get(taskNumber).markAsDone();
                    saveToFile();
                    System.out.println("____________________________________________________________");
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("   " + tasks.get(taskNumber));
                    System.out.println("____________________________________________________________");
                } else if (userInput.startsWith("unmark ")) {
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    if (taskNumber < 0 || taskNumber >= tasks.size()) {
                        throw new BigmouthException("Invalid task number. Please enter a valid task number.");
                    }
                    tasks.get(taskNumber).markAsNotDone();
                    saveToFile();
                    System.out.println("____________________________________________________________");
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println("   " + tasks.get(taskNumber));
                    System.out.println("____________________________________________________________");
                } else if (userInput.startsWith("todo ")) {
                    String description = userInput.substring(5).trim();
                    if (description.isEmpty()) {
                        throw new BigmouthException("OOPS! The description of a todo cannot be empty.");
                    }
                    tasks.add(new Todo(description));
                    saveToFile();
                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + tasks.get(tasks.size() - 1));
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else if (userInput.startsWith("deadline ")) {
                    String[] parts = userInput.split(" /by ");
                    if (parts.length < 2 || parts[1].trim().isEmpty()) {
                        throw new BigmouthException("OOPS! The deadline command is missing a date/time.");
                    }
                    String description = parts[0].substring(9).trim();
                    LocalDateTime by = parseDateTime(parts[1].trim());
                    if (description.isEmpty()) {
                        throw new BigmouthException("OOPS! The description of a deadline cannot be empty.");
                    }
                    tasks.add(new Deadline(description, by));
                    saveToFile();
                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + tasks.get(tasks.size() - 1));
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else if (userInput.startsWith("event ")) {
                    String[] parts = userInput.split(" /from | /to ");
                    if (parts.length < 3 || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
                        throw new BigmouthException("OOPS! The event command is missing a start or end time.");
                    }
                    String description = parts[0].substring(6).trim();
                    LocalDateTime from = parseDateTime(parts[1].trim());
                    LocalDateTime to = parseDateTime(parts[2].trim());
                    if (description.isEmpty()) {
                        throw new BigmouthException("OOPS! The description of an event cannot be empty.");
                    }
                    tasks.add(new Event(description, from, to));
                    saveToFile();
                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + tasks.get(tasks.size() - 1));
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else if (userInput.startsWith("delete ")) {
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    if (taskNumber < 0 || taskNumber >= tasks.size()) {
                        throw new BigmouthException("Invalid task number. Please enter a valid task number.");
                    }
                    Task removedTask = tasks.remove(taskNumber);
                    saveToFile();
                    System.out.println("____________________________________________________________");
                    System.out.println(" Noted. I've removed this task:");
                    System.out.println("   " + removedTask);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else {
                    throw new BigmouthException("OOPS! I don't know what that means. Please try again.");
                }
            } catch (BigmouthException e) {
                System.out.println("____________________________________________________________");
                System.out.println(" " + e.getMessage());
                System.out.println("____________________________________________________________");
            } catch (NumberFormatException e) {
                System.out.println("____________________________________________________________");
                System.out.println(" OOPS! Please enter a valid number.");
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close();
    }

    private static LocalDateTime parseDateTime(String input) throws BigmouthException {
        try {
            return LocalDateTime.parse(input, DateTimeFormatter.ofPattern("M/d/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw new BigmouthException("OOPS! Please enter the date/time in the format 'M/d/yyyy HHmm'.");
        }
    }
    private static void saveToFile() {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rootPath))) {
            for (Task task : tasks) {
                writer.write(task.toSaveFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    private static void loadFromFile() {
        File file = new File(rootPath);
        if (!file.exists()) {
            return;  // No file to load from
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                Task task = null;
                switch (type) {
                    case "T":
                        task = new Todo(description);
                        break;
                    case "D":
                        LocalDateTime by = LocalDateTime.parse(parts[3], DATE_FORMATTER);
                        task = new Deadline(description, by);
                        break;
                    case "E":
                        LocalDateTime from = LocalDateTime.parse(parts[3], DATE_FORMATTER);
                        LocalDateTime to = LocalDateTime.parse(parts[4], DATE_FORMATTER);
                        task = new Event(description, from, to);
                        break;
                }

                if (task != null) {
                    if (isDone) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
    }
}