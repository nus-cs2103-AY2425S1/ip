package LBot.helper;

import LBot.task.Task;

public class Ui {
    // honestly IDK what to put here LMAOFAOAFOAFOA
    public void printTaskAddedMessage(Task task) {
        System.out.println("Added: " + task);
    }

    public void printTaskMarkedMessage(Task task) {
        System.out.println("Marked: " + task);
    }

    public void printTaskDeletedMessage(Task task) {
        System.out.println("Deleted: " + task);
    }

    public void printTaskList(TaskList taskList) {
        System.out.println("Here are your tasks:");
        System.out.println(taskList);
    }

    public void printGreeting() {
        System.out.println("Heyo, I'm LBot!");
        System.out.println("How can I help? :)");
    }

    public void printBye() {
        System.out.println("Bye-bi!!");
    }

    public void printDataLoadFailed() {
        System.out.println("Seems like there was an issue loading your tasks... Guess we gotta start from scratch :(");
    }

    public void printException(String output) {
        System.out.println("Oopssie: " + output);
    }
}
