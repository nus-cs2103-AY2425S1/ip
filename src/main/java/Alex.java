import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Alex {

    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static final String FILE_PATH = "./data/tasks.txt";

    public static void main(String[] args) {
        createDataDirectory();  // Ensure data directory exists
        loadTasks();  // Load tasks from file

        Scanner scanner = new Scanner(System.in);

        printDividerWithMessage("Hello! I'm Alex, your friendly assistant!\nWhat can I do for you?");

        while (true) {
            String userInput = scanner.nextLine();

            try {
                if (userInput.equalsIgnoreCase("list")) {
                    printTaskList();
                } else if (userInput.toLowerCase().startsWith("mark ")) {
                    int taskIndex = parseTaskIndex(userInput);
                    if (taskIndex >= 0 && taskIndex < tasks.size()) {
                        tasks.get(taskIndex).markAsDone();
                        printTaskStatusChange("Nice! I've marked this task as done:", taskIndex);
                        saveTasks();  // Save tasks after update
                    } else {
                        printInvalidTaskNumber();
                    }
                } else if (userInput.toLowerCase().startsWith("unmark ")) {
                    int taskIndex = parseTaskIndex(userInput);
                    if (taskIndex >= 0 && taskIndex < tasks.size()) {
                        tasks.get(taskIndex).markAsNotDone();
                        printTaskStatusChange("OK, I've marked this task as not done yet:", taskIndex);
                        saveTasks();  // Save tasks after update
                    } else {
                        printInvalidTaskNumber();
                    }
                } else if (userInput.toLowerCase().startsWith("delete ")) {
                    int taskIndex = parseTaskIndex(userInput);
                    if (taskIndex >= 0 && taskIndex < tasks.size()) {
                        Task removedTask = tasks.remove(taskIndex);
                        printTaskDeleted(removedTask);
                        saveTasks();  // Save tasks after update
                    } else {
                        printInvalidTaskNumber();
                    }
                } else if (userInput.equalsIgnoreCase("tell me a joke")) {
                    printDividerWithMessage("Why did the scarecrow win an award? Because he was outstanding in his field!");
                } else if (userInput.equalsIgnoreCase("bye")) {
                    printDividerWithMessage("Bye. Hope to see you again soon!");
                    break;
                } else if (userInput.toLowerCase().startsWith("todo ")) {
                    Task newTask = new Todo(userInput.substring(5));
                    tasks.add(newTask);
                    printTaskAdded(newTask);
                    saveTasks();  // Save tasks after update
                } else if (userInput.toLowerCase().startsWith("deadline ")) {
                    String[] parts = userInput.substring(9).split(" /by ");
                    Task newTask = new Deadline(parts[0], parts[1]);
                    tasks.add(newTask);
                    printTaskAdded(newTask);
                    saveTasks();  // Save tasks after update
                } else if (userInput.toLowerCase().startsWith("event ")) {
                    String[] parts = userInput.substring(6).split(" /from | /to ");
                    Task newTask = new Event(parts[0], parts[1], parts[2]);
                    tasks.add(newTask);
                    printTaskAdded(newTask);
                    saveTasks();  // Save tasks after update
                } else if (userInput.toLowerCase().equals("todo")) {
                    throw new EmptyTodoException();
                } else if (userInput.toLowerCase().equals("blah")) {
                    throw new UnknownCommandException();
                } else {
                    Task newTask = new Task(userInput, TaskType.TODO);
                    tasks.add(newTask);
                    printTaskAdded(newTask);
                    saveTasks();  // Save tasks after update
                }
            } catch (AlexException e) {
                printDividerWithMessage(e.getMessage());
            }
        }
        scanner.close();
    }

    private static void createDataDirectory() {
        File dataDir = new File("./data");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }
    }

    private static void loadTasks() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return;  // No tasks to load if file doesn't exist
        }
    
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                if (parts.length < 2) continue;  // Skip invalid lines
    
                TaskType type = TaskType.valueOf(parts[0]);
                boolean isDone = parts[1].equals("1");
                String description = parts[2];
    
                Task task = null;
                switch (type) {
                    case TODO:
                        task = new Todo(description);
                        break;
                    case DEADLINE:
                        if (parts.length == 4) {
                            task = new Deadline(description, parts[3]);
                        }
                        break;
                    case EVENT:
                        if (parts.length == 5) { // Adjusted length to 5 for Event (with from and to)
                            task = new Event(description, parts[3], parts[4]);
                        }
                        break;
                    default:
                        // Handle unexpected TaskType
                        break;
                }
    
                if (task != null) {
                    if (isDone) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                }
            }
        } catch (IOException | IllegalArgumentException e) {
            printDividerWithMessage("Error loading tasks: " + e.getMessage());
        }
    }
    

    private static void saveTasks() {
        File file = new File(FILE_PATH);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                String line = task.taskType + " | " + (task.isDone ? "1" : "0") + " | " + task.getDescription();
                if (task instanceof Deadline) {
                    line += " | " + ((Deadline) task).by;
                } else if (task instanceof Event) {
                    line += " | " + ((Event) task).from + " | " + ((Event) task).to;
                }
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            printDividerWithMessage("Error saving tasks: " + e.getMessage());
        }
    }

    private static void printTaskList() {
        StringBuilder taskList = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            taskList.append("\n ").append(i + 1).append(". ").append(task);
        }
        printDividerWithMessage(taskList.toString());
    }

    private static void printTaskStatusChange(String message, int taskIndex) {
        String statusChangeMessage = message + "\n   " + tasks.get(taskIndex);
        printDividerWithMessage(statusChangeMessage);
    }

    private static void printInvalidTaskNumber() {
        printDividerWithMessage("Invalid task number.");
    }

    private static void printTaskAdded(Task task) {
        printDividerWithMessage("Got it. I've added this task:\n   " + task + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    private static void printTaskDeleted(Task task) {
        printDividerWithMessage("Noted. I've removed this task:\n   " + task + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    private static void printDividerWithMessage(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(" " + message);
        System.out.println("____________________________________________________________");
    }

    private static int parseTaskIndex(String userInput) {
        try {
            return Integer.parseInt(userInput.split(" ")[1]) - 1; // Convert to zero-based index
        } catch (Exception e) {
            return -1; // Return -1 if parsing fails
        }
    }
}
