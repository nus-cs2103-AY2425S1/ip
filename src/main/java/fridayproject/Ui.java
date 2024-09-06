package fridayproject;

import java.util.ArrayList;

/*
 * Represents the user interface of the program.
 * Ui deals with interactions with the user.
 */
public class Ui {
    private static final String LINE = "_________________________________________";

    /*
    * Displays the welcome message when the program starts.
    */
    public void displayWelcome() {
        String greeting = "Hello! I'm Friday\nWhat can I do for you?\n";
        System.out.println(greeting + LINE);
    }

    /*
    * Displays the farewell message when the program ends.
    */
    public void displayFarewell() {
        String farewell = "Bye. Hope to see you again soon!";
        System.out.println(farewell + LINE);
    }

    /*
     * Displays a given message.
     * @param message The message to be displayed.
     */
    public void displayMessage(String message) {
        System.out.println(message);
        System.out.println(LINE);
    }

    /*
     * Displays loading error message.
     */
    public void displayLoadingError() {
        System.out.println("Error loading tasks from file: ");
        System.out.println(LINE);
    }

    /*
     * Displays an unknown command error message.
     */
    public void displayUnknownCommandError() {
        System.out.println("I'm sorry, but I don't know what that means :(((\n Please enter a valid task description.");
        System.out.println(LINE);
    }
    /*
     * Displays a custom error message.
     * @param message The custom error message to be displayed.
     */
    public void displayError(String message) {
        System.out.println("Error: " + message);
        System.out.println(LINE);
    }

    public void displayTasks(ArrayList<Tasks> tasks) {
        if (tasks.size() == 0) {
            System.out.println("You have no tasks in the list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i).getTypeIcon() 
                + tasks.get(i).toString());
            }
        }
        System.out.println(LINE);
    }
}
