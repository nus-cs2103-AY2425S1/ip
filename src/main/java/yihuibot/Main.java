package yihuibot;

import java.io.IOException;
import java.nio.file.InvalidPathException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import yihuibot.component.MainWindow;
import yihuibot.exception.taskformat.IncorrectTaskFormatException;

/**
 * Start the application.
 *
 * @author Toh Yi Hui A0259080A
 */
public class Main extends Application {
    private static final String DEFAULT_FILE_PATH = "data/task.txt";
    private YihuiBot yihuiBot;

    /**
     * Display the GUI for YihuiBot.
     */
    @Override
    public void start(Stage stage) throws InvalidPathException, IOException,
            IncorrectTaskFormatException {
        try {
            String filePath = getParameters().getRaw().isEmpty()
                    ? DEFAULT_FILE_PATH
                    : getParameters().getRaw().get(0);
            yihuiBot = new YihuiBot(filePath);

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setBot(yihuiBot); // Injects the bot instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
