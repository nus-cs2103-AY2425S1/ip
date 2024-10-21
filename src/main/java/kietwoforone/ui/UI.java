package kietwoforone.ui;

import kietwoforone.exceptions.KieTwoForOneException;
import kietwoforone.tasks.Task;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents what the chatbot displays depending on user input.
 */
public class UI {

    private static String separationLine = "_________________________________________";
    private static String chatBotName = "KieTwoForOne";
    private static Scanner scanner;
    private static String response;
    /**
     * Constructor for UI Object.
     * Initialises a scanner to read keyboard input.
     */
    public UI() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Returns the string obtained from the input keyed in by the user.
     *
     * @return User input.
     */
    public static String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints the separation line.
     */
    public static void showLine() {
        System.out.println(separationLine);
    }

    /**
     * Prints the welcome message.
     */
    public static void showWelcome() {
        showLine();
        response = "Hello! I'm " + chatBotName + "." + "\n" + "What can I do for you?";
        System.out.println(response);
        showLine();
    }

    /**
     * Prints the message displayed when a task is added to the ArrayList.
     *
     * @param tasks
     * @param newTask
     */
    public void showAddTasks(ArrayList<Task> tasks, Task newTask) {
        assert tasks != null: "Tasks cannot be null.";
        assert newTask != null: "NewTask cannot be null.";
        response = "Got it. I've added this task:\n" + "    " + newTask.toString() + "\n" +
                    String.format("Now you have %d tasks in the list.", tasks.size());
        System.out.println(response);
    }

    /**
     * Prints the message displayed when a task is deleted from the ArrayList.
     *
     * @param tasks
     * @param removedTask
     */
    public void showDeleteTask(ArrayList<Task> tasks, Task removedTask) {
        assert tasks != null: "Tasks cannot be null.";
        assert removedTask != null: "RemovedTask cannot be null.";
        response = "Noted. I've removed the task:\n" + "    " + removedTask.toString() + "\n" +
                String.format("Now you have %d tasks in the list.", tasks.size());
        System.out.println(response);
    }

    /**
     * Prints the task list.
     *
     * @param tasks
     */
    public void showTaskList(ArrayList<Task> tasks) {
        assert tasks != null: "Tasks cannot be null.";
        String currString = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            currString = currString + String.format("%d. %s\n", i + 1, tasks.get(i).toString());
        }
        response = currString;
        System.out.print(response);
    }

    /**
     * Prints the message when a task is marked.
     *
     * @param task
     */
    public void showMarkTask(String task) {
        assert task != null: "Task cannot be null.";
        response = "Nice! I've marked this task as done:\n" + "    " + task;
        System.out.println(response);
    }

    /**
     * Prints the message when a task is unmarked.
     *
     * @param task
     */
    public void showUnmarkTask(String task) {
        assert task != null: "Task cannot be null.";
        response = "OK. I've marked this task as incomplete:\n" + "    " + task;
        System.out.println(response);
    }

    public void showTaggedTask(String task) {
        assert task != null: "Task cannot be null.";
        response = "OK. I've tagged the following task:\n" + "    " + task;
        System.out.println(response);
    }

    /**
     * Prints all the tasks where the inputted date lies within.
     *
     * @param tasks
     * @param date
     * @throws KieTwoForOneException
     */
    public void showSameDate(ArrayList<Task> tasks, String date) throws KieTwoForOneException {
        assert tasks != null: "Tasks cannot be null.";
        assert date != null: "Date cannot be null.";
        ArrayList<Task> taskList = new ArrayList<>(100);
        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            if (currTask.compareDate(date)) {
                taskList.add(currTask);
            }
        }
        String currString = "Here are the tasks occurring on this date:\n";
        for (int i = 0; i < taskList.size(); i++) {
            currString = currString + String.format("%d. %s\n", i + 1, taskList.get(i).toString());
        }
        response = currString;
        System.out.print(response);
    }

    /**
     * Prints all the tasks that contains the keyword.
     *
     * @param tasks
     * @param keyword
     * @throws KieTwoForOneException
     */
    public void showMatchingTask(ArrayList<Task> tasks, String keyword) {
        assert tasks != null: "Tasks cannot be null.";
        assert keyword != null: "Keyword cannot be null.";
        ArrayList<Task> taskList= new ArrayList<>(100);
        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            if (currTask.compareString(keyword)) {
                taskList.add(currTask);
            }
        }
        String currString = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            currString = currString + String.format("%d. %s\n", i + 1, taskList.get(i).toString());
        }
        response = currString;
        System.out.print(response);
    }

    /**
     * Prints the error message when an exception is thrown.
     *
     * @param e
     */
    public void showErrorMessage(KieTwoForOneException e) {
        assert e != null: "e cannot be null.";
        response = e.getMessage();
        System.out.println(response);
    }

    /**
     * Prints the message when the user exits the chatbot.
     */
    public void showBye() {
        response = "Bye. Hope to see you again soon!";
        System.out.println(response);
    }

    /**
     * Returns the chatbot response to the user's input.
     *
     * @return
     */
    public String getResponse() {
        return response;
    }

}
