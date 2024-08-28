import java.io.File;
import java.util.ArrayList;


public class TaskList {
    private final ArrayList<Task> taskList;

    TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    TaskList(ArrayList<Task> storageData) {
        this.taskList = storageData;
    }
}


