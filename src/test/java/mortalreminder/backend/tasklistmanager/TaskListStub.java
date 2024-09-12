package mortalreminder.backend.tasklistmanager;

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
