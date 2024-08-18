import java.util.Scanner;

public class ChatKaki {
    private static final String CHATBOT_NAME = "ChatKaki";

    public static void sayGreeting() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm " + CHATBOT_NAME);
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________\n");
    }

    public static void sayMessage(String message) {
        System.out.println("\n____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________\n");
    }

    public static void sayBye() {
        System.out.println("\n____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");

    }

    public static void chatService() {
        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                sayBye();
                break;
            }
            sayMessage(userInput);
        }
    }

    public static void main(String[] args) {
        sayGreeting();
        chatService();
    }
}
