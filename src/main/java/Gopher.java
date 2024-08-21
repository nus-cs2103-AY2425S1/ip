import java.util.Scanner;
import java.lang.StringBuilder;
import java.util.ArrayList;

public class Gopher {
    // Initialize the input reader
    private final static Scanner inputReader = new Scanner(System.in);

    // Tasks List Data to handle user input tasks
    private final static ArrayList<Task> taskList = new ArrayList<>();
    private static int currentTaskNumber = 0;

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

    // Show greeting message when user first enter the application
    private static void greet () {
        System.out.println(horizontalSeparator);
        System.out.println(gopherLogo);
        System.out.println("Hello! I am Gopher.\nWhat can I do for you?\n");
    }

    // Check if the user input command is invalid
    private static boolean isUnknownCommand(String command) {
        return (!command.equalsIgnoreCase("todo"))
                && (!command.equalsIgnoreCase("deadline"))
                && (!command.equalsIgnoreCase("event"));
    }

    // Handle the logic when user input command to add a new task
    private static void addTask (String input) throws UnknownCommandException,
            EmptyTaskDescriptionException, MissingTokenException {
        // Split user command into tokens
        String[] tokens = input.split(" ");

        // Determine if the user command is valid
        String taskType = tokens[0];
        if (isUnknownCommand(taskType)) {
            throw new UnknownCommandException(taskType);
        }

        // Determine if there's any missing task description
        if (tokens.length < 2) {
            throw new EmptyTaskDescriptionException(taskType);
        }

        StringBuilder taskName = new StringBuilder();

        if (taskType.equalsIgnoreCase("todo")) {
            for (int i = 1; i < tokens.length; i++) {
                taskName.append(tokens[i]);
                if (i < tokens.length - 1) {
                    taskName.append(" ");
                }
            }
            taskList.add(new ToDo(taskName.toString()));
        } else if (taskType.equalsIgnoreCase("deadline")) {
            StringBuilder dueDate = new StringBuilder();

            int byTokenIndex = -1;
            for (int i = 1; i < tokens.length; i++) {
                if (tokens[i].equalsIgnoreCase("/by")) {
                    byTokenIndex = i;
                }
            }

            if (byTokenIndex == -1) {
                throw new MissingTokenException(taskType, "/by");
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

            taskList.add(new Deadline(taskName.toString(),
                    dueDate.toString()));
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

            if (fromTokenIndex == -1) {
                throw new MissingTokenException(taskType, "/from");
            }

            if (toTokenIndex == -1) {
                throw new MissingTokenException(taskType, "/from");
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
            taskList.add(new Event(taskName.toString(),
                    startDate.toString(),
                    endDate.toString()));
        }

        Task addedTask = taskList.get(currentTaskNumber);
        currentTaskNumber++;
        System.out.println(horizontalSeparator);
        System.out.println("Got it! I have added this task:\n" + addedTask);
        System.out.println(String.format("Now you have %d %s in the list",
                currentTaskNumber,
                currentTaskNumber == 1 ? "task" : "tasks"));
        System.out.println(horizontalSeparator + "\n");
    }

    // List out all the existing tasks in the application
    private static void listTasks() {
        System.out.println(horizontalSeparator);
        for (int i = 1; i <= currentTaskNumber; i++) {
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
        System.out.println(horizontalSeparator);
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(task);
        System.out.println(horizontalSeparator + "\n");
    }

    // Delete the task with the corresponding task number
    private static void deleteTask(int taskNumber) {
        int taskIndex = taskNumber - 1;
        String deletedTask = taskList.get(taskIndex).toString();
        taskList.remove(taskIndex);
        currentTaskNumber--;
        System.out.println(horizontalSeparator);
        System.out.println("Noted. I've removed this task:");
        System.out.println(deletedTask);
        System.out.println(horizontalSeparator + "\n");
    }

    // Say goodbye to the user and exit the application
    private static void exit () {
        System.out.println(horizontalSeparator);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalSeparator + "\n");
    }

    public static void main(String[] args) {
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
                try {
                    addTask(userInput);
                } catch (UnknownCommandException
                        | EmptyTaskDescriptionException
                        | MissingTokenException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        exit();
    }
}
