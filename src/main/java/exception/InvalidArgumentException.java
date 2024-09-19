package exception;

/**
 * The InvalidArgumentException class is used for invalid values for arguments
 */
public class InvalidArgumentException extends LevelHundredException {
    private String arg;
    private String val;

    /**
     * Constructor for an InvalidArgumentException
     * @param arg String representing the argument
     * @param val String representing the invalid value
     */
    public InvalidArgumentException(String arg, String val) {
        super("Invalid value for argument");
        this.arg = arg;
        this.val = val;
    }

    @Override
    public String toString() {
        return super.toString() + " " + this.arg + ": \"" + this.val + "\"";
    }
}
