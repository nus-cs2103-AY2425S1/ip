import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class EchoBot {
    private static final String FILE_PATH = "./data/echo-bot.txt";
    private static final String DIR_PATH = "./data/";


    private static final ArrayList<Task> tasks = new ArrayList<>();

    // Method to ensure the directory and file exist
    private static void ensureFileExists() {
        File dir = new File(DIR_PATH);
        if (!dir.exists()) {
            dir.mkdirs(); // Create the directory if it doesn't exist
        }

        File file = new File(FILE_PATH);
        try {
            if (!file.exists()) {
                file.createNewFile(); // Create the file if it doesn't exist
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    // Method to save tasks to a file
    private static void saveTasksToFile() {
        ensureFileExists();
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks) {
                writer.println(task.toFileFormat());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    // Call this method after any operation that modifies the task list
    private static void addTask(Task task) {
        tasks.add(task);
        saveTasksToFile(); // Save after adding a task
    }

    private static Task removeTask(int index) {
        Task removedTask = tasks.remove(index);
        saveTasksToFile(); // Save after removing a task
        return removedTask;
    }

    // Load tasks from file when starting the program
    private static void loadTasksFromFile() {
        ensureFileExists();
        Path path = Paths.get(FILE_PATH);
        if (!Files.exists(path)) {
            return; // No file to load from
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String[] parts = line.split(" \\| ");
                    Task task = null;
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
                            throw new IllegalArgumentException("Unknown task type");
                    }
                    if (task != null && parts[1].equals("1")) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                } catch (Exception e) {
                    System.out.println("Corrupted line in file: " + line);
                    // Optionally, delete or reset the file if corruption detected:
                    // new File(FILE_PATH).delete(); // Resets the file
                    // System.out.println("File reset due to corruption.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        loadTasksFromFile(); // Load tasks when starting
        Scanner scanner = new Scanner(System.in);


        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm EchoBot");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        label:
        while (true) {
            String userInput = scanner.nextLine();
            String[] inputParts = userInput.split(" ", 2); // Splits input into command and the rest

            String command = inputParts[0];

            switch (command) {
                case "bye":
                    System.out.println("____________________________________________________________");
                    System.out.println(" Bye! Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    break label;
                case "list":
                    listTasks();
                    break;
                case "mark": {
                    handleMarkCommand(inputParts);
                    break;
                }
                case "unmark": {
                    handleUnmarkCommand(inputParts);
                    break;
                }
                case "todo": {
                    handleTodoCommand(inputParts);
                    break;
                }
                case "deadline": {
                    try {
                        handleDeadlineCommand(inputParts);
                    } catch (IllegalArgumentException e) {
                        System.out.println("____________________________________________________________");
                        System.out.println(" " + e.getMessage());
                        System.out.println(" Please enter date in the format dd/MM/yyyy?");
                        System.out.println("____________________________________________________________");
                    }
                    break;
                }
                case "event": {
                    try {
                        handleEventCommand(inputParts);
                    } catch (IllegalArgumentException e) {
                        System.out.println("____________________________________________________________");
                        System.out.println(" " + e.getMessage());
                        System.out.println(" Please enter date in the format dd/MM/yyyy?");
                        System.out.println("____________________________________________________________");
                    }
                    break;
                }
                case "delete": {
                    handleDeleteCommand(inputParts);
                    break;
                }
                case "findbydate":
                    handleFindByDateCommand(inputParts);
                default:
                    System.out.println("____________________________________________________________");
                    System.out.println(" I'm sorry, I don't recognize that command.");
                    System.out.println("____________________________________________________________");
                    break;
            }
        }

        scanner.close();
    }
    private static void listTasks() {
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }
    private static void handleMarkCommand(String[] inputParts) {
        if (inputParts.length < 2) {
            System.out.println("____________________________________________________________");
            System.out.println(" Please specify the task number to mark as done.");
            System.out.println("____________________________________________________________");
        } else {
            int taskNumber = Integer.parseInt(inputParts[1]) - 1;
            if (taskNumber < tasks.size() && taskNumber >= 0) {
                tasks.get(taskNumber).markAsDone();
                saveTasksToFile();
                System.out.println("____________________________________________________________");
                System.out.println(" Great! I've marked this task as done:");
                System.out.println("   " + tasks.get(taskNumber));
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("____________________________________________________________");
                System.out.println(" Oops! That task number doesn't exist.");
                System.out.println("____________________________________________________________");
            }
        }
    }
    private static void handleUnmarkCommand(String[] inputParts) {
        if (inputParts.length < 2) {
            System.out.println("____________________________________________________________");
            System.out.println(" Please specify the task number to unmark.");
            System.out.println("____________________________________________________________");
        } else {
            int taskNumber = Integer.parseInt(inputParts[1]) - 1;
            if (taskNumber < tasks.size() && taskNumber >= 0) {
                tasks.get(taskNumber).markAsNotDone();
                saveTasksToFile();
                System.out.println("____________________________________________________________");
                System.out.println(" OK! I've marked this task as not done yet:");
                System.out.println("   " + tasks.get(taskNumber));
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("____________________________________________________________");
                System.out.println(" Oops! That task number doesn't exist.");
                System.out.println("____________________________________________________________");
            }
        }
    }

    private static void handleTodoCommand(String[] inputParts) {
        if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
            System.out.println("____________________________________________________________");
            System.out.println(" The description of a todo cannot be empty.");
            System.out.println("____________________________________________________________");
        } else {
            addTask(new Todo(inputParts[1]));
            System.out.println("____________________________________________________________");
            System.out.println(" Got it. I've added this task:");
            System.out.println("   " + tasks.get(tasks.size() - 1));
            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("____________________________________________________________");
        }
    }

    private static void handleDeadlineCommand(String[] inputParts) {
        if (inputParts.length < 2 || !inputParts[1].contains("/by ")) {
            System.out.println("____________________________________________________________");
            System.out.println(" The description of a deadline must include a due date.");
            System.out.println("____________________________________________________________");
        } else {
            String[] details = inputParts[1].split(" /by ");
            if (details.length < 2 || details[0].trim().isEmpty()) {
                System.out.println("____________________________________________________________");
                System.out.println(" The description of a deadline cannot be empty.");
                System.out.println("____________________________________________________________");
            } else {
                addTask(new Deadline(details[0], details[1]));
                System.out.println("____________________________________________________________");
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + tasks.get(tasks.size() - 1));
                System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("____________________________________________________________");
            }
        }
    }

    private static void handleEventCommand(String[] inputParts) {
        if (inputParts.length < 2 || !inputParts[1].contains("/from ") || !inputParts[1].contains("/to ")) {
            System.out.println("____________________________________________________________");
            System.out.println(" The description of an event must include start and end times.");
            System.out.println("____________________________________________________________");
        } else {
            String[] details = inputParts[1].split(" /from | /to ");
            if (details.length < 3 || details[0].trim().isEmpty()) {
                System.out.println("____________________________________________________________");
                System.out.println(" The description of an event cannot be empty.");
                System.out.println("____________________________________________________________");
            } else {
                addTask(new Event(details[0], details[1], details[2]));
                System.out.println("____________________________________________________________");
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + tasks.get(tasks.size() - 1));
                System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("____________________________________________________________");
            }
        }
    }
    private static void handleDeleteCommand(String[] inputParts) {
        if (inputParts.length < 2) {
            System.out.println("____________________________________________________________");
            System.out.println(" Please specify the task number to delete.");
            System.out.println("____________________________________________________________");
        } else {
            int taskNumber = Integer.parseInt(inputParts[1]) - 1;
            if (taskNumber < tasks.size() && taskNumber >= 0) {
                Task removedTask = removeTask(taskNumber);
                System.out.println("____________________________________________________________");
                System.out.println(" Noted. I've removed this task:");
                System.out.println("   " + removedTask);
                System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("____________________________________________________________");
                System.out.println(" Oops! That task number doesn't exist.");
                System.out.println("____________________________________________________________");
            }
        }
    }
    private static void handleFindByDateCommand(String[] inputParts) {
        if (inputParts.length < 2) {
            System.out.println("Please specify a date in the format yyyy-mm-dd.");
            return;
        }

        String dateString = inputParts[1];  // Assuming the date is the second element
        try {
            LocalDate queryDate = LocalDate.parse(dateString);
            System.out.println("Tasks occurring on " + queryDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":");
            boolean hasTasks = false;
            for (Task task : tasks) {
                if (task instanceof Deadline && ((Deadline) task).by.equals(queryDate)) {
                    System.out.println(task);
                    hasTasks = true;
                } else if (task instanceof Event && (((Event) task).from.equals(queryDate) || ((Event) task).to.equals(queryDate))) {
                    System.out.println(task);
                    hasTasks = true;
                }
            }
            if (!hasTasks) {
                System.out.println("No tasks found on this date.");
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use yyyy-mm-dd.");
        }
    }
}
