package hoodini;

/**
 * Abstract class as users should not be able to
 * create an input object.
 */
public abstract class Input {

    private String input;
    private boolean done;

    /**
     * Constructor for Input object
     * @param input String input
     */

    public Input(String input) {
        this.input = input;
        this.done = false;

    }

    /**
     * Allows chatbot to check if the input
     * is empty.
     */
    public boolean empty() {
        return this.input.isEmpty();
    }

    /**
     * Marks the task/input as done.
     * Used to handle input read from file
     */
    public void markdone() {
        this.done = true;
    }

    /**
     * Marks the task/input as done
     * Used to handle input from user
     */

    public String done() {
        if (this.done) {
            return "This has already been marked";
        } else {
            this.done = true;
            return "I have marked the task "
                    + "as done:\n" + this + "\n";
        }
    }

    /**
     * Marks the task/input as undone
     * Used to handle inputs from user.
     */

    public String unDone() {
        if (!this.done) {
            return "This has already been unmarked";
        } else {
            this.done = false;
            return "I have marked the task "
                    + "as undone: \n" + this + "\n";

        }
    }


    /**
     * toString method for input object
     * @return String representation of the input object
     */

    @Override
    public String toString() {
        if (done) {
            String str = "[X] " + this.input;
            return str;
        } else {
            String str = "[ ] " + this.input;
            return str;
        }
    }
}
