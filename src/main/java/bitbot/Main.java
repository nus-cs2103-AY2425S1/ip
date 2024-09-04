package bitbot;

//@@author SwaminathanViswa -reused
//Reused from https://se-education.org/guides/tutorials/javaFxPart4.html
// with minor modifications
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Main extends Application {

    private BitBot bitbot;

    /**
     * Constructor for Main()
     *
     * @throws FileNotFoundException if the file is not found
     */
    public Main() throws FileNotFoundException {
        bitbot = new BitBot();
    }

    /**
     * Serves as the starting point to the application
     *
     * @param stage
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setBitbot(bitbot);  // inject the BitBot instance
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//@@author
