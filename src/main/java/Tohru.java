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
            String argument = null;
            if (dissectedPrompt.length == 2) {
                argument = dissectedPrompt[1];
            }

            printDivider();
            switch (command) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                toExit = true;
                break;

            case "list":
                System.out.println(String.format("These are %s entries on your todo:", todoList.getTotal()));
                for (String todoItem : todoList.listItems()) {
                    System.out.println(todoItem);
                }
                break;

            case "mark":
                int markItemIndex = Integer.parseInt(argument) - 1;
                if (todoList.markComplete(markItemIndex)) {
                    System.out.println("Alright! I have set this task as done:");
                } else {
                    System.out.println("Unable to set this task as done:");
                }
                System.out.println(todoList.getItemStatus(markItemIndex));
                break;

            case "unmark":
                int unmarkItemIndex = Integer.parseInt(argument) - 1;
                if (todoList.markIncomplete(unmarkItemIndex)) {
                    System.out.println("Alright! I have set this task as not done:");
                } else {
                    System.out.println("Unable to set this task as not done:");
                }
                System.out.println(todoList.getItemStatus(unmarkItemIndex));
                break;

            case "todo":
                TodoItem newTodo = new TodoItem(argument);

                if (todoList.addItem(newTodo)) {
                    System.out.println(String.format("Added todo entry: %s", argument.trim()));
                    System.out.println(newTodo);
                } else {
                    System.out.println(String.format("Unable to add todo entry: %s", argument.trim()));
                }
                System.out.println(String.format("There are now %d total entries", todoList.getTotal()));
                break;

            case "deadline":
                String[] dissectedDeadlineArgument = argument.split("/by", 2);
                String deadlineContent = dissectedDeadlineArgument[0];
                String deadline = dissectedDeadlineArgument[1];
                DeadlineItem newDeadline = new DeadlineItem(deadlineContent, deadline);

                if (todoList.addItem(newDeadline)) {
                    System.out.println(String.format("Added deadline entry: %s", deadlineContent.trim()));
                    System.out.println(newDeadline);
                } else {
                    System.out.println(String.format("Unable to add deadline entry: %s", deadlineContent.trim()));
                }
                System.out.println(String.format("There are now %d total entries", todoList.getTotal()));
                break;

            case "event":
                String[] dissectedEventArgument = argument.split("/from", 2);
                String eventContent = dissectedEventArgument[0];
                String[] eventArgument = dissectedEventArgument[1].split("/to", 2);
                String from = eventArgument[0];
                String to = eventArgument[1];
                EventItem newEvent = new EventItem(eventContent, from, to);

                if (todoList.addItem(newEvent)) {
                    System.out.println(String.format("Added event entry: %s", eventContent.trim()));
                    System.out.println(newEvent);
                } else {
                    System.out.println(String.format("Unable to add event entry: %s", eventContent.trim()));
                }
                System.out.println(String.format("There are now %d total entries", todoList.getTotal()));
                break;

            default:
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
