package mortalreminder.backend.tasklistmanager;

import mortalreminder.errorhandling.MortalReminderException;
import mortalreminder.tasks.Task;

public class TaskListStub extends TaskList {
    public TaskListStub() {
        super();
    }

    @Override
    public String addTask(Task task) {
        this.taskList.add(task);
        return "added task!";
    }

    public Task getTask(int id) throws MortalReminderException {
        if (this.taskList.isEmpty()) {
            throw new MortalReminderException("List is empty!");
        }
        if (id < 0 || id >= this.taskList.size()) {
            throw new MortalReminderException("Task id is invalid!");
        }
        return this.taskList.get(id);
    }
}
