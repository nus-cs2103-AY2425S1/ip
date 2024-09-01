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
     * Constructor for a new Main, with parameter to specify where to read tasks info from.
     *
     * @param filePath the file path to read tasks info from, and store them.
     * @throws InvalidPathException when filePath cannot be converted to a Path.
     * @throws IOException when an I/O Error occurred.
     * @throws IncorrectTaskFormatExceptionn when data in filePath is corrupted.
     */
    public Main(String filePath) throws InvalidPathException, IOException,
            IncorrectTaskFormatException {
        yihuiBot = new YihuiBot(filePath);
    }

    /**
     * Constructor for a new Main, when no file path is provided.
     *
     * @throws InvalidPathException when filePath cannot be converted to a Path.
     * @throws IOException when an I/O Error occurred.
     * @throws IncorrectTaskFormatExceptionn when data in filePath is corrupted.
     */
    public Main() throws InvalidPathException, IOException,
            IncorrectTaskFormatException {
        this(DEFAULT_FILE_PATH);
    }

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
            fxmlLoader.<MainWindow>getController().setBot(yihuiBot); // Injects the bot instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
