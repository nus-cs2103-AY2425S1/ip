package boss;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the class that deals with the
 * interactions with the user.
 */
public class Ui {

    /**
     * Prints an error message when
     * an exception occurs!
     */
    public void showLoadingError() {
        System.out.println("There was an error!");
    }

    /**
     * Prints an error message when
     * an exception occurs!
     */
    public void showLoadingError(Exception e) {
        System.out.println(e.getMessage());
    }

    /**
     * Welcomes the user!
     */
    public void welcome() {
        System.out.println("Hello! I'm the boss.");
        System.out.println("What can I do for you?");
    }

    /**
     * Says goodbye to the user!
     */
    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }


}
