package alexer;

import alexer.ui.Response;

/**
 * Defines a prompter class that creates and manages responses
 * by the chatbot to users and formatting of the output messages.
 *
 * @author sayomaki
 */
public class Prompter {
    /** Wordart logo of the chatbot **/
    public static final String LOGO = """
                     .     .                           
                    /|     |     ___  _  .-   ___  .___
                   /  \\    |   .'   `  \\,'  .'   ` /   \\
                  /---'\\   |   |----'  /\\   |----' |   '
                ,'      \\ /\\__ `.___, /  \\  `.___, /   """;

    /**
     * Builds the response containing logo of the chatbot
     * @return the response instance with the logo
     */
    public Response buildLogo() {
        return new Response(LOGO);
    }

    /**
     * Builds a response containing greeting message to the user
     * @return the response instance with the greeting message
     */
    public Response buildGreeting() {
        return new Response(String.format("Hello from %s, what can I do for you today?", Alexer.NAME));
    }

    /**
     * Builds a response containing the goodbye message for the user
     * (when chatbot is terminating)
     * @return the response instance with the goodbye message
     */
    public Response buildGoodbye() {
        return new Response("Goodbye! If you ever want to chat again, I'll be here.\nHave a great day!");
    }

    /**
     * Builds a response with the list of tasks created by the user
     * @param tasks the list of tasks to be included
     * @return the response instance with the list of tasks
     */
    public Response buildTaskList(String tasks) {
        return new Response("Sure thing! Here is your task list:\n\n" + tasks);
    }

    /**
     * Builds a response with the list of filtered tasks
     * @param tasks the list of tasks to be included
     * @return the response instance with the list of tasks
     */
    public Response buildFilteredTaskList(String tasks) {
        return new Response("I got you! Here is what I found:\n" + tasks);
    }
}
