package boss.exceptions;

import boss.exceptions.BossException;

/**
 * Represents an exception where the user inputs a task that does not exist
 */
public class NonExistentTaskException extends BossException {

    private final int num;

    /**
     * Constructs an NonExistentTaskException with a number
     *
     * @param num invalid task number from user input
     */
    public NonExistentTaskException(int num) {
        super("" + num);
        this.num = num;
    }

    @Override
    public String toString() {
        return "My friend, task " + num + " does not exist!";
    }

}
