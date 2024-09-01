package gray;

import gray.command.Command;

/**
 * Represents the chatbot gray
 */
public class Gray {

    private final Tasks tasks;

    public Gray(Tasks tasks) {
        this.tasks = tasks;
    }

    /**
     * Respond for chatbot
     *
     * @param text
     * @return
     */
    public String respond(String text) {
        try {
            Command command = Parser.parse(text);
            return command.execute(tasks);
        } catch (GrayException e) {
            return e.getMessage();
        }
    }

    public String welcome() {
        return "Hello! I am chatbot Gray. How may I assist today?";
    }
}
