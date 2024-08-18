import java.util.Scanner;

public class ChatKaki {
    private static final String CHATBOT_NAME = "ChatKaki";
    private static final int MAX_TASKS = 100;
    private static Task[] taskHistory = new Task[MAX_TASKS];

    public static void sayGreeting() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm " + CHATBOT_NAME);
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________\n");
    }

    //deprecated
    public static void sayTask(String message) {
        taskHistory[Task.taskCount] = new Task(message);
        System.out.println("\n____________________________________________________________");
        System.out.println("added: " + message);
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
        for (int i=0; i < Task.taskCount; i++) {
            System.out.println(" " + (i + 1) + ". " + taskHistory[i]);
        }
        System.out.println("____________________________________________________________\n");
    }


    public static void markDone(String[] inputs) {
        int index = Integer.parseInt(inputs[1]);
        if (index < 1 || index > Task.taskCount) {
            System.out.println(" Invalid Task number. ");
            return;
        }
        taskHistory[index-1].MarkAsDone();
    }

    public static void markAsUndone(String[] inputs) {
        int index = Integer.parseInt(inputs[1]);
        if (index < 1 || index > Task.taskCount) {
            System.out.println(" Invalid Task number. ");
            return;
        }
        taskHistory[index-1].MarkAsUndone();
    }

    private static void addTask(Task task) {
         taskHistory[Task.taskCount - 1] = task;
         System.out.println("\n____________________________________________________________");
         System.out.println(" Got it. I've added this task:");
         System.out.println("   " + task.toString());
         System.out.println(" Now you have " + Task.taskCount + " task" + (Task.taskCount == 1 ? "" : "s") +  " in the list.");
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
                System.out.println(" Invalid Deadline format");
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
                 System.out.println(" Invalid Event format");
             }
        } else {
            System.out.println(" The description of a todo cannot be empty.");
        }
    }

    public static void chatService() {
        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            userInput = scanner.nextLine();
            String[] inputs = userInput.split(" ", 2);
            String command = inputs[0];

            switch (command) {
                case "todo":
                    createTodo(inputs);
                    break;
                case "deadline":
                    createDeadline(inputs);
                    break;
                case "event":
                    createEvent(inputs);
                    break;
                case "bye":
                    sayBye();
                    return;
                case "mark":
                    markDone(inputs);
                    break;
                case "unmark":
                    markAsUndone(inputs);
                    break;
                case "list":
                    sayList();
                    break;
                default:
                    System.out.println("Command not found");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        sayGreeting();
        chatService();
    }
}
