package io;

import task.TaskList;

public class Ui {

    private TaskList taskList;

    public Ui(TaskList taskList) {
        this.taskList = taskList;
    }

    public static void displayExitMessage() {
        System.out.println("----------------------\n"
                + "Till we meet again, GOODBYE");
    }
}
