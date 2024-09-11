package mylo.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mylo.Mylo;
/**
 * A GUI for Mylo using FXML.
 */
public class Gui extends Application implements Ui {
    private static Gui instance;

    private Mylo mylo = new Mylo("data/tasks.txt");
    private FXMLLoader fxmlLoader;

    public Gui() {
        instance = this;
    }

    @Override
    public void start(Stage stage) {
        try {
            fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            Mylo.main();
            //fxmlLoader.<MainWindow>getController().setMylo(mylo);  // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showWelcomeMessage(String welcomeMessage) {
        fxmlLoader.<MainWindow>getController().showWelcomeMessage(welcomeMessage);
    }

    public void setController(UiController c) {
        fxmlLoader.<MainWindow>getController().setController(c);
    }

    public static Gui getInstance() {
        return instance;
    }
}
