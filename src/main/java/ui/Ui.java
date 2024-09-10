package ui;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import main.DialogBox;
import main.TaskList;
import tasks.Task;

import java.io.InputStream;

/**
 * The {@code Ui} class handles interactions with the user, including
 * reading user input and displaying messages.
 */
public class Ui {
    private VBox dialogContainer;
    private final Image proYapperImage;

    /**
     * Constructs a {@code Ui} object and initializes the Scanner to read user input.
     */
    public Ui() {
        // Load ProYapper image
        InputStream proYapperStream = getClass().getResourceAsStream("/images/ProYapper.png");
        if (proYapperStream == null) {
            System.out.println("Resource not found: /images/ProYapper.png");
            this.proYapperImage = null;
        } else {
            this.proYapperImage = new Image(proYapperStream);
        }
    }

    /**
     * Displays a general message to the user.
     *
     * @param message the message to display
     */
    public void showMessage(String message) {
        DialogBox proYapperDialog = DialogBox.getProYapperDialog(message, proYapperImage);
        dialogContainer.getChildren().add(proYapperDialog);
    }

    /**
     * Displays an error message to the user.
     *
     * @param message the error message to display
     */
    public void showError(String message) {
        DialogBox errorDialog = new DialogBox(message, proYapperImage, false);
        errorDialog.setStyle("-fx-text-fill: red;");
        dialogContainer.getChildren().add(errorDialog);
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param taskList the {@code TaskList} containing tasks to be displayed
     */
    public void showTasks(TaskList taskList) {
        if (taskList.size() == 0) {
            showMessage("No tasks in your list.");
            return;
        }
        StringBuilder message = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            int lstNum = i + 1;
            Task next = taskList.get(i);
            message.append(lstNum).append(". ").append(next.toString()).append("\n");
        }
        showMessage(message.toString());
    }

    public void showFound(TaskList taskList) {
        if (taskList.size() == 0) {
            showMessage("No tasks in your list.");
            return;
        }
        StringBuilder message = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            int lstNum = i + 1;
            Task next = taskList.get(i);
            message.append(lstNum).append(". ").append(next.toString()).append("\n");
        }
        showMessage(message.toString());
    }

    /**
     * Sets the VBox container for the dialog.
     *
     * @param dialogContainer the VBox to set for displaying dialog
     */
    public void setDialogContainer(VBox dialogContainer) {
        this.dialogContainer = dialogContainer;
    }
}
