import java.util.Scanner;

public class PoChat {
    public static void main(String[] args) {
        String introMessage = "Hello! I'm PoChat, the chatbot in your pocket.\nWhat can I do for you?";
        String goodbyeMessage = "Bye. Hope to see you again soon!";

        System.out.println(introMessage);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println(goodbyeMessage);
                break;
            } else {
                System.out.println(input);
            }
        }
    }
}