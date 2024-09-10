package app;

import command.Command;
import controller.MainWindow;
import fridayException.FridayException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;
import ui.UiGui;

import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Represents the Friday application that manages tasks for the user.
 */
public class Friday extends Application {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private final UiGui gui;

    /**
     * Constructs a Friday application with the specified file path for the task list.
     */
    public Friday() {
        String filePath = "data/fridayTaskList.txt";
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        this.gui = new UiGui();
        try {
            this.tasks = new TaskList(storage.load());
        } catch (IOException e) {
            this.ui.showError(e.getMessage());
            this.tasks = new TaskList();
        }
//        this.run();
    }

    /**
     * Runs the CLI for the Friday application,
     * displaying the welcome message and reading commands from the user.
     * The application continues to run until the user enters the "bye" command.
     */
    public void run() {
        ui.showWelcome();
        boolean isEndScanner = false;
        while (!isEndScanner) {
            try {
                String commandRaw = ui.readCommand();
                Command command = Parser.parse(commandRaw);
                command.execute(tasks, ui, storage);
                isEndScanner = command.isEndScanner();
            } catch (FridayException | DateTimeParseException e ) {
                if (e instanceof DateTimeParseException) {
                    ui.showError("Please enter a valid date in the format yyyy-mm-dd.");
                } else {
                    ui.showError(e.getMessage());
                }
            }
        }
        ui.closeScanner();
        Platform.exit();
    }

    /**
     * Runs the Graphical User Interface for the Friday application.
     *
     * @param stage The stage to display the GUI.
     */
    public void runGui(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Friday.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setStorage(storage).setTasks(tasks).setGui(gui);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts the Friday application.
     *
     * @param stage The stage to display the GUI.
     */
    @Override
    public void start(Stage stage) {
        Thread thread = new Thread(this::run);
        thread.start();
        runGui(stage);
    }
}