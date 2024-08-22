import java.util.*;

public class Susan {
    public static void main(String[] args) {
        // Greet
        System.out.println("Hi there, Susan's at your service!");
        System.out.println("How may I assist you?");

        // Initialisation
        Scanner scanner = new Scanner(System.in);
        List<String> tasks = new ArrayList<>();
        String userInput = scanner.nextLine();

        // Add, List
        while (!userInput.equalsIgnoreCase("bye")) {
            // Print the list
            if (userInput.equalsIgnoreCase("list")) {
                System.out.println("Babes. YOU NEED TO");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
            } else {
                // Add task to list
                tasks.add(userInput.toUpperCase());
                System.out.println("OK busy girl! added: " + userInput);
            }
            // Read next input
            userInput = scanner.nextLine();
        }
        // Exit
        System.out.println("Good bye, slay the day!");
    }
}
