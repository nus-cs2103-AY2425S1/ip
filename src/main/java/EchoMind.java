import java.util.Scanner;

public class EchoMind {

    public static void sendMessage(String msg) {
        System.out.println("[EchoMind] " + msg);
    }

    public static void main(String[] args) {
        sendMessage("Hello! I'm EchoMind!");
        sendMessage("What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        String message = "";
        while (true) {
            message = scanner.nextLine();
            if (message.equalsIgnoreCase("bye")) {
                break;
            }
            sendMessage(message);
        }
        sendMessage("Bye. Hope to see you again soon!");
    }
}
