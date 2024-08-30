package Ui;

import Tasks.TaskList;
import Tasks.Task;
import Exceptions.TestamentException;
import Exceptions.NotInTaskListException;

public class Ui {

    final static String LINE = "_".repeat(60);

    public void welcome() {
        printDialogue("Morning!\n Nice day for a stroll, don't you think?");
    }

    public void bye() {
        printDialogue("I'd say it's time for a tea break. Milk and sugar for you?");
    }

    public void schedule(TaskList taskList) {
        printDialogue(taskList.toString());
    }

    public void mark(Task task) {
        printDialogue("Congratulations on completing your task:\n" + task.toString());
    }

    public void unMark(Task task) {
        printDialogue("This task has been unmarked:\n" + task.toString());
    }

    public void add(TaskList taskList) {
        try {
            printDialogue(
                    String.format("I've added the following task to your schedule:\n%s\n" +
                                    "You have %d tasks to complete",
                            taskList.getTask(taskList.getSize()).toString(), taskList.getSize())
            );
        } catch (NotInTaskListException e) {
            exception(e);
        }
    }

    public void delete(Task task) {
        printDialogue("This task has been deleted:\n" + task.toString());
    }

    public void exception(TestamentException e) {
        printDialogue(e.getMessage());
    }

    private void printDialogue(String s) {
        System.out.println(LINE);
        System.out.println(s);
        System.out.println(LINE);
    }

}
