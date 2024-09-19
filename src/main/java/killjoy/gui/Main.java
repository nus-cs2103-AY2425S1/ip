package killjoy.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import killjoy.main.KillJoy;

/**
 * A Main class that sets the stage for the GUI and launches the GUI.
 */
public class Main extends Application {
    private static final double MIN_STAGE_HEIGHT = 220;
    private static final double MIN_STAGE_WIDTH = 417;
    private final KillJoy kj = new KillJoy();
    @Override
    public void start(Stage stage) {
        setupStage(stage);
    }

    /**
     * Sets up the main stage and loads the FXML file.
     *
     * @param stage The primary stage.
     */
    private void setupStage(Stage stage) {
        try {
            assert stage != null : "Stage must not be null";
            assert kj != null : "KillJoy instance must not be null";
            FXMLLoader fxmlLoader = loadFxml();
            AnchorPane mainLayout = fxmlLoader.load();
            initializeScene(stage, mainLayout);
            fxmlLoader.<MainWindow>getController().setKj(kj);
            kj.loadTasks("KillJoy.txt");
            showStage(stage);
            showWelcomeMessage(fxmlLoader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void showWelcomeMessage(FXMLLoader fxmlLoader) {
        MainWindow mw = fxmlLoader.getController();
        mw.logo();
        mw.welcomeMessage();
    }

    /**
     * Loads the FXML file for the main window.
     *
     * @return The AnchorPane layout from the FXML.
     * @throws IOException if FXML loading fails.
     */
    private FXMLLoader loadFxml() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
        return fxmlLoader;
    }

    /**
     * Initializes the scene and sets it on the stage.
     *
     * @param stage The primary stage.
     * @param layout The layout to set in the scene.
     */
    private void initializeScene(Stage stage, AnchorPane layout) {
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.setMinHeight(MIN_STAGE_HEIGHT);
        stage.setMinWidth(MIN_STAGE_WIDTH);
    }

    /**
     * Displays the stage.
     *
     * @param stage The primary stage.
     */
    private void showStage(Stage stage) {
        stage.show();
    }
}
