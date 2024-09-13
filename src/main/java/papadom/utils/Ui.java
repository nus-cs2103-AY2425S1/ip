package papadom.utils;
/**
 * Handles user interaction by displaying messages.
 */
public class Ui {
    public static final String HELLO_MESSAGE = " Hello! I'm Papadom\n What can I do for you? ";
    public static final String GOODBYE_MESSAGE = " Bye. Hope to see you again soon!";
    public static final String HELP_MESSAGE = "Here are the available commands:\n\n"
            + " 1. todo [task]: Adds a new todo task\n"
            + "   Example: todo buy groceries\n\n"
            + " 2. deadline [task] /by [date]: Adds a task with a deadline\n"
            + "   Example: deadline submit report /by 2024-09-15\n\n"
            + " 3. event [task] /from [start time] /to [end time]: Adds an event with start and end times\n"
            + "   Example: event project meeting /from 2024-09-10 14:00 /to 2024-09-10 16:00\n\n"
            + " 4. list: Lists all tasks\n\n"
            + " 5. mark [task number]: Marks a task as done\n"
            + "   Example: mark 2\n\n"
            + " 6. unmark [task number]: Unmarks a completed task\n"
            + "   Example: unmark 2\n\n"
            + " 7. delete [task number]: Deletes a task\n"
            + "   Example: delete 3\n\n"
            + " 8. find [keyword]: Finds tasks that match the given keyword\n"
            + "   Example: find meeting\n\n"
            + " 9. bye: Exits the application";

    /**
     * Outputs a formatted message to the user.
     *
     * @param message The message to be displayed.
     */
    public String output(String message) {
        assert !message.isEmpty() : "Message cannot be empty";
        return message;
    }
    /**
     * Displays a welcome message when the chatbot starts.
     */
    public static String welcomeMessage() {
       return HELLO_MESSAGE + HELP_MESSAGE;
    }
    /**
     * Displays a farewell message when the chatbot exits.
     */
    public String exitMessage() {
        return GOODBYE_MESSAGE;
    }
}

