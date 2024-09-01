import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import tasks.DeadLine;

public class AddDeadLineTaskScene {
    private SceneManager sceneManager;

    @FXML
    private TextField textDescription;

    @FXML
    private DatePicker byDate;

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

    public void addTask() {
        sceneManager.getLuke().addTask(new DeadLine(
                textDescription.getText(),
                byDate.getValue().toString()
        ));
        sceneManager.showHomeScreenScene();
    }
}
