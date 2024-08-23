
import java.util.Scanner;

public class Bob {

    private static void dialogue(String input) {
        System.out.println("___________________________________\n");
        System.out.println(input);
        System.out.println("___________________________________\n");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        dialogue("Hello! I'm Bob\n"
                + "What can I do for you?");

        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                dialogue("Bye. Hope to see you again soon!");
                break;
            } else {
                dialogue(userInput);
            }
        }

        scanner.close();
    }
}
