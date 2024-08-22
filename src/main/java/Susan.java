import java.util.*;

public class Susan {
    public static void main(String[] args) {
        // Greet
        System.out.println("Hi there, Susan's at your service!");
        System.out.println("How may I assist you?");

        // Initialisation
        Scanner scanner = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        String userInput = scanner.nextLine();

        // Add, List
        while (!userInput.equalsIgnoreCase("bye")) {
            // Print the list
            if (userInput.equalsIgnoreCase("list")) {
                System.out.println("Babes. YOU NEED TO");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
            }
            // Mark as Undone
            else if (userInput.contains("unmark")) {
                int n = Integer.parseInt(userInput.replaceAll("[^0-9]", ""));
                tasks.get(n - 1).undoMark();
                System.out.println("OK. This task is not done yet:");
                System.out.println(tasks.get(n - 1));
            }
            // Mark as Done
            else if (userInput.contains("mark")) {
                int n = Integer.parseInt(userInput.replaceAll("[^0-9]", ""));
                tasks.get(n - 1).markAsDone();
                System.out.println("Good job! This task has been conquered:");
                System.out.println(tasks.get(n - 1));
            }
            // Add task to list
            else {
                tasks.add(new Task(userInput));
                System.out.println("OK busy girl! added: " + userInput);
            }
            // Read next input
            userInput = scanner.nextLine();
        }
        // Exit
        System.out.println("Good bye, slay the day!");
    }
}
