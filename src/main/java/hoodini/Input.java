package hoodini;

/**
 * Abstract class as users should not be able to
 * create an input object.
 */
public abstract class Input {

    private String input;
    private boolean isDone;

    /**
     * Constructor for Input object
     * @param input String input
     */

    public Input(String input) {
        this.input = input;
        this.isDone = false;

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
        this.isDone = true;
    }

    /**
     * Marks the task/input as done
     * Used to handle input from user
     */

    public String done() {
        if (this.isDone) {
            return "This has already been marked";
        } else {
            this.isDone = true;
            return "I have marked the task "
                    + "as done:\n" + this + "\n";
        }
    }

    /**
     * Marks the task/input as undone
     * Used to handle inputs from user.
     */

    public String unDone() {
        if (!this.isDone) {
            return "This has already been unmarked";
        } else {
            this.isDone = false;
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
        if (isDone) {
            String str = "[X] " + this.input;
            return str;
        } else {
            String str = "[ ] " + this.input;
            return str;
        }
    }
}
