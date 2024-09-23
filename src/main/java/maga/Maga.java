package maga;

import maga.task.TaskList;
import maga.task.TaskManager;

/**
* The {@code Maga} class represents the main application for a task management bot.
* It handles the initialization, execution, and shutdown processes of the bot.
* This class manages the lifecycle of key components such as the {@code TaskManager},
* {@code TaskList}, and {@code Ui}.
*/
public class Maga {
    private Controller controller;
    private TaskList taskList;
    private TaskManager taskManager;

    public Maga() {
        initialiseBot();
    }

    private void initialiseBot() {
        taskManager = new TaskManager();
        taskList = taskManager.loadTasks();
        controller = new Controller(taskList, taskManager);
    }

    public void closeBot() {
        taskManager.saveTasks(taskList);
    }

    public String getResponse(String input) {
        return controller.handleInput(input);
    }

}
