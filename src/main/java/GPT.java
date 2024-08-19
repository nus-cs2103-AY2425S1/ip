import java.util.Scanner;
public class GPT {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String chatbotName = "GPT";

        System.out.println("Hello! I'm " + chatbotName);
        System.out.println("What can I do for you?");

        while (true) {
            // Read user input
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            System.out.println(input);
        }
        scanner.close();
    }
}
