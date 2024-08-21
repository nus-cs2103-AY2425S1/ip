import java.util.Scanner;
import java.lang.StringBuilder;

public class Gopher {
    // Initialize the input reader
    private final static Scanner inputReader = new Scanner(System.in);

    // Tasks List Data to handle user input tasks
    private final static Task[] taskList = new Task[100];
    private static int currentTaskNumber = 0;

    // Common Interface elements for easy reuse
    private final static String gopherLogo = """
              ____             _              \s
             / ___| ___  _ __ | |__   ___ _ __\s
            | |  _ / _ \\| '_ \\| '_ \\ / _ \\ '__|
            | |_| | (_) | |_) | | | |  __/ |  \s
             \\____|\\___/| .__/|_| |_|\\___|_|  \s
                        |_|                   \s
            """;
    private final static String horizontalSeparator = "==================================================";

    private static void greet () {
        System.out.println(horizontalSeparator);
        System.out.println(gopherLogo);
        System.out.println("Hello! I am Gopher.\nWhat can I do for you?\n");
    }

    private static void addTask (String input) {
        String[] tokens = input.split(" ");

        String taskType = tokens[0];
        StringBuilder taskName = new StringBuilder();

        if (taskType.equalsIgnoreCase("todo")) {
            for (int i = 1; i < tokens.length; i++) {
                taskName.append(tokens[i]);
                if (i < tokens.length - 1) {
                    taskName.append(" ");
                }
            }
            taskList[currentTaskNumber] = new ToDo(taskName.toString());
        } else if (taskType.equalsIgnoreCase("deadline")) {
            StringBuilder dueDate = new StringBuilder();

            int byTokenIndex = -1;
            for (int i = 1; i < tokens.length; i++) {
                if (tokens[i].equalsIgnoreCase("/by")) {
                    byTokenIndex = i;
                }
            }

            for (int i = 1; i < byTokenIndex; i++) {
                taskName.append(tokens[i]);
                if (i < byTokenIndex - 1) {
                    taskName.append(" ");
                }
            }

            for (int i = byTokenIndex + 1; i < tokens.length; i++) {
                dueDate.append(tokens[i]);
                if (i < tokens.length - 1) {
                    dueDate.append(" ");
                }
            }

            taskList[currentTaskNumber] = new Deadline(taskName.toString(),
                    dueDate.toString());
        } else if (taskType.equalsIgnoreCase("event")) {
            StringBuilder startDate = new StringBuilder();
            StringBuilder endDate = new StringBuilder();

            int fromTokenIndex = -1;
            int toTokenIndex = -1;
            for (int i = 1; i < tokens.length; i++) {
                if (tokens[i].equalsIgnoreCase("/from")) {
                    fromTokenIndex = i;
                }
                if (tokens[i].equalsIgnoreCase("/to")) {
                    toTokenIndex = i;
                }
            }

            for (int i = 1; i < fromTokenIndex; i++) {
                taskName.append(tokens[i]);
                if (i < fromTokenIndex - 1) {
                    taskName.append(" ");
                }
            }

            for (int i = fromTokenIndex + 1; i < toTokenIndex; i++) {
                startDate.append(tokens[i]);
                if (i < toTokenIndex - 1) {
                    startDate.append(" ");
                }
            }

            for (int i = toTokenIndex + 1; i < tokens.length; i++) {
                endDate.append(tokens[i]);
                if (i < tokens.length - 1) {
                    endDate.append(" ");
                }
            }
            taskList[currentTaskNumber] = new Event(taskName.toString(),
                    startDate.toString(),
                    endDate.toString());
        }

        Task addedTask = taskList[currentTaskNumber];
        currentTaskNumber++;
        System.out.println(horizontalSeparator);
        System.out.println("Got it! I have added this task:\n" + addedTask);
        System.out.println("Now you have " + currentTaskNumber + " tasks in the list");
        System.out.println(horizontalSeparator + "\n");
    }

    private static void listTasks() {
        System.out.println(horizontalSeparator);
        for (int i = 1; i <= currentTaskNumber; i++) {
            int currentTaskIndex = i - 1;
            String message = String.format("%d. %s", i, taskList[currentTaskIndex]);
            System.out.println(message);
        }
        System.out.println(horizontalSeparator + "\n");
    }

    private static void markTaskAsDone(int taskNumber) {
        int taskIndex = taskNumber - 1;
        Task task = taskList[taskIndex];
        task.markAsDone();
        System.out.println(horizontalSeparator);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        System.out.println(horizontalSeparator + "\n");
    }

    private static void markTaskAsNotDone(int taskNumber) {
        int taskIndex = taskNumber - 1;
        Task task = taskList[taskIndex];
        task.markAsNotDone();
        System.out.println(horizontalSeparator);
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(task);
        System.out.println(horizontalSeparator + "\n");
    }

    private static void exit () {
        System.out.println(horizontalSeparator);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalSeparator + "\n");
    }

    public static void main(String[] args) {
        // Greet the user when starting application
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
                String [] tokens = userInput.split(" ");
                int taskNumber = Integer.parseInt(tokens[1]);
                markTaskAsNotDone(taskNumber);
            // Handle Add Task Logic
            } else {
                addTask(userInput);
            }
        }

        // Send exit message to user when event loop ends
        exit();
    }
}
