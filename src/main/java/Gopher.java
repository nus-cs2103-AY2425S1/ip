import java.util.Scanner;
import java.util.ArrayList;

public class Gopher {
    // Common Interface elements for easy reuse
    private final static String gopherLogo = """
              ____             _
             / ___| ___  _ __ | |__   ___ _ __
            | |  _ / _ \\| '_ \\| '_ \\ / _ \\ '__|
            | |_| | (_) | |_) | | | |  __/ |
             \\____|\\___/| .__/|_| |_|\\___|_|
                        |_|
            """;
    private final static String horizontalSeparator = "==================================================";

    // Initialize the necessary components
    private static Scanner inputReader;

    // Tasks ArrayList to store user input tasks
    private static ArrayList<Task> taskList;

    private static void initialize() {
        inputReader = new Scanner(System.in);
        TaskManager.initialize();
        taskList = TaskManager.loadTasks();
    }

    // Show greeting message when user first enter the application
    private static void greet () {
        System.out.println(horizontalSeparator);
        System.out.println(gopherLogo);
        System.out.println("Hello! I am Gopher.\nWhat can I do for you?\n");
    }

    // Handle the logic when user input command to add a new task
    private static void addTask (String input)  {
        try {
            Task newTask = Task.of(input);
            taskList.add(newTask);
            TaskManager.saveTasks(taskList);

            System.out.println(horizontalSeparator);
            System.out.println("Got it! I have added this task:\n" + newTask);
            System.out.println(String.format("Now you have %d %s in the list",
                    taskList.size(),
                    taskList.size() == 1 ? "task" : "tasks"));
            System.out.println(horizontalSeparator + "\n");
        } catch (UnknownCommandException e) {
            System.out.println(e.getMessage());
            System.out.println(horizontalSeparator);
            System.out.println("""
                    Current Supported Commands:
                        1. todo - Create a ToDo Task
                        2. deadline - Create a Deadline Task
                        3. event - Create an Event Task
                        4. mark - Mark a task as done
                        5. unmark - Mark a task as not done
                        6. bye - Exit the chatbot
                    
                    Note: The command is case-insensitive, as long as the
                    input characters match, the chatbot would be able to
                    respond to the given command
                    """);
        } catch (EmptyTaskDescriptionException
            | MissingTokenException e) {
            System.out.println(e.getMessage());
        }
    }

    // List out all the existing tasks in the application
    private static void listTasks() {
        System.out.println(horizontalSeparator);
        for (int i = 1; i <= taskList.size(); i++) {
            int currentTaskIndex = i - 1;
            String message = String.format("%d. %s", i, taskList.get(currentTaskIndex));
            System.out.println(message);
        }
        System.out.println(horizontalSeparator + "\n");
    }

    // Mark the task with corresponding number as done
    private static void markTaskAsDone(int taskNumber) {
        int taskIndex = taskNumber - 1;
        Task task = taskList.get(taskIndex);
        task.markAsDone();
        TaskManager.saveTasks(taskList);

        System.out.println(horizontalSeparator);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        System.out.println(horizontalSeparator + "\n");
    }

    // Mark the task with corresponding number as not done
    private static void markTaskAsNotDone(int taskNumber) {
        int taskIndex = taskNumber - 1;
        Task task = taskList.get(taskIndex);
        task.markAsNotDone();
        TaskManager.saveTasks(taskList);

        System.out.println(horizontalSeparator);
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(task);
        System.out.println(horizontalSeparator + "\n");
    }

    // Delete the task with the corresponding task number
    private static void deleteTask(int taskNumber) {
        int taskIndex = taskNumber - 1;

        System.out.println(horizontalSeparator);
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskList.get(taskIndex));
        System.out.println(horizontalSeparator + "\n");

        taskList.remove(taskIndex);
        TaskManager.saveTasks(taskList);
    }

    // Say goodbye to the user and exit the application
    private static void exit () {
        System.out.println(horizontalSeparator);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalSeparator + "\n");
    }

    public static void main(String[] args) {
        initialize();
        greet();

        // Start the event loop for responding user input and query
        boolean isRunning = true;
        String userInput;
        while (isRunning) {
            // Getting User Input
            userInput = inputReader.nextLine();

            // Handle Exit Logic
            if (userInput.equalsIgnoreCase("Bye")) {
                isRunning = false;
            // Handle List Task Logic
            } else if (userInput.equalsIgnoreCase("List")) {
                listTasks();
            // Handle Mark Task as Done Logic
            } else if (userInput.toLowerCase().startsWith("mark")){
                String[] tokens = userInput.split(" ");
                int taskNumber = Integer.parseInt(tokens[1]);
                markTaskAsDone(taskNumber);
            // Handle Mark Task as Not Done Logic
            } else if (userInput.toLowerCase().startsWith("unmark")) {
                String[] tokens = userInput.split(" ");
                int taskNumber = Integer.parseInt(tokens[1]);
                markTaskAsNotDone(taskNumber);
                // Handle Add Task Logic
            }else if (userInput.toLowerCase().startsWith("delete")){
                String[] tokens = userInput.split(" ");
                int taskNumber = Integer.parseInt(tokens[1]);
                deleteTask(taskNumber);
            } else {
                addTask(userInput);
            }
        }

        exit();
    }
}
