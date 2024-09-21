package scenebuilder;

import java.io.IOException;
import java.util.Collections;

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
  private Label dialog;
  @FXML
  private ImageView displayPicture;

  private DialogBox(String text, Image img) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
      fxmlLoader.setController(this);
      fxmlLoader.setRoot(this);
      fxmlLoader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }

    dialog.setText(text);
    displayPicture.setImage(img);
  }


  /**
   * Flips the dialog box such that the ImageView is on the left and text on the right.
   */
  private void flip() {
    ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
    Collections.reverse(tmp);
    getChildren().setAll(tmp);
    setAlignment(Pos.TOP_LEFT);
    dialog.getStyleClass().add("reply-label");
  }

  /**
   * Creates a DialogBox for the user with the given text and image.
   *
   * @param s The text message to be displayed in the dialog box.
   * @param i The image associated with the user in the dialog box.
   * @return A DialogBox representing the user's dialog.
   */
  public static DialogBox getUserDialog(String s, Image i) {
    return new DialogBox(s, i);
  }


  /**
   * Creates a DialogBox for Eli (the system) with the given text and image.
   * The dialog box is flipped to differentiate it from the user's dialog.
   *
   * @param s The text message to be displayed in the dialog box.
   * @param i The image associated with Eli (the system) in the dialog box.
   * @return A flipped DialogBox representing Eli's dialog.
   */
  public static DialogBox getEliDialog(String s, Image i) {
    var db = new DialogBox(s, i);
    db.flip();
    return db;
  }
}
