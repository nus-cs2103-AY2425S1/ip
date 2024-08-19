import java.util.Scanner;

public class Tohru {
    public static void main(String[] args) {
        // Setup scanner to receive user input and set exit condition.
        TodoList todoList = new TodoList();
        Scanner userInput = new Scanner(System.in);
        boolean toExit = false;

        // Greetings
        printDivider();
        System.out.println("Hello! I'm Tohru");
        System.out.println("What can I do for you?");
        printDivider();

        // Process User response
        while (!toExit && userInput.hasNext()) {
            String prompt = userInput.nextLine();
            printDivider();
            switch (prompt) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                toExit = true;
                break;
            case "list":
                for (String todoItem : todoList.listItems()) {
                    System.out.println(todoItem);
                }
                break;
            default:
                if (todoList.addItem(prompt)) {
                    System.out.println(String.format("Added entry: %s", prompt));
                } else {
                    System.out.println(String.format("Unable to add entry: %s", prompt));
                }

                break;
            }
            printDivider();
        }

        // Close the scanner
        userInput.close();
    }

    /**
     * Helper function to print dividers
     */
    private static void printDivider() {
        System.out.println("____________________________________________________________");
    }
}
