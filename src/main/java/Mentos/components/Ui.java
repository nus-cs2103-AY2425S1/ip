package Mentos.components;

import Mentos.task.Task;
import Mentos.task.TaskList;

import java.util.Scanner;

public class Ui {
    protected final Scanner scanner;

    public Ui() {

        this.scanner = new Scanner(System.in);
    }

    public boolean hasNext(){

        return this.scanner.hasNext();
    }
    public String readCommand(){

        return this.scanner.nextLine();
    }

    /**
     * Starts the conversation with the user by displaying a greeting message.
     * This method prints a formatted message to the console, including a
     * border and a welcome message indicating that the chatbot, Mentos, is
     * ready to assist.
     */
    public void startConversation() {
        print_line();
        System.out.println("Hello! I'm Mentos\nWhat can I do to help you?");
        print_line();
    }

    /**
     * Prints a horizontal line to the console.
     * This method outputs a fixed horizontal line consisting of underscores
     * to the console, which can be used to visually separate sections of text
     * or output.
     */
    public void print_line () {
        System.out.println("____________________________");
    }

    /**
     * Ends the conversation with the user by displaying a farewell message.
     * This method prints a formatted message to the console, including a
     * border and a closing statement with a fun reference to Mentos,
     * encouraging the user to "Pop a Mentos" and stay fresh.
     */
    public void endConversation(){
        print_line();
        System.out.println("Pop a Mentos, stay fresh! See you next time!");
        print_line();
    }

    /**
     * Prints a formatted message indicating that a Mentos.task event has occurred.
     *
     * This method outputs a message to the console that includes the type of event
     * (such as "todo", "deadline", or "event"), the details of the Mentos.task that was
     * added, and the number of remaining tasks. The message is enclosed within
     * a border for visual clarity.
     *
     * @param event the type of event that occurred (e.g., "todo", "deadline", "event").
     * @param task the task object that was added or modified, whose details will be printed.
     */
    public void print_remaining_event(String event, Task task, int taskSize){
        print_line();
        System.out.printf(event+" Added\n%s\n%d remaining tasks%n",task.toString(),taskSize);
        print_line();
    }

    /**
     * Prints a message indicating that the task has been unmarked as done.
     *
     * This method displays a message to the console stating that the given task
     * has been unmarked and is not completed. It then prints the task's details
     * and a horizontal line to separate the output.
     *
     * @param task The task that has been unmarked as not done.
     */
    public void unmarkEvent(Task task){
        System.out.println("Holdup this Mentos.task is not done!");
        System.out.println(task.toString());
        print_line();
    }

    /**
     * Prints a message indicating that the task has been marked as done.
     * This method displays a congratulatory message to the console stating
     * that the given task has been marked as done. It then prints the task's
     * details and a horizontal line to separate the output.
     *
     * @param task The task that has been marked as done.
     */
    public void markEvent(Task task){
        System.out.println("Nicely done! This Mentos.task is marked as done!");
        System.out.println(task.toString());
        print_line();
    }

    /**
     * Prints a message indicating that a task has been deleted.
     *
     * This method displays a message to the console confirming that the given task
     * has been deleted. It prints the details of the deleted task and the number
     * of remaining tasks, followed by a horizontal line to separate the output.
     *
     * @param task The task that has been deleted.
     * @param tasksSize The number of tasks remaining after the deletion.
     */

    public void deleteEvent(Task task, int tasksSize){
        print_line();
        System.out.printf("Alrights I have removed the following Mentos.task!\n%s%n",task.toString());
        System.out.printf("%d remaining tasks%n",tasksSize);
    }

    /**
     * Displays the list of tasks currently stored.
     *
     * This method iterates through the list of tasks and prints each one,
     * preceded by its corresponding index in the list. The tasks are formatted
     * as a numbered list, and each task's string representation is output.
     * After displaying all tasks, a separator line is printed.
     */
    public void displayTasks(TaskList tasks){
        for (int i = 0; i < tasks.size(); i++) {
            String task_out = String.format("%d. %s",i+1,tasks.get(i).toString());
            System.out.println(task_out);
        }
        System.out.println("____________________________");
    }


    public void printMatchingEvents(){
        print_line();
        System.out.println("Here are the matching tasks in your list!");
    }

    public void noMatchingEvents(){
        print_line();
        System.out.println("Sorry there are no events in your list");
    }

    public void printEvent(int index,Task task){
        System.out.printf("%d. %s%n",index,task.toString());
    }


}

