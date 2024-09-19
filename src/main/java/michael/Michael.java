package michael;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Scanner;

import java.io.IOException;

import gui.MainWindow;

public class Michael extends Application {
    private Ui ui; // Handles user interactions
    private Storage storage; // Handles loading and saving of tasks
    private TaskList tasks; // Handles adding, retrieval and deletion of tasks
    private Parser parser; // Processes user commands
    private final String PATH = "./data/save.txt"; // File path for save file of tasks

    private static Michael michael = new Michael();
    /**
     * Initialises the chatbot.
     *
     */
    public Michael() {
        ui = new Ui();
        storage = new Storage(PATH);

        try {
            tasks = storage.load();
            parser = new Parser(tasks, storage);
        } catch (IOException e) {
            ui.showLoadingError("Can't load tasks!");
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        michael.run();
    }

    /**
     * Runs main logic of program.
     *
     */
    public void run() {
        Scanner user = new Scanner(System.in); // scanner for user input

        // Read user's input
        while (true) {
            try {
                String input = user.nextLine().strip();
                String feedback = parser.parse(input);
                ui.giveFeedback(feedback);

                if (input.equals("bye")) {
                    break;
                }
            } catch (MichaelException e) {
                ui.showLoadingError(e.getMessage());
            }
        }
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Michael.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setMichael(michael);  // inject the Michael instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Parser getParser() {
        return this.parser;
    }

    public Ui getUi() {
        return this.ui;
    }
}