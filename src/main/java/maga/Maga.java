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
    private Ui ui;
    private TaskList taskList;
    private TaskManager taskManager;

    private void initialiseBot() {
        taskManager = new TaskManager();
        taskList = taskManager.loadTasks();
        ui = new Ui(taskList);
        ui.start();
    }

    private void closeBot() {
        ui.close();
        taskManager.saveTasks(taskList);
    }

    private void run() {
        initialiseBot();
    }

    /**
     * The entry point of the application.
     * This method creates an instance of {@code Maga}, runs it, and closes the bot upon completion.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Maga maga = new Maga();
        maga.run();
        maga.closeBot();
    }

    public String getResponse(String input) {
        return "test" + input;
    }

}
