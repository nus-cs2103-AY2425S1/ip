package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import talker.Talker;

/**
 * Represents implementation of Application
 */
public class Main extends Application {
    private Talker talker = new Talker();

    @Override
    public void start(Stage stage) {
        MainWindow mw = new MainWindow();
        Scene scene = new Scene(mw);

        stage.setScene(scene);
        stage.setMinHeight(220);
        stage.setMinWidth(417);

        // inject talker instance
        mw.setTalker(talker);
        stage.show();
    }
}
