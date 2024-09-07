package noosy.stubs;

import noosy.task.Task;
import noosy.task.TaskList;
import noosy.ui.Ui;

public class UiStub extends Ui {
    @Override
    public void showTaskAdded(TaskList tasks, Task task) {
        // Simulate the behavior of showing the task without doing actual UI stuff
        System.out.println("Task added: " + task.toString());
    }
}