package enums;

/**
 * Enums representing the different fixed status messages of BotManager.
 */
public enum StatusMessage {
    WELCOME("Hello, I'm BotManager, your friendly task assistant!\\n\"\n"
            + "What can I do for you? (Type 'help' to view all available commands)"),
    GOODBYE("Goodbye! Hope to see you again soon!"),
    FILE_LOAD_SUCCESS("Data file successfully loaded!"),
    FILE_INIT_SUCCESS("Data file successfully initialised!"),
    FILE_INIT_FAILURE("Error initialising file, BotManager will now exit!"),
    DATA_SAVE_FAILURE("Error saving task list!");


    private final String message;

    StatusMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
