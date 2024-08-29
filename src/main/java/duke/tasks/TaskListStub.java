package duke.tasks;

import duke.data.TaskDataBase;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.List;

public class TaskListStub extends TaskList {

    List<Task> listOfTasks;
    public TaskListStub(Ui ui) {
        super(ui);
        this.listOfTasks = new ArrayList<>();
    }

    @Override
    public void addTask(Task task) {
        listOfTasks.add(task);
    }

    public int getSize() {
        return listOfTasks.size();
    }

    public String getTask(int index) {
        return listOfTasks.get(index).toString();
    }

}
