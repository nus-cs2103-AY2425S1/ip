package ui;

import exceptions.NotInTaskListException;
import exceptions.TestamentException;
import tasks.Task;
import tasks.TaskList;

/**
 * Prints outputs of the testament chatbot to the screen.
 */
public class Ui {

    static final String LINE = "_".repeat(60);

    /**
     * Returns welcome message.
     */
    public String welcome() {
        return "Morning!\n Nice day for a stroll, don't you think?";
    }

    /**
     * Returns bye message.
     */
    public String bye() {
        return "I'd say it's time for a tea break. Milk and sugar for you?";
    }

    /**
     * Takes in a taskList, and returns the string representing the tasklist.
     *
     * @param taskList TaskList containing tasks to turn to string.
     */
    public String schedule(TaskList taskList) {
        return "Your schedule is as follows: \n" + taskList.toString();
    }

    /**
     * Returns the mark message and details for a specified task.
     *
     * @param task Task details to print.
     */
    public String mark(Task task) {
        return "Congratulations on completing your task:\n" + task.toString();
    }

    /**
     * Returns the unmark message and details for a specified task.
     *
     * @param task Task details to return.
     */
    public String unMark(Task task) {
        return "This task has been unmarked:\n" + task.toString();
    }


    /**
     * Returns the add message and the details of the latest task in a taskList.
     *
     * @param taskList TaskList to retrieve latest task.
     */
    public String add(TaskList taskList) {
        try {
            return
                    String.format("I've added the following task to your schedule:\n%s\n"
                                    + "You have %d tasks to complete",
                            taskList.getTask(taskList.getSize()).toString(), taskList.getSize());
        } catch (NotInTaskListException e) {
            return exception(e);
        }
    }

    /**
     * Returns the delete message and details for a specified task.
     *
     * @param task Task details to return.
     */
    public String delete(Task task) {
        return "This task has been deleted:\n" + task.toString();
    }

    /**
     * Returns find message and the tasks in the given taskList
     *
     * @param taskList taskList to return
     */
    public String find(TaskList taskList) {
        if (taskList.getSize() == 0) {
            return "There are no relevant tasks";
        }
        return "Here are the relevant tasks:\n" + taskList.toString();
    }

    /**
     * Returns exception message for a specified exception.
     *
     * @param e Exception to retrieve message from.
     */
    public String exception(TestamentException e) {
        return e.getMessage();
    }

}
