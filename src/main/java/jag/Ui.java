package jag;

import java.util.Scanner;

/**
 * Represents an instance of User Interface interactions
 * Purpose is to read messages / commands, respond appropriately,
 * and return values to be used for other Command instances e.g. "Event"
 */
public abstract class Ui {

    public abstract void showLoadingError();

    public abstract void setCommand(String command);
    public abstract void setResponse(String response);

    public abstract String getResponse();

    /**
     * Prints out the error message in the case of an exception caught
     *
     * @param e String representation of the error message
     */
    public abstract void showError(String e);

    public abstract void showLine();

    /**
     * Prints out a greeting message for the user
     */
    public abstract void showWelcome();

    public abstract String readCommand();


    /**
     * Returns the description of the task from user input
     *
     * @param type Type represents T, D, E, F, which helps to indicate
     *             the type of Task to be processed.
     * @return description of the task from the user input on a case
     *          by case basis, of T, D, E, F
     */
    public abstract String getDescription(char type);

    /**
     * Returns a String representation of the deadline from user input
     * for a deadline task
     *
     * @return a String of the deadline set by the user for a deadline task
     */
    public abstract String getBy();
    /**
     * Returns a String representation of the starting date from user input
     * for an event
     *
     * @return a String of the starting date set by the user for an event
     */
    public abstract String getFrom();

    /**
     * Returns a String representation of the end date from user input
     * for an event
     *
     * @return a String of the end date set by the user for an event
     */
    public abstract String getTo();

    /**
     * Returns an Integer representing the index of the task to be deleted
     * in the stored TaskList
     *
     * @return an Integer representing the index of the task to be deleted
     *          in the stored TaskList
     */
    public abstract int getDeleteIndex();

    /**
     * Returns an Integer representing the index of the task to be marked
     *  or unmarked in the stored TaskList
     *
     * @return an Integer representing the index of the task to be marked
     *          or unmarked in the stored TaskList
     */
    public abstract int getMark();

    /**
     * Responds by printing out the String response
     *
     * @param response Response that consist of all Tasks
     *                 that have been turned into Strings
     *                 from the List class
     */
    public abstract void list(String response);


    /**
     * Displays the right response to the user that a task has been added
     * based on the type of task
     *
     * @param type Type of task so that the right type of response can be displayed
     * @param task Task instance that has been created, so that it's toString() method
     *             can be called upon
     * @param tasks The Instance of the TaskList so that the size of the list can be
     *              accessed and displayed to the user upon the addition
     */
    public abstract void addedResponse(char type, Task task, TaskList tasks);

    /**
     * Displays the right response to the user that a task has been deleted
     *
     * @param task Task instance that has been created, so that it's toString() method
     *             can be called upon
     * @param size Represents the updated size of the task list after the deletion
     */
    public abstract void deleteResponse(Task task, int size);

    /**
     * Displays the right response to the user that a task has been unmarked
     *
     * @param task Task instance that has been created, so that it's toString() method
     *             can be called upon
     */
    public abstract void unmarkResponse(Task task);

    /**
     * Displays the right response to the user that a task has been marked
     *
     * @param task Task instance that has been created, so that it's toString() method
     *             can be called upon
     */
    public abstract void markResponse(Task task);

    /**
     * Index of the task in the task list for the update command
     * @return integer of the stored task index in task list
     */
    public abstract int getUpdateIndex();


    /**
     * Display list of tasks that matches the description of the find
     * command in the saved list, if size of the list is 0, that means
     * there are no found tasks and a "sorry" message will be displayed
     *
     * @param foundTasks Represents an instance of a TaskList that
     *                   consists of all the found tasks that was
     *                   found by the FindCommand instance
     */
    public abstract void findResponse(TaskList foundTasks);

    /**
     * Displays the updated task
     * @param task Task instance that has been updated
     */
    public abstract void updateResponse(Task task);

    /**
     * Displays the response for exiting the chatbot
     * and to close the instance of the scanner to end the application
     */
    public abstract void exitResponse();









}
