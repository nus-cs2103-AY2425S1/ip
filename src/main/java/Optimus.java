import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

// Asked ChatGPT to suggest comments and coding style which follows CS2103T guidelines
/**
 * Optimus class represents a simple task management system
 * that allows users to add, list, mark, and delete tasks.
 */
public class Optimus {
    private static ArrayList<Task> taskList = new ArrayList<>(); // Stores the list of tasks
    private static final String FILE_PATH = "data/optimus.txt"; // File path for storing the task data

    /**
     * Main method that runs the Optimus chatbot.
     * Greets the user, loads tasks from file, and processes user input.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        // Greeting for user
        String greeting = "Hello! I'm Optimus.\n" +
                "What can I do for you?\n";
        System.out.println(greeting);
        System.out.println("You need to start your input with either todo, deadline, or event.\n" +
                        "For example:\n" +
                        "todo borrow book\n" +
                        "deadline return book /by 2019-12-02\n" +
                        "event project meeting /from 2019-12-02 /to 2019-12-03");

        // Load tasks from the file if it exists
        loadTasks();

        //Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Infinite loop to keep taking in user input
        while (true) {
            try {
                String userInput = scanner.nextLine();

                // exit program if user enters "bye"
                if (userInput.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!\n");
                    scanner.close();
                    System.exit(0);
                }

                // List out all existing tasks from taskList array if user inputs "list"
                if (userInput.equals("list")) {
                    listTasks();
                } else if (userInput.startsWith("mark")) { // Check if user wants to mark task as done
                    String[] parts = userInput.split(" ");
                    int taskIndex = Integer.parseInt(parts[1]) - 1; // Convert 1-based index to 0-based
                    if (taskIndex + 1 > taskList.size()) { // Ensure the task exists
                        System.out.println("Sorry, you only have up to task number " + taskList.size());
                        continue;
                    }
                    markTaskAsDone(taskIndex);
                    saveTasks();
                } else if (userInput.startsWith("delete")) { // Check if user wants to delete a task
                    String[] parts = userInput.split(" ");
                    int taskIndex = Integer.parseInt(parts[1]) - 1; // Convert 1-based index to 0-based
                    delete(taskIndex);
                    saveTasks();
                } else {
                    // Add user task to taskList array and echo "added: <task>"
                    addTask(userInput);
                    saveTasks();
                }
            } catch (OptimusException e) { // Handle custom OptimusException
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Adds a task to the taskList based on user input.
     * Task can be of type Todo, Deadline, or Event.
     *
     * @param userInput The input string containing task details.
     * @throws OptimusException if the input format is invalid.
     */
    private static void addTask(String userInput) throws OptimusException {
        Task task;
        // Check and create task based on input type (todo, deadline, or event)
        //used ChatGPT to find how to extract relevant words from user input
        if (userInput.startsWith("todo")) {
            if (userInput.length() <= 5) { // Check if description is provided
                throw new OptimusException("The description of a todo cannot be empty >:(");
            }
            String description = userInput.substring(5).trim();
            task = new Todo(description);
        } else if (userInput.startsWith("deadline")) {
            if (userInput.length() <= 9) { // Check if description is provided
                throw new OptimusException("The description of a deadline cannot be empty >:(");
            }
            String[] parts = userInput.substring(9).split("/by");
            if (parts.length < 2) { // Ensure input is valid
                throw new OptimusException("Invalid input. Use this format: deadline return book /by 2019-12-02");
            }
            String description = parts[0].trim();
            String by = parts[1].trim();
            task = new Deadline(description, by);
        } else if (userInput.startsWith("event")) {
            if (userInput.length() <= 6) { // Check if description is provided
                throw new OptimusException("The description of an event cannot be empty >:(");
            }
            String[] parts = userInput.substring(6).split ("/from|/to");
            if (parts.length < 3) { // Ensure input is valid
                throw new OptimusException("Invalid input. Use this format: event project meeting /from 2019-12-02 /to 2019-12-03");
            }
            String description = parts[0].trim();
            String from = parts[1].trim();
            String to = parts[2].trim();
            task = new Event(description, from, to);
        } else {
            throw new OptimusException("Sorry, you need to start your input with either todo, deadline, or event.\n" +
                    "For example:\n" +
                    "todo borrow book\n" +
                    "deadline return book /by 2019-12-02\n" +
                    "event project meeting /from 2019-12-02 /to 2019-12-03");
        }
        // Add the created task to the task list
        taskList.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Lists out all tasks in the taskList.
     */
    private static void listTasks() {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + "." + taskList.get(i).toString());
        }
    }

    /**
     * Marks the task at the given index as done.
     *
     * @param taskIndex The index of the task to mark as done.
     */
    private static void markTaskAsDone(int taskIndex) {
        taskList.get(taskIndex).isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskList.get(taskIndex).toString());
    }

    /**
     * Deletes the task at the given index from the taskList.
     *
     * @param taskIndex The index of the task to delete.
     */
    private static void delete(int taskIndex) {
        Task[] newTaskList = new Task[100];
        System.out.println("Noted, I've removed this task:");
        System.out.println(taskList.get(taskIndex));
        taskList.remove(taskIndex);
        System.out.println("Now you have " + taskList.size() + " tasks in the list");
    }

    /**
     * Loads tasks from the file if it exists.
     * If the file or directory does not exist, creates them.
     * This method was partially inspired by code examples provided by ChatGPT and W3Schools.
     * Reference: https://www.w3schools.com/java/java_files_create.asp
     */
    private static void loadTasks() {
        try {
            // Create directory and file if they do not exist
            File directory = new File("data");
            File file = new File(FILE_PATH);

            if (!directory.exists()) {
                directory.mkdir();
            }

            if (!file.exists()) {
                //remove after testing
                file.createNewFile();
                System.out.println("Created new data file optimus.txt");
            } else {
                // Read tasks from the file
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split("\\|");
                    String taskType = parts[0].trim();

                    Task task;
                    if (taskType.equals("T")) {
                        String description = parts[2].trim();
                        task = new Todo(description);
                    } else if (taskType.equals("D")) {
                        String description = parts[2].trim();
                        String by = parts[3].trim();
                        try {
                            task = new Deadline(description, by);
                        } catch (OptimusException e) {
                            System.out.println("Error loading deadline task: " + e.getMessage());
                            continue;
                        }
                    } else {
                        String description = parts[2].trim();
                        String from = parts[3].trim();
                        String to = parts[4].trim();
                        try {
                            task = new Event(description, from, to);
                        } catch (OptimusException e) {
                            System.out.println("Error loading event task: " + e.getMessage());
                            continue;
                        }
                    }
                    // Mark task as done if indicated in the file
                    task.isDone = parts[1].trim().equals("1");
                    taskList.add(task);
                }
                scanner.close();
                System.out.println("Loaded tasks from file optimus.txt");
            }
        } catch (IOException e) {
            // Handle file I/O exceptions
            System.out.println("Error loading tasks from file: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            // Handle invalid file formatting
            // Suggested by ChatGPT
            System.out.println("Invalid task format in file.");
        }
    }

    /**
     * Saves the tasks in the taskList to the file.
     * Overwrites the file with the current state of the taskList.
     * This method was partially inspired by code examples provided by W3Schools.
     * Asked ChatGPT for advice on how to incorporate polymorphism. It suggested toFileFormat method.
     * Reference: https://www.w3schools.com/java/java_files_create.asp
     */
    private static void saveTasks() {
        try {
            FileWriter myWriter = new FileWriter(FILE_PATH);

            // Loop through taskList to add each task to file
            for (int i = 0; i < taskList.size(); i++) {
                myWriter.write(taskList.get(i).toFileFormat());
            }
            myWriter.close();
            System.out.println("Tasks saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }
}
