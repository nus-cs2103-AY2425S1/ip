import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class HomeScreenScene {
    private SceneManager sceneManager;


    public void switchToAddTaskScene() {
        sceneManager.showAddTodoTaskScene();
    }

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    public void saveTasks() throws IOException {
        sceneManager.getLuke().saveTasks();
    }
}
