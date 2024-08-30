package Ui;

import Tasks.TaskList;
import Tasks.Task;
import Exceptions.TestamentException;
import Exceptions.NotInTaskListException;

/**
 * Prints outputs of the testament chatbot to the screen.
 */
public class Ui {

    final static String LINE = "_".repeat(60);

    /**
     * Prints welcome message.
     */
    public void welcome() {
        printDialogue("Morning!\n Nice day for a stroll, don't you think?");
    }

    /**
     * Prints bye message.
     */
    public void bye() {
        printDialogue("I'd say it's time for a tea break. Milk and sugar for you?");
    }

    /**
     * Takes in a taskList, and prints the tasklist to the screen.
     *
     * @param taskList TaskList containing tasks to print.
     */
    public void schedule(TaskList taskList) {
        printDialogue(taskList.toString());
    }

    /**
     * Prints the mark message and details for a specified task.
     *
     * @param task Task details to print.
     */
    public void mark(Task task) {
        printDialogue("Congratulations on completing your task:\n" + task.toString());
    }

    /**
     * Prints the unmark message and details for a specified task.
     *
     * @param task Task details to print.
     */
    public void unMark(Task task) {
        printDialogue("This task has been unmarked:\n" + task.toString());
    }


    /**
     * Prints the add message and the details of the latest task in a taskList.
     *
     * @param taskList TaskList to retrieve latest task.
     */
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

    /**
     * Prints the delete message and details for a specified task.
     *
     * @param task Task details to print.
     */
    public void delete(Task task) {
        printDialogue("This task has been deleted:\n" + task.toString());
    }

    /**
     * Prints exception message for a specified exception.
     *
     * @param e Exception to retrieve message from.
     */
    public void exception(TestamentException e) {
        printDialogue(e.getMessage());
    }

    private void printDialogue(String s) {
        System.out.println(LINE);
        System.out.println(s);
        System.out.println(LINE);
    }

}
