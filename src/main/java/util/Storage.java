package util;

import task.TaskList;

public class Storage {
    public TaskList loadTaskList() {
        return new TaskList();
    }

    public void saveTaskList(TaskList taskList) {}
}
