import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import tasks.Event;

public class AddEventTaskScene {
    private SceneManager sceneManager;
    @FXML
    private TextField taskDescription;

    @FXML
    private DatePicker fromDate;

    @FXML
    private DatePicker toDate;


    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    public void back() {
        sceneManager.showHomeScreenScene();
    }

    public void switchToTodo() {
        sceneManager.showAddTodoTaskScene();
    }

    public void switchToDeadLine() {
        sceneManager.showAddDeadLineTaskScene();
    }

    public void addTask() {
        sceneManager.getLuke().addTask(new Event(
                taskDescription.getText(),
                fromDate.getValue().toString(),
                toDate.getValue().toString()
        ));
        sceneManager.showHomeScreenScene();
    }
}
