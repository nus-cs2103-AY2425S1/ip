import java.io.IOException;

import chatbot.bot.Bword;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/** Main class to enable the GUI */
public class Main extends Application {
    private static final String FILELOCATION = "./data/bword.txt";
    private Bword bword = new Bword(FILELOCATION);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            stage.setMinHeight(220);
            stage.setMinWidth(417);

            fxmlLoader.<MainWindow>getController().setBword(bword); // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
