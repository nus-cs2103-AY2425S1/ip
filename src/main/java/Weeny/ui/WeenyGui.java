package weeny.ui;

import javafx.fxml.FXMLLoader;
import weeny.Weeny;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;


public class WeenyGui extends AnchorPane {

    // Other class instances
    private final Ui ui = new Ui();

    // GUI instances
    private Weeny weenyApp;
    private final Image userImage;
    private final Image weenyImage;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Button sendButton;
    @FXML
    private AnchorPane mainLayout;

//    public WeenyGui(Weeny weenyApp) {
//        this.weenyApp = weenyApp;
//        userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
//        weenyImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
//    }

    public WeenyGui() {
        this.weenyApp = new Weeny();
        userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
        weenyImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    }
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Weeny.class.getResource("/view/WeenyGui.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<WeenyGui>getController().setWeeny(this.weenyApp);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setWeeny(Weeny weeny) {
        weenyApp = weeny;
    }

    @FXML
    public void initialize() {
        dialogContainer.getChildren().add(DialogBox.getWeenyDialog(ui.showWelcomeMessage(), weenyImage));
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String weenyText = weenyApp.executeWeeny(userText);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getWeenyDialog(weenyText, weenyImage)
        );
        userInput.clear();
    }
}
