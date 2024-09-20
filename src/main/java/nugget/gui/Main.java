package nugget.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import nugget.Nugget;

public class Main extends Application {

    private Nugget nugget;
    private ChatUI chatUI;

    private static final String DEFAULT_FILE_PATH = "data/nugget.txt";

    @Override
    public void start(Stage primaryStage) throws Exception {
        nugget = new Nugget(DEFAULT_FILE_PATH, this);

        chatUI = new ChatUI(nugget);

        VBox layout = chatUI.getLayout();

        Scene scene = new Scene(layout, 400, 600);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Nugget Task Tracker");
        primaryStage.show();

        nugget.start();
    }

    /**
     * Updates the output on the Chat UI by adding a message from the bot.
     *
     * @param message The message to be displayed.
     */
    public void updateOutput(String message) {
        chatUI.addMessage(message, false);
    }
}
