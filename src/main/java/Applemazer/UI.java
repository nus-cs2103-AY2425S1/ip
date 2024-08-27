package applemazer;

public class Ui {

    /**
     * Constructor for the Ui object.
     */
    public Ui() {}

    /**
     * Greeting message printed when starting up the chatbot.
     */
    public void greeting() {
        String greeting = "Hello! I'm Applemazer.\nWhat can I do for you?\n";
        System.out.println(greeting);
    }

    /**
     * Farewell message printed when shutting down the chatbot.
     */
    public void farewell() {
        String farewell = "Bye. Hope to see you again soon!";
        System.out.println(farewell);
    }
}
