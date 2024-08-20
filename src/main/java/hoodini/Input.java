package hoodini;

/**
 * Abstract class as users should not be able to
 * create an input object.
 */
abstract class Input {

    private String input;
    private boolean done;


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

    public void done() {
        if(this.done) {
            System.out.println("This has already been marked");
        } else {
            this.done = true;
            System.out.print("I have marked the task as done:\n" + this + "\n");
        }
    }

    /**
     * Marks the task/input as undone
     * Used to handle inputs from user.
     */

    public void unDone() {
        if(!this.done) {
            System.out.println("This has already been unmarked");
        } else {
            this.done = false;
            System.out.print("I have marked the task as undone: \n" + this +"\n");

        }
    }






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
