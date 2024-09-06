package alexer;

/**
 * Defines a prompter class that handles responses by the chatbot
 * to users and formatting of the output messages.
 *
 * @author sayomaki
 */
public class Prompter {
    private static final String BREAK = "____________________________________________________________";

    /**
     * Prints the logo of the chatbot
     */
    public void printLogo() {
        printResponse(Alexer.LOGO);
    }

    /**
     * Prints a greeting message to the user
     */
    public void printGreeting() {
        printResponse(String.format("Hello from %s, what can I do for you today?", Alexer.NAME));
    }

    /**
     * Prints the goodbye message to the user
     * (when chatbot is terminating)
     */
    public void printGoodbye() {
        printResponse("Goodbye! If you ever want to chat again, I'll be here.\nHave a great day!");
    }

    /**
     * Prints the list of tasks created by the user
     */
    public void printTaskList(String tasks) {
        printResponse("Sure thing! Here is your task list:\n\n" + tasks);
    }

    public void printFilteredTaskList(String tasks) {
        printResponse("I got you! Here is what I found:\n" + tasks);
    }

    /**
     * Prints the chatbot response with break lines
     * 
     * @param response The message to be printed
     */
    public void printResponse(String response) {
        System.out.println(BREAK);
        System.out.println(response);
        System.out.println(BREAK);
    }
}
