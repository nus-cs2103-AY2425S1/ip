package mylo.ui;

/**
 * Represents the user interface of Mylo.
 * <p></p>
 * <p>
 * This interface defines the basic operations that a UI should support, such as displaying a welcome message.
 * It allows for flexibility in UI implementations, whether graphical or text-based.
 * </p>
 */
public interface Ui {

    /**
     * Displays a welcome message to the user.
     *
     * @param welcomeMessage The welcome message to be shown to the user.
     */
    public void showWelcomeMessage(String welcomeMessage);
}
