package yihuibot;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Start the application.
 *
 * @author Toh Yi Hui A0259080A
 */
public class Main extends Application {
    private YihuiBot yihuiBot = new YihuiBot();
    
    /**
     * Display the GUI for YihuiBot.
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
            fxmlLoader.<MainWindow>getController().setBot(yihuiBot);  // Injects the bot instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
