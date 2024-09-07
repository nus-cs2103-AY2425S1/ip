package nugget;

import java.util.ArrayList;

import nugget.gui.Main;

public class Ui {
    private Main gui;

    public Ui(Main gui) {
        this.gui = gui;
    }

    public void line() {
        gui.updateOutput("________________________________________");
    }

    public void showMessage(String message) {
        gui.updateOutput(message);
    }

    public void printIntro() {
        gui.updateOutput("Hello! I'm Nugget\n"
                + "What can I do for you?\n");
    }

    public void printEnd() {
        gui.updateOutput("Bye. Hope to see you again soon!");
    }

    public void showError(String message) {
        gui.updateOutput("Error: " + message);
    }

    public void showTaskAdded(Task task, int taskCount) {
        String output = "Got it. I've added this task:\n"
                + task + "\n"
                + "Now you have " + taskCount + " tasks in the list.";
        gui.updateOutput(output);
    }

    public void showTaskRemoved(Task task, int taskCount) {
        String output = "Noted. I've removed this task:\n"
                + task + "\n"
                + "Now you have " + taskCount + " tasks in the list.";
        gui.updateOutput(output);
    }

    public void showMarkedTask(Task task) {
        String output = "Nice! I've marked this task as done:\n"
                + task;
        gui.updateOutput(output);
    }

    public void showUnmarkedTask(Task task) {
        String output = "OK, I've marked this task as not done yet:\n"
                + task;
        gui.updateOutput(output);
    }

    public void showFindResults(ArrayList<Task> matchingTasks) {
        StringBuilder output = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            output.append(i + 1).append(".").append(matchingTasks.get(i)).append("\n");
        }
        gui.updateOutput(output.toString().trim()); // Use trim() to remove any trailing newline
    }
}
