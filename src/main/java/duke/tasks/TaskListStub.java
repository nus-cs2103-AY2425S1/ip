package duke.tasks;
import java.util.ArrayList;
import java.util.List;

import duke.ui.Ui;

/**
 * The `TaskListStub` class is a class created for isolated testing of the parser independent of tasklist.
 */
public class TaskListStub extends TaskList {

    private List<Task> listOfTasks;

    /**
     * Constructs a `TaskListStub` object with ui.
     *
     * @param ui The Ui passed.
     */
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
