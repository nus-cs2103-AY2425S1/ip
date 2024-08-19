package executable;

/**
 * A greet executable.
 *
 * @author Toh Yi Hui A0259080A
 */
public class Greet implements Executable {
    private String output;
    private String greeter;

    /**
     * Constructor for a new Greet.
     *
     * @param greeter the name of the greeter.
     */
    public Greet(String greeter) {
        this.greeter = greeter;
    }

    /**
     * Change the greeter's name.
     *
     * @param greeter the name of the greeter.
     * @return whether the greeter is changed.
     */
    public boolean setGreeter(String greeter) {
        if (this.greeter.equals(greeter)) {
            return false;
        }
        this.greeter = greeter;
        return true;
    }

    /**
     * Output a greeting message.
     *
     * @return 0.
     */
    @Override
    public int execute() {
        output = "Hello! I am " + greeter + "!\nWhat can I do for you?";
        return 0;
    }

    /**
     * Return the output of the executable.
     *
     * @return the output of the executable.
     */
    @Override
    public String getOutput() {
        return output;
    }
}
