import java.util.Scanner;

public class ChatBuddy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("_____________________________________________");
            System.out.println("Hello! I'm ChatBuddy.\nLet me know how can I assist you today?");

            String userInput = scanner.nextLine();
            while (!userInput.equals("bye")) {
                System.out.println("_____________________________________________");
                System.out.println(userInput);
                userInput = scanner.nextLine();
            }
        } finally {
            System.out.println("_____________________________________________");
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println("_____________________________________________");
        }
    }
}
