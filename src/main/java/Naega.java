import java.util.Scanner;
import java.util.ArrayList;

public class Naega {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Naega");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>();
        String userInput;

        // Echo loop until user types "bye"
        while (true) {
            userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (userInput.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(" " + (i + 1) + ". " + tasks.get(i));
                }
                System.out.println("____________________________________________________________");
            } else {
                tasks.add(userInput);
                System.out.println("____________________________________________________________");
                System.out.println(" " + userInput);
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close();
    }
}
