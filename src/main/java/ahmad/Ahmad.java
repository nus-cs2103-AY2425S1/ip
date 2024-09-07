package ahmad;

import java.io.IOException;
import java.util.Scanner;

import ahmad.exceptions.AhmadException;
import ahmad.processor.Processor;
import ahmad.response.Response;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Main class for Ahmad bot.
 */
public class Ahmad extends Application {
    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 400;

    private static void startInteraction() {
        final Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                final String prompt = scanner.nextLine();

                final Processor inst = Parser.parse(prompt);

                final Response response = inst.process(prompt);

                response.getResponse().forEach(Ui::print);

                if (response.shouldExit()) {
                    break;
                }

                if (response.checkShouldRecord()) {
                    Storage.save(prompt);
                }
            } catch (AhmadException e) {
                Ui.print(e.getMessage());
            }
        }
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Ahmad.class.getResource("/fx/MainView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("ERROR " + e.getMessage());
        }
    }

    /**
     * Runs the program.
     * Loads file (if exists), and starts interaction.
     *
     * @param args Arguments from the compiler.
     */
    public static void main(String[] args) {
        launch(args);
//        final String welcomeMsg = "Hello! I'm ahmad.Ahmad\nWhat can I do for you?\n\nEnjoy!";
//        Ui.print(welcomeMsg);
//
//        Storage.load();
//
//        startInteraction();
    }
}
