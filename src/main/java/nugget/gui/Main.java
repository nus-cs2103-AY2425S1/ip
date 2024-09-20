package nugget.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import nugget.Nugget;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ChatUi.fxml"));
        VBox root = loader.load();

        ChatUiController controller = loader.getController();
        Nugget nugget = new Nugget("data/nugget.txt", controller);
        controller.setNugget(nugget);

        Scene scene = new Scene(root, 400, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Nugget Task Tracker");
        primaryStage.show();

        nugget.start();
    }
}
