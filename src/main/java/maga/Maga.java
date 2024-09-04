package maga;

import maga.task.TaskList;
import maga.task.TaskManager;

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

    public static void main(String[] args) {
        Maga maga = new Maga();
        maga.run();
        maga.closeBot();
    }

}
