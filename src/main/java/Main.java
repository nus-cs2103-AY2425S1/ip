//package Bob;
//
//import java.io.IOException;
//
//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.scene.layout.AnchorPane;
//import javafx.stage.Stage;
//
///**
// * A GUI for Bob using FXML
// */
//
//public class Main extends Application {
//    private Bob bob = new Bob();
//
//    @Override
//    public void start(Stage stage) {
//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
//            AnchorPane ap = fxmlLoader.load();
//            Scene scene = new Scene(ap);
//            stage.setScene(scene);
//            fxmlLoader.<MainWindow>getController().setBob(bob);  // inject the Duke instance
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}


import bob.Bob;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private final Bob bob = new Bob();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBob(bob); // inject the Bob instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
