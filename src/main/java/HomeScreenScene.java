import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomeScreenScene {
    private SceneManager sceneManager;


    public void switchToAddTaskScene() {
        sceneManager.showAddTodoTaskScene();
    }

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
