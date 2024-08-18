import java.util.ArrayList;
import java.util.Scanner;
import java.util.ArrayList;

public class ChatKaki {
    private static final String CHATBOT_NAME = "ChatKaki";
    private static final int MAX_TASKS = 100;
    private static ArrayList<Task> taskHistory = new ArrayList<>();

    public static void sayGreeting() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm " + CHATBOT_NAME);
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________\n");
    }

    public static void sayBye() {
        System.out.println("\n____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________\n");
    }

    public static void sayList() {
        System.out.println("\n____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i=0; i < taskHistory.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + taskHistory.get(i));
        }
        System.out.println("____________________________________________________________\n");
    }

    public static void markDone(String[] inputs) {
        int index = Integer.parseInt(inputs[1]);
        if (index < 1 || index > taskHistory.size()) {
            System.out.println(" Invalid Task number. ");
            return;
        }
        taskHistory.get(index-1).MarkAsDone();
    }

    public static void markAsUndone(String[] inputs) {
        int index = Integer.parseInt(inputs[1]);
        if (index < 1 || index > taskHistory.size()) {
            System.out.println(" Invalid Task number. ");
            return;
        }
        taskHistory.get(index-1).MarkAsUndone();
    }

    private static void addTask(Task task) {
         taskHistory.add(task);
         System.out.println("\n____________________________________________________________");
         System.out.println(" Got it. I've added this task:");
         System.out.println("   " + task.toString());
         System.out.println(" Now you have " + taskHistory.size() + " task" + (taskHistory.size() == 1 ? "" : "s") +  " in the list.");
         System.out.println("____________________________________________________________\n");

    }

    public static void createTodo(String[] inputs) {
        if (inputs.length > 1) {
            addTask(new Todo(inputs[1]));
        } else {
            System.out.println(" The description of a todo cannot be empty.");
        }
    }

    public static void createDeadline(String[] inputs) {
        if (inputs.length > 1) {
            String[] deadlineParts = inputs[1].split(" /by ");
            if (deadlineParts.length == 2) {
                addTask(new Deadline(deadlineParts[0], deadlineParts[1]));
            } else {
                System.out.println(" Invalid Deadline format, it should contain /by");
            }
        } else {
            System.out.println(" The description of a todo cannot be empty.");
        }
    }

    public static void createEvent(String[] inputs) {
        if (inputs.length > 1) {
            String[] eventParts = inputs[1].split(" /from | /to ");
             if (eventParts.length == 3) {
                 addTask(new Event(eventParts[0], eventParts[1], eventParts[2]));
             } else {
                 System.out.println(" Invalid Event format, it should contain /from and /to");
             }
        } else {
            System.out.println(" The description of a todo cannot be empty.");
        }
    }

    public static void deleteItem(String[] inputs) {
        if (inputs.length > 1) {
            int index = Integer.parseInt(inputs[1]) - 1;
            if (index < 0 | index >= taskHistory.size()) {
                System.out.println(" Index is out of range, there are only " + taskHistory.size() + " task(s)");
            } else {
                Task task = taskHistory.get(index);
                taskHistory.remove(index);
                System.out.println("\n____________________________________________________________");
                System.out.println(" Noted. I've removed this task:");
                System.out.println("   " + task.toString());
                System.out.println(" Now you have " + taskHistory.size() + " task" + (taskHistory.size() == 1 ? "" : "s") +  " in the list.");
                System.out.println("____________________________________________________________\n");
            }
        } else {
            System.out.println(" The description of a delete cannot be empty, add an index");
        }
    }

    public static void chatService() {
        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            userInput = scanner.nextLine();
            String[] inputs = userInput.split(" ", 2);
            Command command = Command.fromString(inputs[0]);

            switch (command) {
                case DELETE:
                    deleteItem(inputs);
                    break;
                case TODO:
                    createTodo(inputs);
                    break;
                case DEADLINE:
                    createDeadline(inputs);
                    break;
                case EVENT:
                    createEvent(inputs);
                    break;
                case BYE:
                    sayBye();
                    return;
                case MARK:
                    markDone(inputs);
                    break;
                case UNMARK:
                    markAsUndone(inputs);
                    break;
                case LIST:
                    sayList();
                    break;
                default:
                    System.out.println("Command not found, try another one!");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        sayGreeting();
        chatService();
    }
}
