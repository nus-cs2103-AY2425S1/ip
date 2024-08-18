import java.util.Scanner;

public class ChatKaki {
    private static final String CHATBOT_NAME = "ChatKaki";
    private static final int MAX_TASKS = 100;
    private static String[] messageHistory = new String[MAX_TASKS];
    private static int messageCount = 0;

    public static void sayGreeting() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm " + CHATBOT_NAME);
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________\n");
    }

    public static void sayMessage(String message) {
        messageHistory[messageCount++] = message;
        System.out.println("\n____________________________________________________________");
        System.out.println("added: " + message);
        System.out.println("____________________________________________________________\n");
    }

    public static void sayBye() {
        System.out.println("\n____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void sayList() {
        System.out.println("\n____________________________________________________________");
        for (int i=0; i < messageCount; i++) {
            System.out.println(" " + (i + 1) + ". " + messageHistory[i]);
        }
        System.out.println("____________________________________________________________");
    }

    public static void chatService() {
        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            userInput = scanner.nextLine();

            switch (userInput) {
                case "bye":
                    sayBye();
                    return;

                case "list":
                    sayList();
                    break;

                default:
                    sayMessage(userInput);
                    break;
            }
        }
    }

    public static void main(String[] args) {
        sayGreeting();
        chatService();
    }
}
