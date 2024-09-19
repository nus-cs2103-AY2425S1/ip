package jude;

import jude.command.Command;

/**
 * Represents a jude.Jude, the personal assistant chatbot.
 *
 * This class helps a person to keep track of various things by simulating the chatbot features.
 */
public class Jude {

    private String name = "jude.Jude";
    private TaskList tasks;
    private Storage storage;
    private String commandType;
    private boolean isExit = false;

    /**
     * Takes filePath to initialise the setups for Jude the Chatbot.
     *
     * @param filePath Path to where the files are stored.
     */
    public Jude(String filePath) {
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (JudeException je) {
            //TODO: Show more informative error message depending on the error that was thrown
            tasks = new TaskList();
        }
    }

    /**
     * Generates a welcome message from the bot.
     *
     * @return A welcome message.
     */
    public String generateWelcomeMessage() {
        return "Hi, I'm Jude."
                + System.lineSeparator()
                + "What can I help you?";
    }

    /**
     * Processes user input and generates a response.
     *
     * <p>
     * Parses the given user input to create a command, executes the command, and updates the task list
     * and storage. If an exception is thrown during parsing or execution, the error message is returned.
     * </p>
     *
     * @param input The user input to process.
     * @return The response generated from the command execution or an error message.
     */
    public String getResponse(String input) {
        try {
            return handleCommand(input);
        } catch (JudeException e) {
            return formatErrorMessage(e);
        }
    }

    private String handleCommand(String input) throws JudeException {
        Command command = Parser.parse(input);
        command.execute(tasks, storage);
        this.isExit = command.isExit();
        commandType = command.getType();
        return command.getMessage();
    }

    private String formatErrorMessage(JudeException e) {
        return "Error: " + e.getMessage();
    }

    public String getCommandType() {
        return commandType;
    }

    public boolean isExit() {
        return this.isExit;
    }
}


