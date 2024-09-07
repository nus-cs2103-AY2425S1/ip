package pebble;

/**
 *  Auxiliary class that handles stray tasks
 */
public class InvalidTask extends Task {
    public InvalidTask() {
        super("Invalid task, please contact administrator");
    }
}
