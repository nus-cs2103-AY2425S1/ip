package revir.user.ui.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import revir.tasks.TaskList;
import revir.user.Parser;
import revir.user.command.Command;
import revir.user.ui.Ui;
import revir.user.ui.gui.components.MainWindow;

/**
 * Represents the GUI instance of the Revir program.
 */
public class Gui extends Application implements Ui {
    private static MainWindow window;
    private static TaskList taskList;

    /**
     * Initializes a new GUI instance.
     *
     */
    public Gui() {
    }

    /**
     * Runs the GUI application.
     */
    @Override
    public void run(TaskList taskList) {
        Gui.taskList = taskList;
        launch();
    }

    /**
     * Displays an error message on the GUI.
     *
     * @param error the error message to be displayed
     */
    @Override
    public void showError(String error) {
        this.sendMessage(error);
    }

    /**
     * Displays an exit message on the GUI.
     */
    @Override
    public void showExit() {
        this.sendMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Displays the result of a command on the GUI.
     *
     * @param result the result of the command
     */
    @Override
    public void showResult(String result) {
        this.sendMessage(result);
    }

    /**
     * Sends a message to the GUI.
     *
     * @param message the message to be sent
     */
    private void sendMessage(String message) {
        window.sendBotMessage(message);
    }

    /**
     * Handles user input from the GUI.
     *
     * @param input the user input
     */
    public void handleUserInput(String input) {
        Parser parser = new Parser();

        try {
            Command c = parser.parse(input);
            c.execute(this, Gui.taskList);
        } catch (NumberFormatException e) {
            this.showError("Invalid task index. Expected a number.");
        } catch (IOException e) {
            this.showError("Unable to save file: " + e.getMessage());
        } catch (Exception e) {
            this.showError(e.getMessage());
        }
    }

    /**
     * Starts the GUI application.
     *
     * @param stage the stage to be displayed
     * @throws Exception if an error occurs during the start of the GUI
     */
    @Override
    public void start(Stage stage) throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/views/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            Gui.window = fxmlLoader.<MainWindow>getController();
            Gui.window.setGuiHandler(this);
            stage.show();
            this.sendMessage("Hello! I'm Revir\nWhat can I do for you?");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
