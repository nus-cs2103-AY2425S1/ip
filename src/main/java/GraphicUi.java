import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Java FX Graphical User Interface for ChatterBox.
 */
public class GraphicUi extends Application {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Scene scene;
    private final Chatterbox chatterbox = new Chatterbox("/data/chatterbox_save.txt");

    private final Image userImage = new Image((this.getClass().getResourceAsStream("/images/DaUser.png")));
    private final Image chatterboxImage = new Image((this.getClass().getResourceAsStream("/images/DaChatterbox.png")));

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GraphicUi.class.getResource("/view/MainWindow.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setChatterbox(chatterbox);
            stage.setTitle("Chatterbox");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
