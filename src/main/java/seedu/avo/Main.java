package seedu.avo;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import seedu.avo.commands.CommandManager;
import seedu.avo.parser.CommandParser;
import seedu.avo.storage.FileStorage;
import seedu.avo.storage.Storage;
import seedu.avo.storage.TaskParser;
import seedu.avo.tasks.Task;
import seedu.avo.tasks.TaskManager;
import seedu.avo.ui.AppUI;

public class Main extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image avoImage = new Image(this.getClass().getResourceAsStream("/images/DaAvo.png"));
    private Storage<Task, String> storage = new FileStorage<Task>("data", new TaskParser());
    private AppUI ui = new AppUI();
    private TaskManager taskManager = new TaskManager(storage, ui);
    private CommandParser parser = new CommandParser(new CommandManager(taskManager));
    private Avo avo = new Avo(parser);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setDuke(avo);  // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
