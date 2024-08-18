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

    public static void chatService() {
        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            userInput = scanner.nextLine();
            String[] inputs = userInput.split(" ", 2);
            String command = inputs[0];

            switch (command) {
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
                    sayTask(userInput);
                    break;
            }
        }
    }

    public static void main(String[] args) {
        sayGreeting();
        chatService();
    }
}
