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

            String[] dissectedPrompt = prompt.trim().split(" ", 2);
            String command = dissectedPrompt[0];
            String argument = null;
            if (dissectedPrompt.length == 2) {
                argument = dissectedPrompt[1].trim();
            }

            try {
                printDivider();
                switch (command) {
                    case "bye":
                        // Check for excess arguments
                        if (argument != null) {
                            throw new TohruException(String.format("Invalid argument: %s does not accept %s",
                                    command, argument));
                        }

                        System.out.println("Bye. Hope to see you again soon!");
                        toExit = true;
                        break;

                    case "list":
                        // Check for excess arguments
                        if (argument != null) {
                            throw new TohruException(String.format("Invalid argument: %s does not accept %s",
                                    command, argument));
                        }

                        System.out.println(String.format("These are %s entries on your todo:", todoList.getTotal()));
                        for (String todoItem : todoList.listItems()) {
                            System.out.println(todoItem);
                        }
                        break;

                    case "mark":
                        // Check if no arguments are provided
                        if (argument == null) {
                            throw new TohruException("Missing argument: Specify index to mark");
                        }

                        int markItemIndex = Integer.parseInt(argument) - 1;
                        // Check for valid index
                        if (markItemIndex < 0 || markItemIndex >= todoList.getTotal()) {
                            throw new TohruException("The entry you are looking to mark cannot be found");
                        }

                        if (todoList.markComplete(markItemIndex)) {
                            System.out.println("Alright! I have set this task as done:");
                        } else {
                            System.out.println("Unable to set this task as done:");
                        }
                        System.out.println(todoList.getItemStatus(markItemIndex));
                        break;

                    case "unmark":
                        // Check if no arguments are provided
                        if (argument == null) {
                            throw new TohruException("Missing argument: Specify index to unmark");
                        }

                        int unmarkItemIndex = Integer.parseInt(argument) - 1;
                        // Check for valid index
                        if (unmarkItemIndex < 0 || unmarkItemIndex >= todoList.getTotal()) {
                            throw new TohruException("The entry you are looking to unmark cannot be found");
                        }

                        if (todoList.markIncomplete(unmarkItemIndex)) {
                            System.out.println("Alright! I have set this task as not done:");
                        } else {
                            System.out.println("Unable to set this task as not done:");
                        }
                        System.out.println(todoList.getItemStatus(unmarkItemIndex));
                        break;

                    case "todo":
                        // Check if no arguments are provided
                        if (argument == null) {
                            throw new TohruException("Missing argument: Please specify description");
                        }

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
                        // Check if no arguments are provided
                        if (argument == null) {
                            throw new TohruException("Missing argument: Please specify description");
                        }

                        String[] dissectedDeadlineArgument = argument.split("/by", 2);
                        String deadlineContent = dissectedDeadlineArgument[0];
                        // Check for valid description
                        if (deadlineContent.isBlank()) {
                            throw new TohruException("Missing argument: Please specify description");
                        }
                        String deadline = dissectedDeadlineArgument[1];
                        // Check for valid deadline
                        if (deadline.isBlank()) {
                            throw new TohruException("Missing argument: Please specify deadline");
                        }
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
                        // Check if no arguments are provided
                        if (argument == null) {
                            throw new TohruException("Missing argument: Specify description");
                        }

                        String[] dissectedEventArgument = argument.split("/from|/to",3);
                        // Check if all arguments are present
                        if (dissectedEventArgument.length < 3) {
                            throw new TohruException("Missing argument: Missing either description, from date or to date");
                        }
                        String eventContent = dissectedEventArgument[0];
                        // Check for valid description
                        if (eventContent.isBlank()) {
                            throw new TohruException("Missing argument: Please specify description");
                        }
                        String from = dissectedEventArgument[1];
                        // Check for valid from date
                        if (from.isBlank()) {
                            throw new TohruException("Missing argument: Please specify from date");
                        }
                        String to = dissectedEventArgument[2];
                        // Check for valid to date
                        if (to.isBlank()) {
                            throw new TohruException("Missing argument: Please specify to date");
                        }
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
                        System.out.println("You have entered an invalid option! Please select again.");
                        break;
                }
                printDivider();
            } catch (TohruException e) {
                System.out.println(e.getMessage());
                printDivider();
            } catch (NumberFormatException e) {
                System.out.println(String.format("%s is not valid index for %s operation", argument, command));
                printDivider();
            }
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
