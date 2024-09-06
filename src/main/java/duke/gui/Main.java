package duke.gui;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Represents the GUI.
 */
public class Main extends Application {
    private static BlockingQueue<String> inputQueue;
    private static BlockingQueue<String> outputQueue;

    /**
     * Connect the input and output of the gui and bot.
     *
     * @param inputQueue The queue that the gui pushes user input to.
     * @param outputQueue The queue that the gui gets response message from.
     */
    public static void connect(BlockingQueue<String> inputQueue, BlockingQueue<String> outputQueue) {
        Main.inputQueue = inputQueue;
        Main.outputQueue = outputQueue;
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().connect(Main.inputQueue, Main.outputQueue);
            fxmlLoader.<MainWindow>getController().listen();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
