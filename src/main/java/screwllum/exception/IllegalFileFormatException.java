package screwllum.exception;

public class IllegalFileFormatException extends ScrewllumException {
    public IllegalFileFormatException(String message) {
        super("How peculiar, the file being loaded contains " + message + " which is not the right format.\n"
            + "Please delete the existing file so that I can write a new one for you!");
    }
}
