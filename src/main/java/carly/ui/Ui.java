package carly.ui;

/** Deals with interactions with the user.*/
public class Ui {
    public static final String INDENTATION = "    ";

    public Ui() {}

    /** Displays a welcome message to the user with the current username. */
    public String welcomeMsg() {
        return("Hey there, I'm Carly.\n" + "What can I do for you?");
    }

    /** Displays a farewell message to the user with the current username. */
    public String byeMsg() {
        return("Bye. I'll see you next time!");
    }

}

