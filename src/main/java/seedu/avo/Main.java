package seedu.avo;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import seedu.avo.commands.CommandManager;
import seedu.avo.parser.CommandParser;
import seedu.avo.storage.FileStorage;
import seedu.avo.storage.Storage;
import seedu.avo.storage.TaskParser;
import seedu.avo.tasks.Task;
import seedu.avo.tasks.TaskManager;
import seedu.avo.ui.MainWindow;
import seedu.avo.ui.ResponseFormatter;

/**
 * Represents the application
 */
public class Main extends Application {
    private final Storage<Task, String> storage = new FileStorage<Task>("data", new TaskParser());
    private final ResponseFormatter formatter = new ResponseFormatter();
    private final TaskManager taskManager = new TaskManager(storage, formatter);
    private final CommandParser parser = new CommandParser(new CommandManager(taskManager));
    private final Avo avo = new Avo(parser);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setAvo(avo);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
