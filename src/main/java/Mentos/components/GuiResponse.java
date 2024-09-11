package Mentos.components;

import Mentos.task.Task;
import Mentos.task.TaskList;

public class GuiResponse {

    public GuiResponse() {
    }

    public String greetUser() {
        return "Hello! I'm Mentos\nWhat can I do to help you?";
    }

    public String byeUser() {
        return "Pop a Mentos, stay fresh! See you next time!";
    }

    public String displayTasks(TaskList tasks) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            String task_out = String.format("%d. %s", i + 1, tasks.get(i).toString());
            res.append(task_out).append("\n");
        }
        return res.toString();
    }

    public String markEvent(Task task) {
        StringBuilder res = new StringBuilder();
        res.append("Nicely done! This task is marked as done!\n");
        res.append(task.toString());
        return res.toString();
    }

    public String unmarkEvent(Task task) {
        StringBuilder res = new StringBuilder();
        res.append("Holdup this task is not done!\n");
        res.append(task.toString());
        return res.toString();
    }

    public String deleteEvent(Task task, int tasksSize) {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Alrights I have removed the following task!\n%s%n", task.toString()));
        res.append(String.format("%d remaining tasks%n", tasksSize));
        return res.toString();
    }

    public String printEvent(int index, Task task) {
        return String.format("%d. %s%n", index, task.toString());
    }

    public String printMatchingEvents() {
        return "Here are the matching tasks in your list!";
    }

    public String noMatchingEvents() {
        return "Sorry there are no events in your list";
    }

    public String printRemainingEvents(String event, Task task, int taskSize) {
        return String.format("%s added\n%s\n%d remaining tasks%n", event, task.toString(), taskSize);
    }

    public String invalidCommand() {
        return "Sorry! me no understand";
    }
    public String updateCommand(Task task) {
        StringBuilder res = new StringBuilder();
        res.append("Task has been updated!\n");
        res.append(task.toString());
        return res.toString();
    }
    public String updateCommandFailed() {
        return "Sorry no task has been updated!\nDeadline: /by\nEvent: /from, /to";
    }


}
