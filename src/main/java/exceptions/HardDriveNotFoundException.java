package exceptions;

/**
 * The HardDriveNotFoundException class represents a specific type of DelphiException
 * that is thrown when the hard disk text file which contains all the tasks that have
 * previously been inputted does not exist
 *
 * @author Jordan Chan
 */
public class HardDriveNotFoundException extends DelphiException {
    /**
     * Constructs a new InvalidInputException with a default error message.
     */
    public HardDriveNotFoundException() {
        super("could not find the hard drive!");
    }
}
