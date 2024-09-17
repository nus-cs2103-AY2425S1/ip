package ui;

import java.util.List;
import task.Todo;
import task.Event;
import task.Deadline;
import task.Task;

public class Ui {
    private static final String greetMessage = "Hello! I'm Daniel\nWhat can I do for you?\n";
    private static final String byeMessage = "Bye hope to see you again soon";
    private static final String listMessage = "Here are the tasks in your list\n";
    private static final String addTaskMessage = "Got it. I've added this task:\n";
    private static final String fillerMessageA = "Now you have ";
    private static final String fillerMessageB = " task in the list";
    private static final String markDoneMessage = "Nice I have marked this task as done:\n";
    private static final String markUndoneMessage = "OK, I've marked this task as not done yet:\n";
    private static final String removeMessage = "Noted. I've removed this task:\n";
    /**
     * Returns a greeting message.
     *
     * @return A string with the greeting message.
     */
    public String uiGreet() {
        return greetMessage;

    }
    /**
     * Returns a goodbye message.
     *
     * @return A string with the goodbye message.
     */
    public String uiBye() {
        return byeMessage;
    }
    /**
     * Returns a formatted string representing the list of tasks.
     *
     * @param array The list of tasks to be displayed.
     * @return A formatted string with all tasks in the list.
     */
    public static String uiList(List<Task> array) {
        String s = listMessage;
        int i = 1;
        for ( Task element : array) {
            s = s + i +"." + element.toString() + "\n";
            i += 1;
        }
        return s;
    }
    /**
     * Returns a formatted string for adding a Todo task.
     *
     * @param size The current size of the task list.
     * @param x The Todo task that was added.
     * @return A formatted string confirming the addition of the Todo task.
     */
    public static String uiTodo(int size, Todo x) {
        return addTaskMessage + x.toString() + "\n"
                + fillerMessageA + size + fillerMessageB;
    }
    /**
     * Returns a formatted string for adding a Deadline task.
     *
     * @param size The current size of the task list.
     * @param x The Deadline task that was added.
     * @return A formatted string confirming the addition of the Deadline task.
     */
    public static String uiDeadline(int size, Deadline x) {
        return addTaskMessage + x.toString()
                + "\n" + fillerMessageA + size + fillerMessageB;
    }
    /**
     * Returns a formatted string for adding an Event task.
     *
     * @param size The current size of the task list.
     * @param x The Event task that was added.
     * @return A formatted string confirming the addition of the Event task.
     */
    public static String uiEvent(int size, Event x) {
        return addTaskMessage + x.toString() + "\n"
                + fillerMessageA + size + fillerMessageB;
    }
    /**
     * Returns a formatted string for marking a task as done.
     *
     * @param t The task that was marked as done.
     * @return A formatted string confirming the task has been marked as done.
     */
    public static String uiMark(Task t) {
        return markDoneMessage + t.toString();
    }
    /**
     * Returns a formatted string for marking a task as not done.
     *
     * @param t The task that was marked as not done.
     * @return A formatted string confirming the task has been marked as not done.
     */
    public static String uiUnMark(Task t) {
        return markUndoneMessage + t.toString();
    }
    /**
     * Returns a formatted string for deleting a task.
     *
     * @param t The task that was deleted.
     * @param size The current size of the task list after deletion.
     * @return A formatted string confirming the deletion of the task.
     */
    public static String uiDelete(Task t, int size) {
        return removeMessage + t.toString() + "\n" + fillerMessageA + size + fillerMessageB;
    }
}
