import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import tasks.Todo;

import java.io.IOException;

public class AddTodoTaskScene {
    private SceneManager sceneManager;
    @FXML
    private TextField taskDescription;

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

    public void addTask() throws IOException {
        sceneManager.getLuke().addTask(new Todo(taskDescription.getText()));
        sceneManager.showHomeScreenScene();
    }
}
