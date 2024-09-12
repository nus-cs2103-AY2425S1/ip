package nave;

/**
 * The {@code Ui} class is responsible for interacting with the user through
 * textual responses. It provides methods for greeting the user, showing help
 * messages, displaying task-related responses, and formatting these responses
 * for better readability.
 */
public class Ui {

    /**
     * Displays a greeting message to the user.
     * <p>
     * This method prints a welcome message to the console, introducing
     * the {@code Nave} application and prompting the user for input.
     * </p>
     */
    public void greet() {
        System.out.println(formatResponse("Hello! :) I'm Nave, your personal task management assistant.\n"
                + "What can I do for you today?"));
    }

    /**
     * Displays a farewell message to the user.
     * <p>
     * This method prints a goodbye message to the console, indicating that
     * the application is ending.
     * </p>
     */
    public void sayFarewell() {
        System.out.println(formatResponse("Goodbye :( Come visit me again"));
    }

    /**
     * Displays a help message that lists all available commands.
     * <p>
     * This method prints a help message to the console, providing the user
     * with a list of commands they can use to interact with the {@code Nave}
     * application.
     * </p>
     */
    public void helpMessage() {
        System.out.println(formatResponse("""
            /help: shows all available commands
            list: shows all tasks
            todo [name]: adds a todo with associated name
            deadline [name] /by [date]: adds a deadline with associated name and date
            event [name] /from [date] /to [date]: adds an event with associated name,
                start date and end date
            find [keyword]: finds tasks that contain the keyword in their name
            bye: ends the Nave chatbot"""));
    }

    /**
     * Displays a message indicating that the user's input was not understood.
     * <p>
     * This method prints a message to the console when the application does
     * not recognize the user's command or input, and suggests using the
     * {@code /help} command.
     * </p>
     */
    public void unsureMessage() {
        System.out.println(formatResponse("I'm not sure what you want me to do! try /help"));
    }

    /**
     * Displays a given response message to the user.
     * <p>
     * This method prints the provided {@code response} to the console,
     * formatted with borders for better readability.
     * </p>
     *
     * @param response the message to be displayed
     */
    public void showResponse(String response) {
        System.out.println(formatResponse(response));
    }

    /**
     * Formats a response message with borders for better readability.
     * <p>
     * This method wraps the provided {@code input} message in a bordered format,
     * which is used for displaying responses to the user.
     * </p>
     *
     * @param input the message to be formatted
     * @return the formatted message with borders
     */
    public static String formatResponse(String input) {
        return "-----------------------------------------------------------------\n"
                + input + "\n"
                + "-----------------------------------------------------------------";
    }
}
