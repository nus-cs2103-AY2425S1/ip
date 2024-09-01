//package GUI;
//
//import java.io.IOException;
//
//import CommandLine.NotAGPT;
//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.scene.layout.AnchorPane;
//import javafx.stage.Stage;
//
///**
// * A GUI for NotAGPT using FXML.
// */
//public class Main extends Application {
//
//    private NotAGPT notAGPT = new NotAGPT();
//
//    @Override
//    public void start(Stage stage) {
//        try {
//            System.out.println("FXML file is " + getClass().getResource("/view/MainWindow.fxml"));
//            if (getClass().getResource("/view/MainWindow.fxml") == null) {
//                System.out.println("Null");
//                return;
//            }
//            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("resources/view/MainWindow.fxml"));
//            AnchorPane ap = fxmlLoader.load();
//            Scene scene = new Scene(ap);
//            stage.setScene(scene);
//            stage.setMinHeight(220);
//            stage.setMinWidth(417);
//            fxmlLoader.<MainWindow>getController().setDuke(notAGPT);  // inject the Duke instance
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}

package GUI;

import java.io.IOException;
import CommandLine.NotAGPT;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for NotAGPT using FXML.
 */
public class Main extends Application {

    private NotAGPT notAGPT = new NotAGPT();

    @Override
    public void start(Stage stage) {
        try {
            String fxmlPath = "/view/MainWindow.fxml";
            System.out.println("Loading FXML file from: " + getClass().getResource(fxmlPath));

            if (getClass().getResource(fxmlPath) == null) {
                System.out.println("FXML file not found: " + fxmlPath);
                return;
            }

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setDuke(notAGPT);  // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
