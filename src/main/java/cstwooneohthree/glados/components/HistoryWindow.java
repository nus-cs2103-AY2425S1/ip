package cstwooneohthree.glados.components;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controller for the main GUI.
 */
public class HistoryWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;

    private Scene mainScene;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setMainScene(Scene mainScene) {
        this.mainScene = mainScene;
    }

    public VBox getDialogContainer() {
        return dialogContainer;
    }

    @FXML
    private void handleViewChat() {
        Stage stage = (Stage) dialogContainer.getScene().getWindow();
        stage.setScene(mainScene);
        stage.show();
    }

}
