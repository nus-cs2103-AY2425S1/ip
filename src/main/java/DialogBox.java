import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {
    @FXML
    private Label label;
    @FXML
    private ImageView imageView;

    private DialogBox(String text, Image picture) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        this.label.setText(text);
        this.imageView.setImage(picture);
    }

    public static DialogBox getHumanDialog(String text, Image picture) {
        DialogBox temp = new DialogBox(text, picture);
        temp.styleAsUser();
        return temp;
    }

    public static DialogBox getBotDialog(String text, Image picture) {
        DialogBox temp = new DialogBox(text, picture);
        temp.styleAsBot();
        return temp;
    }

    private void styleAsUser() {
        this.setAlignment(Pos.TOP_RIGHT);
    }

    private void styleAsBot() {
        this.setAlignment(Pos.TOP_LEFT);

        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }
}
