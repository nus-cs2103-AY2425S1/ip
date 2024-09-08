package miku.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import miku.utility.Response;

/**
 * The page where the chat takes place.
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/DialogPane.fxml"));
        AnchorPane mainPage = fxmlLoader.load();
        DialogPane dialogPane = fxmlLoader.getController();
        dialogPane.add(DialogBox.getDukeDialog(Response.greet(),
                new Image(getClass().getResourceAsStream("/Miku.jpeg"))));
        Scene scene = new Scene(mainPage);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
