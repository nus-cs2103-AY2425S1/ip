package tohru.ui;

/**
 * Represents the interface that allows the user to interact with the chatbot.
 */
public interface Ui {

    /**
     * Retrieves user input.
     *
     * @return command entered by user.
     */
    String readCommand();

    /**
     * Closes any open input.
     */
    void closeInput();

    /**
     * Prints text output from commands.
     */
    void showText(String ...text);

    /**
     * Prints errors from commands.
     */
    void showError(String ...error);

    /**
     * Prints dividers.
     */
    void showDivider();

    /**
     * Prints welcome message.
     */
    void showWelcome();

}
