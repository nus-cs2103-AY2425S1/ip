package io;

import task.TaskList;

public class Ui {

    private TaskList taskList;

    public Ui(TaskList taskList) {
        this.taskList = taskList;
    }

    public static String getStartUpMessage() {
        return """
                Hello!! My name is Blitz. Try adding some tasks now!!
                Choose between adding todo, event, and deadline classes.
                Add optional tags by adding `-t` followed by your tags!!

                Here are some sample commands!!

                todo math hw -t urgent difficult
                deadline interview prep /by 1/4/2024
                event science camp /from 10/10/2024 1300 /to 11/10/2024 1800

                find -t urgent
                """;
    }

    public static String getExitMessage() {
        return "Till we meet again, GOODBYE";
    }
}
