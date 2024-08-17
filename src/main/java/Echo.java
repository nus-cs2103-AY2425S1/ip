import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Echo {
    private List<String> tasks;
    public Echo() {
        tasks = new ArrayList<>();
    }
    public static void main(String[] args) {
        Echo e = new Echo();
        // Creates a scanner object
        Scanner scanner = new Scanner(System.in);

        // Greets user
        String welcomeMsg =
                "_________________________________________\n" +
                "Hello! I'm Echo!\n" +
                "What can I do for you?\n" +
                "_________________________________________\n";
        System.out.println(welcomeMsg);

        Boolean isAcceptingInput = true;
        while (isAcceptingInput) { // handles input until user says bye
            isAcceptingInput = e.handleInput(scanner);
        }
    }
    // Handles user input
    public Boolean handleInput(Scanner s) {
        // Reads user input
        String userInput = s.nextLine();
        switch (userInput) {
            case "bye":
                System.out.println(
                        "_________________________________________\n" +
                        "Bye. Hope to see you again soon!\n" +
                        "_________________________________________\n");
                return false;
            case "list":
                printTasks();
                return true;
            default:
                tasks.add(userInput);
                System.out.println(
                        "_________________________________________\n" +
                        "added: " + userInput + "\n" +
                        "_________________________________________\n");
                return true;
        }
    }

    public void printTasks() {
        int count = 1;
        System.out.println("_________________________________________");
        for (String task : tasks) {
            System.out.println(count + ". " + task);
            count++;
        }
        System.out.println("_________________________________________");
    }
}
