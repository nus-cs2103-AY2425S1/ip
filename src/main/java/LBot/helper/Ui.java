package LBot.helper;

import LBot.task.Task;

public class Ui {
    // honestly IDK what to put here LMAOFAOAFOAFOA
    public String printTaskAddedMessage(Task task) {
        return "Added: " + task;
    }

    public String printTaskCompletedMessage(Task task) {
        return "Completed: " + task;
    }

    public String printTaskDeletedMessage(Task task) {
        return "Deleted: " + task;
    }

    public void print(String output) {
        System.out.println(output);
    }
}
