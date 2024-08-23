import java.util.Scanner;

public class ChatGPT {
    private static String NAME = "ChatGPT";
    private static String LINE = "________________________________________________";
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        sendGreeting();
        do {
            String input = userInput.nextLine();
            System.out.println("\t"+LINE);
            if (input.equals("bye")) {
                break;
            } else {
                System.out.println(input);
                System.out.println("\t"+LINE);
            }
        } while (true);
        sendExit();

    }

    private static void sendGreeting() {
        System.out.println("\t"+LINE);
        System.out.println("\tHello! I'm " + NAME);
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t"+LINE);
    }

    private static void sendExit() {
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t"+LINE);
    }
}
