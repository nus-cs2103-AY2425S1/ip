package zaibot.utils;

import zaibot.exception.ZaibotException;
import zaibot.task.Task;

/**
 * This class is responsible for all the user interactions.
 * Encompasses print messages and reading functions.
 */
public class Ui {
    /**
     * Prints the task list.
     *
     * @param taskList The task list
     * @return The task list
     */
    public static String printTaskList(TaskList taskList) {
        return taskList.toString();
    }

    public static String printMessage(String message) {
        return Message.valueOf(message.toUpperCase()).toString();
    }

    /**
     * Prints the greeting message.
     *
     * @return The greeting message
     */
    public static String printGreeting() {
        return Message.GREETING.toString();
    }

    /**
     * Displays the number of tasks.
     *
     * @param taskList The list of tasks
     * @return The string displaying the amount of tasks.
     */
    public static String displayTasksNumber(TaskList taskList) {
        return String.format(Message.TOTAL.toString(), taskList.getNumberOfTasks());
    }

    /**
     * Displays the task, and the type of update that was done to it
     *
     * @param task     The task
     * @param type     Either "mark", "unmark", "add"
     * @param taskList The list of tasks
     * @return The task
     * @throws ZaibotException If the update task is not part of the values for task above.
     */
    public static String displayTask(Task task,
                                     String type,
                                     TaskList taskList) throws ZaibotException {
        switch (type) {
        case "mark":
        case "unmark":
        case "add":
            return task.toString()
                    + "\n" + Message.valueOf(type.toUpperCase())
                    + "\n" + displayTasksNumber(taskList);
        default:
            throw new ZaibotException("Updating task not of correct format.");
        }
    }

    private enum Message {
        GOODBYE("See you whenever."),
        MARK("Task done. Finally."),
        UNMARK("Task unmarked. Seriously?"),
        DELETE("Task removed. Bye bye."),
        GREETING("Hi, or whatever. What do you want from me today?"),
        ADD("Another day, another task. Added."),
        TOTAL("You have %d task(s). Get moving."),
        FILTER("Filtered the tasks for you. Good enough?");

        private final String msg;

        Message(String msg) {
            this.msg = msg;
        }

        @Override
        public String toString() {
            return this.msg;
        }
    }
}
