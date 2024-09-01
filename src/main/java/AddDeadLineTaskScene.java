import javafx.fxml.FXMLLoader;

public class AddDeadLineTaskScene {
    private SceneManager sceneManager;

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    public void back() {
        sceneManager.showHomeScreenScene();
    }

    public void switchToTodo() {
        sceneManager.showAddTodoTaskScene();
    }

    public void switchToEvent() {
        sceneManager.showAddEventTaskScene();
    }

    public void save() {
        //TODO
    }
}
