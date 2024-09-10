package cypherchatbot.util;

import java.util.ArrayList;
import java.util.Scanner;

import cypherchatbot.Cypher;
import cypherchatbot.task.Task;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

/**
 *  The UI class handles all interactions with the user for the Cypher Chat Bot Application such as
 *  reading user commands from the console, displaying various outputs depending on the command. All user
 *  interaction in the chatbot occurs in this class.
 * */
public class Ui {

    /**
     * Initializes a new scanner instance for reading user input from the console.
     * */
    public Ui() {
    }

    /**
     * Outputs a message to the user with a custom line divider before and after the message.
     *
     * @param s The String message to be displayed to the user.
     */
    public String output(String s) {
        return s;
    }


    /**
     * Displays a greeting message to the user.
     */
    public String greet() {
        return "Hello! I am Cypher\nWhat can I do for you!";
    }

    /**
     * Displays a goodbye message to the user. The method being called indicates
     * that the application has ended and the scanner is closed.
     */
    public String goodBye() {
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished((e) -> Platform.exit());
        delay.play();
        return "Bye! See you again. This application will close in 3 seconds";
    }

    /**
     * Displays an error message if there was an issue with the specified file path.
     *
     * @param filePath The file path that could not be loaded/found/created.
     */

    public String showLoadingError(String filePath) {
        return String.format("Given filepath [%s] does not work. Please try again", filePath);
    }

    /**
     * Displays an error message to the user.
     *
     * @param e The String error message to be displayed.
     */

    public String showError(String e) {
       return e;
    }

    public String showAddMessage(Task addedTask, int totalTaskSize) {
        return String.format("Got it. I have added this task:\n  %s\nNow you have %d task in the list\n\n",
                addedTask, totalTaskSize);
    }

    public String showDeleteMessage(Task deletedTask, int totalTaskSize) {
        return "Noted! I have removed this task:\n " + deletedTask + "\n\n"
                + String.format("Now you have %d task in the list%n", totalTaskSize);
    }

    public String showFilterMessage(ArrayList<Task> filteredList) {
        if (filteredList.isEmpty()) {
            return "You have no items in your list matching the given string";
        } else {
            StringBuilder str = new StringBuilder();
            str.append("\nHere are the items in your list that match the search:\n");
            for (int i = 0; i < filteredList.size(); i++) {
                str.append((i + 1)).append(". ").append(filteredList.get(i)).append("\n");
            }
            str.append("\n");
            return str.toString();
        }
    }

    public String showHelpMessage() {
        return "<<UNDER CONSTRUCTION>>";
    }

    public String showList (ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            return "You have no items in your list:";
        } else {
            StringBuilder str = new StringBuilder();
            str.append("Here are the items in your list:\n");
            for (int i = 0; i < taskList.size(); i++) {
                str.append((i + 1)).append(". ").append(taskList.get(i)).append("\n");
            }
            str.append("\n");
            return str.toString();
        }
    }

    public String showMarkedMessage(Task markedTask) {
        return "Nice! I have marked this task as completed:\n " + markedTask;
    }

    public String showUnmarkedMessage(Task unmarkedTask) {
        return "Ok! I have marked this task as incomplete:\n " + unmarkedTask;
    }


}
