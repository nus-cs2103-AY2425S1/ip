import java.util.ArrayList;
import java.util.Scanner;

public class ChatKaki {
    private static final String CHATBOT_NAME = "ChatKaki";
    private static ArrayList<Task> taskHistory = new ArrayList<>();

    public static void main(String[] args) {
        sayGreeting();
        chatService();
    }

    private static void sayGreeting() {
        printMessage("Hello! I'm " + CHATBOT_NAME + "\n What can I do for you?");
    }

    private static void sayBye() {
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
                    addTask(new Todo(inputs[1]));
                    break;
                case DEADLINE:
                    String[] deadlineParts = inputs[1].split(" /by ");
                    if (deadlineParts.length == 2) {
                        addTask(new Deadline(deadlineParts[0], deadlineParts[1]));
                    } else {
                        printMessage("Invalid Deadline format, it should contain /by");
                    }
                    break;
                case EVENT:
                    String[] eventParts = inputs[1].split(" /from | /to ");
                    if (eventParts.length == 3) {
                        addTask(new Event(eventParts[0], eventParts[1], eventParts[2]));
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
