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

            String[] dissectedPrompt = prompt.split(" ", 2);
            String command = dissectedPrompt[0];

            printDivider();
            switch (command) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                toExit = true;
                break;
            case "list":
                System.out.println("These are on your todo:");
                for (String todoItem : todoList.listItems()) {
                    System.out.println(todoItem);
                }
                break;
            case "mark":
                String markArgument = dissectedPrompt[1];
                int markItemIndex = Integer.parseInt(markArgument) - 1;
                if (todoList.markComplete(markItemIndex)) {
                    System.out.println("Alright! I have set this task as done:");
                } else {
                    System.out.println("Unable to set this task as done:");
                }
                System.out.println(todoList.getItemStatus(markItemIndex));
                break;
            case "unmark":
                String unmarkArgument = dissectedPrompt[1];
                int unmarkItemIndex = Integer.parseInt(unmarkArgument) - 1;
                if (todoList.markIncomplete(unmarkItemIndex)) {
                    System.out.println("Alright! I have set this task as not done:");
                } else {
                    System.out.println("Unable to set this task as not done:");
                }
                System.out.println(todoList.getItemStatus(unmarkItemIndex));
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
