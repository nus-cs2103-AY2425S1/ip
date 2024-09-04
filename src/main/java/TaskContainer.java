import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import tasks.DeadLine;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

public class TaskContainer extends HBox {
    @FXML
    private Label taskType, fromDate, byDate, toDate, taskDescription;

    @FXML
    private CheckBox marked;

    private Task task;

    public TaskContainer(Task task) {
        this.task = task;
        if (task instanceof Todo) {
            taskType.setText("Todo");
            taskDescription.setText(task.getDescription());
        } else if (task instanceof DeadLine) {
            taskType.setText("DeadLine");
            taskDescription.setText(task.getDescription());
            byDate.setText(((DeadLine) task).getByDate().toString());
        } else if (task instanceof Event) {
            taskType.setText("Event");
            taskDescription.setText(task.getDescription());
            fromDate.setText(((Event) task).getFrom().toString());
            toDate.setText(((Event) task).getTo().toString());
        }
        marked.setSelected(task.getCompleted());
    }

    public void deleteTask() {

    }
}
