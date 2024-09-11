package evelyn.command.ui.gui;

import java.io.IOException;

import evelyn.Evelyn;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Contains all the logic for the GUI for Evelyn.
 */
public class Gui extends Application {
    private static final String DEFAULT_FILE_PATH = "src/main/data/evelyn.txt";
    private String filePath;
    private Scene scene;
    private Evelyn evelyn = new Evelyn();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Constructor for the GUI.
     * @param filePath the designated filepath for the data and stored information.
     */
    public Gui(String filePath) {
        this.filePath = filePath;
    }

    public Gui() {
        this(DEFAULT_FILE_PATH);
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            MainWindow controller = fxmlLoader.<MainWindow>getController();
            controller.setEvelyn(evelyn); // inject the Duke instance
            stage.show();

            controller.addMessage(
                    "Hi! Welcome to Evelyn. I am here to help! \n\n"

                    + "Here are some of my commands: \n"
                    + "todo [task description]\n"
                    + "deadline [task description] /by [date in YYYY-MM-DD] [Optional: time]\n"
                    + "event [task description] /from [date in YYYY-MM-DD] [Optional: time]"
                    + "/to [date in YYYY-MM-DD] [Optional: time]\n"
                    + "list (Lists out all the current tasks)\n"
                    + "mark [index] (Marks the tasks at the specified index)\n"
                    + "unmark [index] (Unmarks the tasks at the specified index)\n"
                    + "delete [index] (Deletes the tasks at the specified index)\n"
                    + "find [keyword] (Unmarks the tasks at the specified index)\n"
                    + "bye (Exits Evelyn)\n\n"

                    + "For more advanced users, you can use the following shortcuts:\n"
                    + "t [Todo task description]\n"
                    + "d [Deadline task description] /by [date in YYYY-MM-DD] [Optional: time]\n"
                    + "e [Event task description] /from [date in YYYY-MM-DD] [Optional: time]"
                            + "/to [date in YYYY-MM-DD] [Optional: time]\n"
                    + "m [index to be Marked]\n"
                    + "um [index to be Unmarked]\n"
                    + "del [index to be Deleted]\n"
            );
        } catch (IOException e) {
            System.err.println("Error starting application: " + e);
        }
    }

}
