package mortal_reminder.backend;

import mortal_reminder.tasks.Task;

public class TaskListStub extends TaskList {
    public TaskListStub() {
        super();
    }

    @Override
    public void addTask(Task task) {
        this.taskList.add(task);
    }
}
