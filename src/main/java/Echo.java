import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Echo {
    private Tasks tasks;
    public Echo() {
        tasks = new Tasks();
    }
    // Handles user input
    public Boolean handleInput(Scanner s) {
        // Reads user input
        String userInput = s.nextLine();
        if (userInput.toLowerCase().startsWith("mark")) {
            int index = Integer.valueOf(userInput.substring(5));
            tasks.markTask(index);
            System.out.println(
                    "_________________________________________\n" +
                    "Nice! I've marked this task as done: ");
            tasks.printTask(index);
            System.out.print(
                    "_________________________________________\n");
            return true;
        } else if (userInput.toLowerCase().startsWith("unmark")) {
            int index = Integer.valueOf(userInput.substring(7));
            tasks.unmarkTask(index);
            System.out.println(
                    "_________________________________________\n" +
                    "Ok, I've marked this task as not done yet: ");
            tasks.printTask(index);
            System.out.print(
                    "_________________________________________\n");
            return true;
        } else {
            switch (userInput) {
                case "bye":
                    System.out.println(
                            "_________________________________________\n" +
                            "Bye. Hope to see you again soon!\n" +
                            "_________________________________________\n");
                    return false;
                case "list":
                    System.out.println("_________________________________________");
                    System.out.println("Here are the tasks in your list: ");
                    tasks.printTasks();
                    System.out.println("_________________________________________");
                    return true;
                default:
                    tasks.addTask(userInput);
                    System.out.println(
                            "_________________________________________\n" +
                            "added: " + userInput + "\n" +
                            "_________________________________________\n");
                    return true;
            }
        }
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
}
