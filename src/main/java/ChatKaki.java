import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChatKaki {
    private static final String CHATBOT_NAME = "ChatKaki";
    private static final String DATE_FORMAT = "d/M/yyyy HHmm";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
    private static ArrayList<Task> taskHistory = new ArrayList<>();

    public static void main(String[] args) {
        greetUser();
        startChatService();
    }

    private static void greetUser() {
        loadTasksFromFile();
        printMessage("Hello! I'm " + CHATBOT_NAME + "\n What can I do for you?");
    }

    private static void sayGoodbye() {
        saveTasksToFile();
        printMessage("Bye. Hope to see you again soon!");
    }

    private static void listTasks() {
        StringBuilder listMessage = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < taskHistory.size(); i++) {
            listMessage.append("\n ").append(i + 1).append(". ").append(taskHistory.get(i));
        }
        printMessage(listMessage.toString());
    }

    private static void updateTaskStatus(String[] inputs, boolean isDone) {
        int index = Integer.parseInt(inputs[1]);
        if (index < 1 || index > taskHistory.size()) {
            printMessage("Invalid Task number.");
            return;
        }
        Task task = taskHistory.get(index - 1);
        if (isDone) {
            task.markAsDone();
        } else {
            task.markAsUndone();
        }
    }

    private static void addTask(Task task) {
        taskHistory.add(task);
        printMessage("Got it. I've added this task:\n " + task + "\n Now you have " + taskHistory.size() + " task" + (taskHistory.size() == 1 ? "" : "s") + " in the list.");
    }

    private static boolean isValidDateFormat(String date) {
        try {
            LocalDateTime.parse(date, FORMATTER);
            return true;
        } catch (Exception e) {
            printMessage("Invalid date format, it should be " + DATE_FORMAT);
            return false;
        }
    }

    private static LocalDateTime parseDate(String date) {
        try {
            return LocalDateTime.parse(date, FORMATTER);
        } catch (Exception e) {
            printMessage("Invalid date format, it should be " + DATE_FORMAT);
            return null;
        }
    }

    private static void createTask(String[] inputs, TaskType taskType) {
        if (inputs.length <= 1) {
            printMessage("The description of a " + taskType.name().toLowerCase() + " cannot be empty.");
            return;
        }

        switch (taskType) {
            case TODO:
                addTask(new Todo(false, inputs[1]));
                break;
            case DEADLINE:
                createDeadlineTask(inputs[1]);
                break;
            case EVENT:
                createEventTask(inputs[1]);
                break;
        }
    }

    private static void createDeadlineTask(String input) {
        String[] parts = input.split(" /by ");
        if (parts.length != 2 || !isValidDateFormat(parts[1])) {
            printMessage("Invalid Deadline format, it should contain /by and a valid date.");
            return;
        }
        LocalDateTime dateTime = parseDate(parts[1]);
        addTask(new Deadline(false, parts[0], dateTime));
    }

    private static void createEventTask(String input) {
        String[] parts = input.split(" /from | /to ");
        if (parts.length != 3 || !isValidDateFormat(parts[1]) || !isValidDateFormat(parts[2])) {
            printMessage("Invalid Event format, it should contain /from and /to with valid dates.");
            return;
        }
        LocalDateTime start = parseDate(parts[1]);
        LocalDateTime end = parseDate(parts[2]);
        addTask(new Event(false, parts[0], start, end));
    }

    private static void deleteTask(String[] inputs) {
        if (inputs.length <= 1) {
            printMessage("The description of a delete cannot be empty, add an index");
            return;
        }

        int index = Integer.parseInt(inputs[1]) - 1;
        if (index < 0 || index >= taskHistory.size()) {
            printMessage("Index is out of range, there are only " + taskHistory.size() + " task(s)");
            return;
        }

        Task task = taskHistory.remove(index);
        printMessage("Noted. I've removed this task:\n   " + task + "\nNow you have " + taskHistory.size() + " task" + (taskHistory.size() == 1 ? "" : "s") + " in the list.");
    }

    private static void loadTasksFromFile() {
        try {
            File file = new File("data/tasks.txt");
            if (!file.exists()) {
                printMessage("Starting a new task list");
                return;
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String task = scanner.nextLine();
                String[] taskParts = task.split(",");
                TaskType taskType = TaskType.valueOf(taskParts[0]);
                switch (taskType) {
                    case TODO:
                        taskHistory.add(new Todo(Boolean.parseBoolean(taskParts[1]), taskParts[2]));
                        break;
                    case DEADLINE:
                        LocalDateTime dateTime = parseDate(taskParts[3]);
                        taskHistory.add(new Deadline(Boolean.parseBoolean(taskParts[1]), taskParts[2], dateTime));
                        break;
                    case EVENT:
                        LocalDateTime start = parseDate(taskParts[3]);
                        LocalDateTime end = parseDate(taskParts[4]);
                        taskHistory.add(new Event(Boolean.parseBoolean(taskParts[1]), taskParts[2], start, end));
                        break;
                }
            }
        } catch (Exception e) {
            printMessage(e.getMessage());
        }
    }

    private static void saveTasksToFile() {
        try {
            File file = new File("data/tasks.txt");
            if (!file.exists()) {
                file.getParentFile().mkdirs(); // Create the directory if it does not exist
                file.createNewFile(); // Create the file if it does not exist
            }
            FileWriter fileWriter = new FileWriter(file);
            for (Task task : taskHistory) {
                fileWriter.write(task.fileFormat() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            printMessage(e.getMessage());
        }
    }

    private static void startChatService() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            String[] inputs = userInput.split(" ", 2);
            Command command = Command.fromString(inputs[0]);

            switch (command) {
                case DELETE:
                    deleteTask(inputs);
                    break;
                case TODO:
                    createTask(inputs, TaskType.TODO);
                    break;
                case DEADLINE:
                    createTask(inputs, TaskType.DEADLINE);
                    break;
                case EVENT:
                    createTask(inputs, TaskType.EVENT);
                    break;
                case BYE:
                    sayGoodbye();
                    return;
                case MARK:
                    updateTaskStatus(inputs, true);
                    break;
                case UNMARK:
                    updateTaskStatus(inputs, false);
                    break;
                case LIST:
                    listTasks();
                    break;
                default:
                    printMessage("Command not found, try another one!");
                    break;
            }
        }
    }

    private static void printMessage(String message) {
        System.out.println("\n____________________________________________________________");
        System.out.println(" " + message);
        System.out.println("____________________________________________________________\n");
    }

    private enum TaskType {
        TODO, DEADLINE, EVENT
    }
}