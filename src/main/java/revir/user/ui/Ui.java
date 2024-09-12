package revir.user.ui;

import revir.tasks.TaskList;

/**
 * The Ui class represents the user interface of the application.
 * It provides methods for displaying messages, reading user input, and handling
 * errors.
 */
public interface Ui {
    /**
     * Displays a welcome message.
     */
    public void run(TaskList taskList);

    /**
     * Displays an error message.
     *
     * @param error the error message to display
     */
    public void showError(String error);

    /**
     * Displays an exit message.
     */
    public void showExit();

    /**
     * Displays the result of an operation.
     *
     * @param result the result to display
     */
    public void showResult(String result);

}
