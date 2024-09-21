package scenebuilder;

import java.io.IOException;

import eli.Eli;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Eli using FXML.
 */
public class Main extends Application {

  private Eli eli = new Eli("data/eli.txt");

  @Override
  public void start(Stage stage) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
      AnchorPane ap = fxmlLoader.load();
      Scene scene = new Scene(ap);
      stage.setScene(scene);
      fxmlLoader.<MainWindow>getController().setEli(eli);  // inject the Eli instance
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
