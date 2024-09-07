package nugget.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import nugget.Nugget;

public class Main extends Application {

    private Nugget nugget;
    private ChatUI chatUI;

    @Override
    public void start(Stage primaryStage) throws Exception {
        nugget = new Nugget("data/nugget.txt", this);

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

    public static void main(String[] args) {
        launch(args);
    }
}
