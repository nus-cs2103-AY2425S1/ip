package jude;

import jude.command.Command;

/**
 * Represents a jude.Jude, the personal assistant chatbot.
 *
 * This class helps a person to keep track of various things by simulating the chatbot features
 */
public class Jude {

    private String name = "jude.Jude";
    private TaskList tasks;
    private Storage storage;
    private Parser parser;
    private String commandType;
    private boolean isExit = false;
    /**
     * Takes filePath to initialise the setups for Jude the Chatbot.
     *
     * @param filePath
     */
    public Jude(String filePath) {
        this.storage = new Storage(filePath);
        this.parser = new Parser();
        try {
            this.tasks = new TaskList(storage.load());
        } catch (JudeException je) {
            //TODO: Show more informative error message depending on the error that was thrown
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Jude("data/jude.txt").run();
    }

    /**
     * Generates a welcome message from the bot.
     *
     * @return A welcome message.
     */
    public String generateWelcomeMessage() {
        return "Hi, I'm Jude."
                + System.lineSeparator()
                + "Just let me know if you ever find yourself in a pinch. I can help you out.";
    }


    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, storage);
            commandType = c.getClass().getSimpleName();
            return c.getMessage();
        } catch (JudeException e) {
            return "Error: " + e.getMessage();
        }
    }

    public String getCommandType() {
        return commandType;
    }
}


