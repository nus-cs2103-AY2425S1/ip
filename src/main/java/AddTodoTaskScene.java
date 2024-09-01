import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddTodoTaskScene {
    private SceneManager sceneManager;
    @FXML
    private TextField taskName;

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    public void back() {
        sceneManager.showHomeScreenScene();
    }

    public void switchToEvent() {
        sceneManager.showAddEventTaskScene();
    }

    public void switchToDeadLine() {
        sceneManager.showAddDeadLineTaskScene();
    }

    public void save() throws IOException {
        sceneManager.getLuke().saveTodo(taskName.getText());
    }
}
