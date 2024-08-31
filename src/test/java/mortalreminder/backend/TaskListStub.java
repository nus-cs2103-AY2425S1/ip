package mortalreminder.backend;

import java.util.ArrayList;

import mortalreminder.tasks.Task;

public class TaskListStub extends TaskList {
    public TaskListStub() {
        super();
    }

    @Override
    public String addTask(Task task) {
        this.taskList.add(task);
        return "";
    }
}
