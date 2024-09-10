package yapbot.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    private YapBot yapBot = new YapBot("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            stage.setTitle("YapBot");
            stage.getIcons().add(new Image("/images/robot.png"));

            fxmlLoader.<MainWindow>getController().setYapBot(yapBot); // inject the Duke instance
            stage.show();
            fxmlLoader.<MainWindow>getController().printYapBotDialog(yapBot.run());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        yapBot.close();
    }

}
