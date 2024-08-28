import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Bobby {

    private static final String FILE_PATH = "./src/main/data/Bobby.txt";
    enum Command {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, UNKNOWN, FIND;

        public static Command fromString(String input) {
            String command = input.split(" ")[0].toLowerCase();
            switch (command) {
                case "bye":
                    return BYE;
                case "list":
                    return LIST;
                case "mark":
                    return MARK;
                case "unmark":
                    return UNMARK;
                case "delete":
                    return DELETE;
                case "todo":
                    return TODO;
                case "deadline":
                    return DEADLINE;
                case "event":
                    return EVENT;
                case "find":
                    return FIND;
                default:
                    return UNKNOWN;
            }
        }
    }


    /**
     * This function greets the user.
     */
    private static void greet() {
        String greeting = "Hello I'm Bobby\n"
                + "What can I do for you today?";
        System.out.println(greeting);
    }

    /**
     * This function exits the chat app with a default message.
     */
    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }


    private static ArrayList<Task> tasks = new ArrayList<>();

    private static void addTask(Task task) {
        tasks.add(task);
        System.out.println("Task added successfully:");
        System.out.println("  " + task);
        System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
        saveTasksToFile();
    }

    private static void printTasks() {
        if (tasks.size() == 0) {
            System.out.println("No tasks added to the list yet.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(String.format("%d.%s", i + 1, tasks.get(i)));
            }
        }
    }

    private static void deleteTask(String userInput) throws BobbyException {
        String[] parts = userInput.split(" ");
        if (parts.length < 2 || parts[1].isEmpty()) {
            throw new InvalidCommandFormatException("delete", "task number");
        }
        try {
            int taskNumber = Integer.parseInt(parts[1]) - 1;
            Task removedTask = tasks.remove(taskNumber);
            System.out.println("Task removed successfully:");
            System.out.println("  " + removedTask);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            saveTasksToFile();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException();
        }
    }


    private static void handleTaskStatusUpdate(String userInput, boolean mark) throws BobbyException {
        String[] parts = userInput.split(" ");
        if (parts.length < 2 || parts[1].isEmpty()) {
            throw new InvalidCommandFormatException(mark ? "mark" : "unmark", "task number");
        }
        try {
            int taskNumber = Integer.parseInt(parts[1]) - 1;
            if (mark) {
                tasks.get(taskNumber).markTask();
                System.out.println("Nice! I've marked this task as done: " + tasks.get(taskNumber));
            } else {
                tasks.get(taskNumber).unmarkTask();
                System.out.println("OK, I've marked this task as not done yet: " + tasks.get(taskNumber));
            }
            saveTasksToFile();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException();
        }

    }

    private static void handleTask(String userInput) throws BobbyException {
        if (userInput.startsWith("todo ")) {
            String description = userInput.substring(5).trim();
            if (description.isEmpty()) {
                throw new EmptyTodoException();
            }
            Task task = new Todo(description);
            addTask(task);
        } else if (userInput.startsWith("deadline ")) {
            String[] parts = userInput.substring(9).split(" /by ");
            if (parts.length < 2) {
                throw new EmptyDeadlineException();
            }
            String description = parts[0];
            String byString = parts[1];
            try {
                LocalDate by = LocalDate.parse(byString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                Task task = new Deadline(description, by);
                addTask(task);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd");
            }
        } else if (userInput.startsWith("event ")) {
            String[] parts = userInput.substring(6).split(" /from | /to ");
            if (parts.length < 3) {
                throw new EmptyEventException();
            }
            String description = parts[0];
            String fromString = parts[1];
            String toString = parts[2];
            try {
                LocalDate from = LocalDate.parse(fromString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalDate to = LocalDate.parse(toString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                Task task = new Event(description, from, to);
                addTask(task);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd");
            }
        } else {
            throw new InvalidInputException();
        }
    }

    /**
     * Save tasks to file whenever the task list changes.
     */
    private static void saveTasksToFile() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            for (Task task : tasks) {
                writer.write(task.toFileString() + System.lineSeparator());
            }
            System.out.println("Task successfully added to file.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Load tasks from file when the program starts.
     */
    private static void loadTasksFromFile() {
        File file = new File(FILE_PATH);
        try {
            // Handle the case where the file does not exist.
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                return;
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                String taskType = parts[0];
                boolean isDone = parts[1].equals("true");
                String description = parts[2];

                Task task;
                switch (taskType) {
                    case "T":
                        task = new Todo(description);
                        break;
                    case "D":
                        String byString = parts[3];
                        LocalDate by = LocalDate.parse(byString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        task = new Deadline(description, by);
                        break;
                    case "E":
                        String fromString = parts[3];
                        String toString = parts[4];
                        LocalDate from = LocalDate.parse(fromString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        LocalDate to = LocalDate.parse(toString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        task = new Event(description, from, to);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + taskType);
                }

                if (isDone) {
                    task.markTask();
                }

                tasks.add(task);
            }
            scanner.close();
            System.out.println("Tasks successfully loaded from file Bobby.txt.");
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }

    private static void findTasksByDate(String userInput) {
        String[] parts = userInput.split(" ");
        if (parts.length < 2) {
            System.out.println("Please provide a date in the format yyyy-MM-dd.");
            return;
        }

        try {
            LocalDate date = LocalDate.parse(parts[1], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            System.out.println("Tasks on " + date + ":");
            boolean found = false;
            for (Task task : tasks) {
                if (task instanceof Deadline) {
                    Deadline deadlineTask = (Deadline) task;
                    if (deadlineTask.isOnDate(date)) {
                        System.out.println(task);
                        found = true;
                    }
                } else if (task instanceof Event) {
                    Event eventTask = (Event) task;
                    if (eventTask.isOnDate(date)) {
                        System.out.println(task);
                        found = true;
                    }
                }
            }
            if (!found) {
                System.out.println("No tasks found for the given date.");
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    public static void main(String[] args) {
        loadTasksFromFile();  // Load tasks from file at the start
        Scanner scanner = new Scanner(System.in);
        greet();
        boolean isRunning = true;
        while (isRunning) {
            String userInput = scanner.nextLine();
            Command command = Command.fromString(userInput);
            try {
                switch (command) {
                    case BYE:
                        exit();
                        isRunning = false;
                        break;
                    case LIST:
                        printTasks();
                        break;
                    case MARK:
                        handleTaskStatusUpdate(userInput, true);
                        break;
                    case UNMARK:
                        handleTaskStatusUpdate(userInput, false);
                        break;
                    case DELETE:
                        deleteTask(userInput);
                        break;
                    case TODO:
                    case DEADLINE:
                    case EVENT:
                        handleTask(userInput);
                        break;
                    case FIND:
                        findTasksByDate(userInput);
                        break;
                    default:
                        throw new InvalidInputException();
                }
            } catch (BobbyException e) {
                System.out.println("OOPS!!! " + e.getMessage());
            }
        }
        scanner.close();
    }

}
