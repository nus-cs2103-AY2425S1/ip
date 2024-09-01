import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {
    private Stage stage;
    private Luke luke;

    public Luke getLuke() {
        return luke;
    }

    public SceneManager(Stage primaryStage, Luke luke) {
        this.stage = primaryStage;
        this.luke = luke;
    }

    public void showHomeScreenScene() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/HomeScreen.fxml"));
            Parent root = fxmlLoader.load();
            fxmlLoader.<HomeScreenScene>getController().setSceneManager(this);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAddTodoTaskScene() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/AddTodoTaskScene.fxml"));
            Parent root = fxmlLoader.load();
            fxmlLoader.<AddTodoTaskScene>getController().setSceneManager(this);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAddDeadLineTaskScene() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/AddDeadLineTaskScene.fxml"));
            Parent root = fxmlLoader.load();
            fxmlLoader.<AddDeadLineTaskScene>getController().setSceneManager(this);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAddEventTaskScene() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/AddEventTaskScene.fxml"));
            Parent root = fxmlLoader.load();
            fxmlLoader.<AddEventTaskScene>getController().setSceneManager(this);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
