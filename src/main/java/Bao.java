import java.util.Scanner;
public class Bao {
    private static String[] messages = new String[100];
    private static int messageCount = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String baoHi =   "     ___   \n"
                        + "   /     \\  \n"
                        + "  /       \\ \n"
                        + " |  ^   ^  |\n"
                        + " |    3    |\n"
                        + " \\________/ \n";

        String baoBye =   "     ___   \n"
                        + "   /     \\  \n"
                        + "  /       \\ \n"
                        + " |  T   T  |\n"
                        + " |    ^    |\n"
                        + " \\________/ \n";

        System.out.println("____________________________________________________________");
        System.out.println(baoHi);
        System.out.println("Hello! I'm Bao but you can call me Bao");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                System.out.println(baoBye);
                System.out.println("Bye :( Come back soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (input.equalsIgnoreCase("list")){
                System.out.println("____________________________________________________________");
                showMessages();
                System.out.println("____________________________________________________________");
            } else {
                addMessage(input);
                System.out.println("____________________________________________________________");
                System.out.println("added: " + input);
                System.out.println("____________________________________________________________");
            }
        }
        scanner.close();
    }

    private static void showMessages() {
        if (messageCount == 0) {
            System.out.println("You haven't told Bao anything yet!");
        } else {
            for (int i = 0; i < messageCount; i++) {
                System.out.println((i + 1) + ". " + messages[i]);
            }
        }
    }

    private static void addMessage(String message) {
        if (messageCount < 100) {
            messages[messageCount] = message;
            messageCount++;
        } else {
            System.out.println("Bao cannot remember so many thing :(");
        }
    }
}
