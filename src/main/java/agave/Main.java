package agave;

import agave.GUI.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    private Agave agave = new Agave("./data/agave.txt");

    /**
     * Starts the application.
     *
     * @param stage The stage to display the application.
     * @throws Exception If an error occurs while starting the application.
     */
    @Override
    public void start(Stage stage) throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));

            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Agave Chatbot");

            MainWindow controller = fxmlLoader.getController();
            controller.setAgave(agave);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
