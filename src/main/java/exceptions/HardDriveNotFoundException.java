package exceptions;

public class HardDriveNotFoundException extends DelphiException {
    /**
     * Constructs a new InvalidInputException with a default error message.
     */
    public HardDriveNotFoundException() {
        super("could not find the hard drive!");
    }
}
