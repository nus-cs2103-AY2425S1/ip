import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Vinegar {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> userInputList = new ArrayList<>();

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Vinegar\n" +
                "What Can I do for you?");
        System.out.println("____________________________________________________________");

        while(true) {
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < userInputList.size(); i++) {
                    System.out.println((i + 1) + ". " + userInputList.get(i));
                }
                System.out.println("____________________________________________________________");

            } else {
                userInputList.add(userInput);
                System.out.println("____________________________________________________________");
                System.out.println("added: " + userInput);
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close();
    }
}