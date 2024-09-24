package bill;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Bill using FXML.
 */
public class Main extends Application {

    private Bill bill = new Bill();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Bill Bot");
            stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/DaBill.png")));
            stage.setMinHeight(630);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setBill(bill); // inject the Bill instance
            bill.loadData(); // load data from bill.txt
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
