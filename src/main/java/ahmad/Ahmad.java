package ahmad;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import ahmad.exceptions.AhmadException;
import ahmad.fx.Controller;
import ahmad.processor.Processor;
import ahmad.response.Response;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class for Ahmad bot.
 */
public class Ahmad extends Application {
    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 400;

    private static Controller mainController;
    private static final String WELCOME_MESSAGE = "Hello! I'm ahmad.Ahmad\nWhat can I do for you?\n\nEnjoy!";

    /**
     * Processes user prompt and calls the necessary functions to print user
     *
     * @param prompt The user's prompt.
     * @return Whether the bot should exit.
     */
    public static boolean processUserMessage(String prompt) {
        try {

            final Processor inst = Parser.parse(prompt);

            final Response response = inst.process(prompt);

            response.getResponse().forEach(message -> Ui.print(message, mainController::pushBotMessage));

            if (response.checkShouldExit()) {
                return true;
            }

            if (response.checkShouldRecord()) {
                Storage.save(prompt);
            }
        } catch (AhmadException e) {
            Ui.print(e.getMessage(), mainController::pushBotMessage);
        }
        return false;
    }

    private static void startInteraction() {
        final Scanner scanner = new Scanner(System.in);
        while (true) {
            final String prompt = scanner.nextLine();
            if (processUserMessage(prompt)) {
                break;
            }
        }
    }

    @Override
    public void start(Stage stage) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Ahmad.class.getResource("/fx/MainView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            mainController = fxmlLoader.getController();
            mainController.pushBotMessage(WELCOME_MESSAGE);
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
    public static void main(String... args) {


        if (!Arrays.asList(args).contains("--without-file")) {
            Storage.load();
        }

        launch(args);

        /*
            Ui.print(welcomeMsg);


            startInteraction();
        */
    }
}
