import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class GPT {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static final Path FILE_PATH = Paths.get("data", "tasks.txt");

    public static void main(String[] args) {
        try {
            if (!Files.exists(FILE_PATH.getParent())) {
                Files.createDirectories(FILE_PATH.getParent());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating directories: " + e.getMessage());
        }

        // Load tasks from file
        loadTasks();

        Scanner scanner = new Scanner(System.in);
        String chatbotName = "GPT";

        // Display the initial greeting
        System.out.println("Type 'list' to display saved tasks or 'bye' to exit.");
        printLine();
        System.out.println("Hello! I'm " + chatbotName);
        System.out.println("What can I do for you?");
        printLine();

        while (true) {
            try {
                // Read user input
                String input = scanner.nextLine().trim();

                // Exit
                if (input.equalsIgnoreCase("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                }

                // List tasks
                if (input.equalsIgnoreCase("list")) {
                    printLine();
                    if (tasks.isEmpty()) {
                        System.out.println("No tasks to show.");
                    } else {
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + "." + tasks.get(i));
                        }
                    }
                    printLine();
                    continue;
                }

                // Mark tasks as done
                if (input.startsWith("mark")) {
                    int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (taskNumber < 0 || taskNumber >= tasks.size()) {
                        throw new GPTException("The task number is out of range.");
                    }
                    tasks.get(taskNumber).markAsDone();
                    saveTasks();
                    printLine();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tasks.get(taskNumber));
                    printLine();
                    continue;
                }

                // Unmark tasks as not done
                if (input.startsWith("unmark")) {
                    int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (taskNumber < 0 || taskNumber >= tasks.size()) {
                        throw new GPTException("The task number is out of range.");
                    }
                    tasks.get(taskNumber).markAsNotDone();
                    saveTasks();
                    printLine();
                    System.out.println("OK, I've marked this task as not done:");
                    System.out.println("  " + tasks.get(taskNumber));
                    printLine();
                    continue;
                }

                // Delete tasks
                if (input.startsWith("delete")) {
                    String[] parts = input.split(" ", 2); // Split into command and argument
                    if (parts.length < 2 || parts[1].trim().isEmpty()) {
                        // No task number provided
                        throw new GPTException("Use 'delete [task number]' to specify the task you want to delete.");
                    }

                    int taskNumber = Integer.parseInt(parts[1].trim()) - 1;
                    if (tasks.isEmpty()) {
                        throw new GPTException("There are no tasks in the list to delete.");
                    } else if (taskNumber < 0 || taskNumber >= tasks.size()) {
                        throw new GPTException("The task number is out of range. Please provide a valid task number.");
                    }

                    Task removedTask = tasks.remove(taskNumber);
                    saveTasks();
                    printLine();
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + removedTask);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    printLine();
                    continue;
                }

                // Handle ToDo tasks
                if (input.startsWith("todo")) {
                    String description = input.substring(4).trim();
                    if (description.isEmpty()) {
                        throw new GPTException("The description of a todo cannot be empty.");
                    }
                    Task newTask = new ToDo(description);
                    tasks.add(newTask);
                    saveTasks();
                    printLine();
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + newTask);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    printLine();
                    continue;
                }

                // Handle Deadline tasks with LocalDateTime parsing
                if (input.startsWith("deadline")) {
                    String[] parts = input.substring(8).trim().split("/by");
                    if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                        throw new GPTException("The description and deadline of a deadline task cannot be empty.");
                    }
                    LocalDateTime byDateTime = parseDateTime(parts[1].trim());

                    if (byDateTime != null) {
                        Task newTask = new Deadline(parts[0].trim(), byDateTime);
                        tasks.add(newTask);
                        saveTasks();  // Save after adding a new Deadline task
                        printLine();
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + newTask);
                    } else {
                        printLine();
                        System.out.println("The task was not added because of an invalid date.");
                    }
                    printLine();
                    continue;
                }


                // Handle Event tasks with LocalDateTime parsing
                if (input.startsWith("event")) {
                    String[] parts = input.substring(5).trim().split("/from|/to");
                    if (parts.length < 3 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
                        throw new GPTException("The description, start, and end time of an event cannot be empty.");
                    }
                    LocalDateTime fromDateTime = parseDateTime(parts[1].trim());
                    LocalDateTime toDateTime = parseDateTime(parts[2].trim());

                    if (fromDateTime != null && toDateTime != null) {
                        Task newTask = new Event(parts[0].trim(), fromDateTime, toDateTime);
                        tasks.add(newTask);
                        saveTasks();  // Save after adding a new Event task
                        printLine();
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + newTask);
                    } else {
                        printLine();
                        System.out.println("The task was not added because of an invalid date or time.");
                    }
                    printLine();
                    continue;
                }


                // Handle unrecognized commands
                throw new GPTException("unrecognized");

            } catch (NumberFormatException e) {
                printLine();
                System.out.println("OOPS!!! Please enter a valid task number.");
                printLine();
            } catch (IndexOutOfBoundsException e) {
                printLine();
                System.out.println("OOPS!!! There's nothing to delete.");
                printLine();
            } catch (GPTException e) {
                printLine();
                if (e.getMessage().equals("unrecognized")) {
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    System.out.println("You can try these commands:");
                    System.out.println("  todo [task description] - To add a ToDo task");
                    System.out.println("  deadline [task description] /by [date/time] - To add a Deadline task");
                    System.out.println("  event [task description] /from [start date/time] /to [end date/time] - To add an Event task");
                    System.out.println("  list - To display all tasks");
                    System.out.println("  mark [task number] - To mark a task as done");
                    System.out.println("  unmark [task number] - To unmark a task");
                    System.out.println("  delete [task number] - To delete a task");
                } else {
                    System.out.println(e.getMessage());
                }
                printLine();
            } catch (Exception e) {
                printLine();
                System.out.println("An unexpected error occurred: " + e.getMessage());
                printLine();
            }
        }
        scanner.close();
    }
    private static void loadTasks() {
        if (Files.exists(FILE_PATH)) {
            try (BufferedReader reader = Files.newBufferedReader(FILE_PATH)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(" \\| ");
                    String taskType = parts[0];
                    boolean isDone = parts[1].equals("1");
                    String description = parts[2];

                    Task task;
                    if (taskType.equals("T")) {
                        task = new ToDo(description);
                    } else if (taskType.equals("D")) {
                        LocalDateTime byDateTime = parseDateTime(parts[3]); // Convert the string to LocalDateTime
                        task = new Deadline(description, byDateTime);
                    } else if (taskType.equals("E")) {
                        LocalDateTime fromDateTime = parseDateTime(parts[3]); // Convert the string to LocalDateTime
                        LocalDateTime toDateTime = parseDateTime(parts[4]);   // Convert the string to LocalDateTime
                        task = new Event(description, fromDateTime, toDateTime);
                    } else {
                        System.out.println("Unknown task type: " + taskType);
                        continue;
                    }

                    if (isDone) {
                        task.markAsDone();
                    }

                    tasks.add(task);
                }
            } catch (IOException e) {
                System.out.println("An error occurred while loading tasks: " + e.getMessage());
            }
        }
}
    private static void saveTasks() {
        try (BufferedWriter writer = Files.newBufferedWriter(FILE_PATH)) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks: " + e.getMessage());
        }
    }

    private static LocalDateTime parseDateTime(String dateTimeStr) {
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-M-d HHmm");
        DateTimeFormatter formatter4 = DateTimeFormatter.ofPattern("d/M/yyyy");
        DateTimeFormatter formatter5 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            return LocalDateTime.parse(dateTimeStr, formatter1);  // Try the first format
        } catch (DateTimeParseException e) {
            try {
                return LocalDateTime.parse(dateTimeStr, formatter2);  // Try the second format
            } catch (DateTimeParseException ex) {
                try {
                    return LocalDateTime.parse(dateTimeStr, formatter3);  // Try the third format
                } catch (DateTimeParseException exc) {
                    try {
                        LocalDate date = LocalDate.parse(dateTimeStr, formatter4);
                        return date.atStartOfDay();  // If date only (d/M/yyyy), set time to start of day
                    } catch (DateTimeParseException exc2) {
                        try {
                            LocalDate date = LocalDate.parse(dateTimeStr, formatter5);
                            return date.atStartOfDay();  // If date only (yyyy-MM-dd), set time to start of day
                        } catch (DateTimeParseException exc3) {
                            System.out.println("Invalid date format. Please use yyyy-MM-dd HHmm, d/M/yyyy HHmm, or yyyy-MM-dd format.");
                            return null;
                        }
                    }
                }
            }
        }
    }

    private static void printLine() {
        System.out.println("____________________________________________________________");
    }
}
