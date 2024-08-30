package ui;

import task.Task;
import java.util.ArrayList;
public class UiStub extends Ui {
    public String lastOutput;

    @Override
    public void showWelcomeMessage() {
        lastOutput = "showWelcomeMessage called";
    }

    @Override
    public void showErrorMessage(String message) {
        lastOutput = "showErrorMessage: " + message;
    }

    @Override
    public void showGoodbyeMessage() {
        lastOutput = "showGoodbyeMessage called";
    }

    @Override
    public void showTaskList(ArrayList<Task> taskList) {
        lastOutput = "showTaskList called with " + taskList.size() + " tasks";
    }

    @Override
    public void showMarkTask(Task task) {
        lastOutput = "showMarkTask called for task: " + task;
    }

    @Override
    public void showUnmarkTask(Task task) {
        lastOutput = "showUnmarkTask called for task: " + task;
    }

    @Override
    public void showAddTask(Task task, int taskListSize) {
        lastOutput = "showAddTask called for task: " + task + " | Total tasks: " + taskListSize;
    }

    @Override
    public void showDeleteTask(Task task, int taskListSize) {
        lastOutput = "showDeleteTask called for task: " + task + " | Remaining tasks: " + taskListSize;
    }

    @Override
    public String readCommand() {
        // Simulate reading a command; can be expanded if needed for testing
        return "stubbed command";
    }
}
