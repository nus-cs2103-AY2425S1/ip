package boss;

/**
 * Represents the class that deals with the
 * interactions with the user.
 */
public class Ui {
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
        System.out.println("WHATS GOOD, MY HOMIE! I'm the boss!");
        System.out.println("What can I do for you today?");
    }


    /**
     * Says goodbye to the user!
     */
    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }


}
