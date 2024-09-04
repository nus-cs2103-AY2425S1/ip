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
     * Prints text messages from commands.
     *
     * @param texts Messages to be displayed.
     */
    void showText(String ...texts);

    /**
     * Prints error messages from commands.
     *
     * @param errors Error messages to be displayed.
     */
    void showError(String ...errors);

    /**
     * Prints dividers.
     */
    void showDivider();

    /**
     * Prints welcome message.
     */
    void showWelcome();

}
