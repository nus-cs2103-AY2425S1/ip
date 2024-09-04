import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class HomeScreenScene {
    private SceneManager sceneManager;
    @FXML
    private VBox taskList;

    public void listTasks() {
        List<TaskContainer> taskContainerList = sceneManager.getLuke()
                .getTasks()
                .stream()
                .map(TaskContainer::new)
                .toList();
        taskList.getChildren().addAll(taskContainerList);
    }

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
