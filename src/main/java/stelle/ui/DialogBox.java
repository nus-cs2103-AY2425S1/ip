package stelle.ui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * This class represents a Dialog box JavaFX UI control, to be used along with MainWindow.
 * @author Lee Ze Hao (@A0276123J)
 */
public class DialogBox extends HBox {

    @FXML
    private HBox panel;
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private static final Background userBackground = new Background(
            new BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY)
    );
    private static final Background stelleBackground = new Background(
            new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)
    );

    private static final Font userFont = Font.font("Consolas", FontWeight.BOLD, 14);
    private static final Font stelleFont = Font.font("Consolas", 14);

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        panel.setBackground(userBackground);
        dialog.setText(text);
        dialog.setFont(userFont);
        displayPicture.setImage(img);
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);

        this.panel.setBackground(stelleBackground);
        this.dialog.setFont(stelleFont);
    }

    /**
     * Creates a dialog box control for the user.
     * @param s String of the dialog box text.
     * @param i Image to put aside the text in dialog box.
     * @return
     */
    public static DialogBox getUserDialog(String s, Image i) {
        return new DialogBox(s, i);
    }

    /**
     * Creates a dialog box control for the chatbot.
     * @param s String of the dialog box text.
     * @param i Image to put aside the text in dialog box.
     * @return
     */
    public static DialogBox getStelleDialog(String s, Image i) {
        var db = new DialogBox(s, i);
        db.flip();
        return db;
    }
}

