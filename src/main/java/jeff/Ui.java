package jeff;

import java.util.Scanner;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import jeff.guis.DialogBox;

/**
 * Handles interactions with the user.
 *
 * The Ui class provides methods for displaying messages to the user, reading user input,
 * and other related interactions.
 */
public class Ui {
    // Reset
    public static final String RESET = "\033[0m"; // Text Reset

    // Regular Colors
    public static final String RED = "\033[0;31m"; // RED

    // Scanner
    private static final Scanner sc = new Scanner(System.in); // Scanner object to detect user input
    // UI Elements
    private static final String LINE = "--------------------------------------------";
    private static final String LOGO =
            """
                                                                        ____._________________________________|
                      _____ ___.__.   ____ _____    _____   ____       |    |\\_   _____/\\_   _____/\\_   _____/|
                     /     <   |  |  /    \\\\__  \\  /     \\_/ __ \\      |    | |    __)_  |    __)   |    __)  |
                    |  Y Y  \\___  | |   |  \\/ __ \\|  Y Y  \\  ___/  /\\__|    | |        \\ |     \\    |     \\   |
                    |__|_|  / ____| |___|  (____  /__|_|  /\\___  > \\________|/_______  / \\___  /    \\___  /   |
                          \\/\\/           \\/     \\/      \\/     \\/                    \\/      \\/         \\/    |
                    """;

    // Dialog
    private VBox dialogContainer;

    // Jeff Image
    private Image jeffImage = new Image(this.getClass().getResourceAsStream("/images/crack_yoda.jpg"));

    /**
     * Displays a welcome message to the user at the start of the application.
     */
    public void showWelcome() {
        showMessage("Hello there!");
        System.out.println(LOGO);
        showMessage("What can I do for you?");
        this.showLine();
    }

    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Adds a message to be displayed to the user
     * @param msg
     */
    public void showMessage(String msg) {
        System.out.println(msg);
        dialogContainer.getChildren().add(
                DialogBox.getJeffDialog(msg, jeffImage)
        );
    }

    public void showError(String error) {
        showMessage(RED + "ERROR!" + RESET + " " + error);
    }

    public String readCommand() {
        return sc.nextLine().trim();
    }

    public void showExit() {
        showMessage("Bye for now...");
    }

    public void setDialogContainer(VBox dCont) {
        this.dialogContainer = dCont;
    }
}
