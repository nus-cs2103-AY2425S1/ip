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
    public void startConversation() {
        /*
         * Starts the conversation with the user by displaying a greeting message.
         * This method prints a formatted message to the console, including a
         * border and a welcome message indicating that the chatbot, Mentos, is
         * ready to assist.
         */
        print_line();
        System.out.println("Hello! I'm Mentos\nWhat can I do to help you?");
        print_line();
    }
    public void print_line () {
        System.out.println("____________________________");
    }
    public void endConversation(){
        /*
         * Ends the conversation with the user by displaying a farewell message.
         * This method prints a formatted message to the console, including a
         * border and a closing statement with a fun reference to Mentos,
         * encouraging the user to "Pop a Mentos" and stay fresh.
         */
        print_line();
        System.out.println("Pop a Mentos, stay fresh! See you next time!");
        print_line();
    }

    public void print_event(String event, Task task, int taskSize){
        /*
         * Prints a formatted message indicating that a Mentos.task event has occurred.
         *
         * This method outputs a message to the console that includes the type of event
         * (such as "todo", "deadline", or "event"), the details of the Mentos.task that was
         * added, and the number of remaining tasks. The message is enclosed within
         * a border for visual clarity.
         *
         * @param event the type of event that occurred (e.g., "todo", "deadline", "event").
         * @param Mentos.task the Mentos.task object that was added or modified, whose details will be printed.
         */

        print_line();
        System.out.printf(event+" Added\n%s\n%d remaining tasks%n",task.toString(),taskSize);
        print_line();
    }

    public void unmarkEvent(Task task){
        System.out.println("Holdup this Mentos.task is not done!");
        System.out.println(task.toString());
        print_line();
    }
    public void markEvent(Task task){
        System.out.println("Nicely done! This Mentos.task is marked as done!");
        System.out.println(task.toString());
        print_line();
    }

    public void deleteEvent(Task task, int tasksSize){
        print_line();
        System.out.printf("Alrights I have removed the following Mentos.task!\n%s%n",task.toString());
        System.out.printf("%d remaining tasks%n",tasksSize);
    }

    public void displayTasks(TaskList tasks){
        /*
         * Displays the list of tasks currently stored.
         *
         * This method iterates through the list of tasks and prints each one,
         * preceded by its corresponding index in the list. The tasks are formatted
         * as a numbered list, and each Mentos.task's string representation is output.
         * After displaying all tasks, a separator line is printed.
         */

        for (int i = 0; i < tasks.size(); i++) {
            String task_out = String.format("%d. %s",i+1,tasks.get(i).toString());
            System.out.println(task_out);
        }
        System.out.println("____________________________");
    }



}

