package app;

import command.Command;
import controller.MainWindow;
import fridayException.FridayException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;
import ui.UiGui;

import java.io.IOException;
import java.lang.reflect.Executable;
import java.time.format.DateTimeParseException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
     * Runs the Friday application, displaying the welcome message and reading commands from the user.
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

    public void runGui(Stage stage, Thread cliThread) {
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

    @Override
    public void start(Stage stage) {
        Thread thread = new Thread(this::run);
        thread.start();
        runGui(stage, thread);
    }

//    /**
//     * The main method that starts the Friday application.
//     *
//     * @param args Command-line arguments (not used).
//     */
//    public static void main(String[] args) {
//        new Friday("data/fridayTaskList.txt").run();
//
//    }


}