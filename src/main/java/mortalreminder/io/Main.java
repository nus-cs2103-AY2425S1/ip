package mortalreminder.io;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mortalreminder.MortalReminder;

/**
 * A GUI for Mortal Reminder using FXML.
 */
public class Main extends Application {

    private MortalReminder mortalReminder = new MortalReminder();

    // this method was slightly modified using chatGPT
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MortalReminderWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Mortal Reminder");
            stage.setMinHeight(220);
            stage.setMinWidth(417);

            MortalReminderWindow controller = fxmlLoader.<MortalReminderWindow>getController();
            controller.setMortalReminder(mortalReminder);
            controller.onStartUp();
            // divided for better readability.
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
