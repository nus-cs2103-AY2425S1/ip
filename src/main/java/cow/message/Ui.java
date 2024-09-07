package cow.message;

import static cow.message.Messages.ADDED_TASK_DEFAULT;
import static cow.message.Messages.ERROR_LOADING_FILE_PROCEED_WITH_EMPTY_TASK_LIST;
import static cow.message.Messages.GOODBYE_MESSAGE;
import static cow.message.Messages.GREETINGS_MESSAGE;
import static cow.message.Messages.LINE;
import static cow.message.Messages.MARKED_AS_DONE;
import static cow.message.Messages.MATCHING_TASKS_IN_LIST;
import static cow.message.Messages.TASK_DELETED;
import static cow.message.Messages.TASK_IN_LIST;
import static cow.message.Messages.UNMARK_AS_DONE;

import java.time.LocalDate;
import java.util.Scanner;

import cow.tasks.Task;
import cow.todolist.TodoList;

/** Class to handle all printing methods. **/
public class Ui {

    private final Scanner scanner = new Scanner(System.in);
    private String text = "";

    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints a string in the dialog box.
     *
     * @param text The content of the dialog box.
     */
    public void print(String text) {
        printDialogBox(text);
    }

    /**
     * Prints greeting message.
     */
    public String printGreetings() {
        String content = GREETINGS_MESSAGE;
        printDialogBox(content);
        return content;
    }

    /**
     * Prints error loading file.
     */
    public void printLoadingError() {
        printDialogBox(ERROR_LOADING_FILE_PROCEED_WITH_EMPTY_TASK_LIST);
    }

    /**
     * Prints the message after adding a task.
     *
     * @param task     The task that was added.
     * @param todoList The todoList the task was added to.
     */
    public void printAddedTask(Task task, TodoList todoList) {
        String content = ADDED_TASK_DEFAULT
                + "  "
                + task + "\n"
                + todoList.count();
        printDialogBox(content);
    }

    /**
     * Prints the message after marking a task.
     *
     * @param task The task to be marked.
     */
    public void printMarked(Task task) {
        String content = MARKED_AS_DONE + task;
        printDialogBox(content);
    }

    /**
     * Prints the message before exiting the program.
     */
    public void printGoodBye() {
        printDialogBox(GOODBYE_MESSAGE);
    }

    /**
     * Prints the deadlines due at the date specified.
     *
     * @param date The specific date to check.
     * @param todoList The list of todos.
     */
    public void printDue(LocalDate date, TodoList todoList) {
        printDialogBox(todoList.getDueAt(date).toString());
    }

    /**
     * Prints the message after unmarking a task.
     *
     * @param task The task to be unmarked.
     */
    public void printUnmarked(Task task) {
        String content = UNMARK_AS_DONE + task;
        printDialogBox(content);
    }

    /**
     * Prints the message after deleting a task.
     *
     * @param task The task that was deleted.
     * @param todoList The todoList the task was deleted from.
     */
    public void printDeletedTask(Task task, TodoList todoList) {
        String content = TASK_DELETED
                + "  "
                + task + "\n"
                + todoList.count();
        printDialogBox(content);
    }

    /**
     * Prints the content and dialog box.
     *
     * @param content The content to be printed in the dialog box.
     */
    private void printDialogBox(String content) {
        String line = LINE;
        System.out.println(line
                + "\n"
                + content + "-Mooooo"
                + "\n"
                + line
                + "\n");
        this.text = content;
    }

    /** Returns the latest command output **/
    public String getCurrentText() {
        return this.text;
    }

    /**
     * Prints todo list in a nice format.
     *
     * @param list TodoList to print.
     */
    public void printList(TodoList list) {
        String content = TASK_IN_LIST
                + list;
        printDialogBox(content);
    }

    /**
     * Prints matched todo list in a nice format.
     *
     * @param list TodoList to print.
     */
    public void printFindTask(TodoList list) {
        String content = MATCHING_TASKS_IN_LIST
                + list;
        printDialogBox(content);
    }

    /**
     * Prints the number of recurring tasks added.
     * @param times number of items added.
     * @param type either weekly or daily recurring tasks.
     */
    public void printRecurringTask(int times, String type) {
        String content = String.valueOf(times);
        if (type.equals("day")) {
            content += " daily ";
        } else if (type.equals("week")) {
            content += " weekly ";
        } else {
            content += " INCORRECT ";
        }
        content += "tasks added";
        printDialogBox(content);
    }
}
