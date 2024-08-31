package mortalreminder.backend;

import mortalreminder.tasks.Task;

public class TaskListStub extends TaskList {
    public TaskListStub() {
        super();
    }

    @Override
    public void addTask(Task task) {
        this.taskList.add(task);
    }
}
