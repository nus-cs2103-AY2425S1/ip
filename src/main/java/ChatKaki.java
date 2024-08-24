import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class ChatKaki {
    private static final String CHATBOT_NAME = "ChatKaki";
    private static ArrayList<Task> taskHistory = new ArrayList<>();

    public static void main(String[] args) {
        sayGreeting();
        chatService();
    }

    private static void sayGreeting() {
        readTasksFromFile();
        printMessage("Hello! I'm " + CHATBOT_NAME + "\n What can I do for you?");
    }

    private static void sayBye() {
        writeTasksToFile();
        printMessage("Bye. Hope to see you again soon!");
    }

    private static void sayList() {
        StringBuilder listMessage = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < taskHistory.size(); i++) {
            listMessage.append("\n ").append(i + 1).append(". ").append(taskHistory.get(i));
        }
        printMessage(listMessage.toString());
    }

    private static void markTask(String[] inputs, boolean isDone) {
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

    private static void createTask(String[] inputs, TaskType taskType) {
        if (inputs.length > 1) {
            switch (taskType) {
                case TODO:
                    addTask(new Todo(false, inputs[1]));
                    break;
                case DEADLINE:
                    String[] deadlineParts = inputs[1].split(" /by ");
                    if (deadlineParts.length == 2) {
                        addTask(new Deadline(false, deadlineParts[0], deadlineParts[1]));
                    } else {
                        printMessage("Invalid Deadline format, it should contain /by");
                    }
                    break;
                case EVENT:
                    String[] eventParts = inputs[1].split(" /from | /to ");
                    if (eventParts.length == 3) {
                        addTask(new Event(false, eventParts[0], eventParts[1], eventParts[2]));
                    } else {
                        printMessage("Invalid Event format, it should contain /from and /to");
                    }
                    break;
            }
        } else {
            printMessage("The description of a " + taskType.name().toLowerCase() + " cannot be empty.");
        }
    }

    private static void deleteTask(String[] inputs) {
        if (inputs.length > 1) {
            int index = Integer.parseInt(inputs[1]) - 1;
            if (index < 0 || index >= taskHistory.size()) {
                printMessage("Index is out of range, there are only " + taskHistory.size() + " task(s)");
            } else {
                Task task = taskHistory.remove(index);
                printMessage("Noted. I've removed this task:\n   " + task + "\nNow you have " + taskHistory.size() + " task" + (taskHistory.size() == 1 ? "" : "s") + " in the list.");
            }
        } else {
            printMessage("The description of a delete cannot be empty, add an index");
        }
    }

    private static void readTasksFromFile() {
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
                        taskHistory.add(new Deadline(Boolean.parseBoolean(taskParts[1]), taskParts[2], taskParts[3]));
                        break;
                    case EVENT:
                        taskHistory.add(new Event(Boolean.parseBoolean(taskParts[1]), taskParts[2], taskParts[3], taskParts[4]));
                        break;
                }
            }
        } catch (Exception e) {
            printMessage(e.getMessage());
        }
    }

    private static void writeTasksToFile() {
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

    private static void chatService() {
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
                    sayBye();
                    return;
                case MARK:
                    markTask(inputs, true);
                    break;
                case UNMARK:
                    markTask(inputs, false);
                    break;
                case LIST:
                    sayList();
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
